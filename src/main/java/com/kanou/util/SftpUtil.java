package com.kanou.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jcraft.jsch.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/12/21 22:01
 */
public class SftpUtil {
    //打印log日志
    private static final Log logger = LogFactory.getLog(SftpUtil.class);
    private static Date last_push_date = null;
    private Session sshSession;
    private ChannelSftp channel;
    private static ThreadLocal<SftpUtil> sftpLocal = new ThreadLocal<SftpUtil>();

    public SftpUtil(String host, int port, String username, String password) throws Exception {
        JSch jsch = new JSch();
        jsch.getSession(username, host, port);
        //根据用户名，密码，端口号获取session
        sshSession = jsch.getSession(username, host, port);
        sshSession.setPassword(password);
        //修改服务器/etc/ssh/sshd_config 中 GSSAPIAuthentication的值yes为no，解决用户不能远程登录
        sshSession.setConfig("userauth.gssapi-with-mic", "no");
        //为session对象设置properties,第一次访问服务器时不用输入yes
        sshSession.setConfig("StrictHostKeyChecking", "no");
        sshSession.connect();
        //获取sftp通道
        channel = (ChannelSftp)sshSession.openChannel("sftp");
        channel.connect();
        logger.info("连接ftp成功!" + sshSession);
    }

    /**
     * 是否已连接
     *
     * @return
     */
    public boolean isConnected() {
        return null != channel && channel.isConnected();
    }
    /**
     * 获取本地线程存储的sftp客户端
     *
     * @return
     * @throws Exception
     */
    public static SftpUtil getSftpUtil(String host, int port, String username, String password) throws Exception {
        //获取本地线程
        SftpUtil sftpUtil = sftpLocal.get();
        if (null == sftpUtil || !sftpUtil.isConnected()) {
            //将新连接防止本地线程，实现并发处理
            sftpLocal.set(new SftpUtil(host, port, username, password));
        }
        return sftpLocal.get();
    }
    /**
     * 释放本地线程存储的sftp客户端
     */
    public static void release() {
        if (null != sftpLocal.get()) {
            sftpLocal.get().closeChannel();
            logger.info("关闭连接" + sftpLocal.get().sshSession);
            sftpLocal.set(null);
        }
    }
    /**
     * 关闭通道
     *
     * @throws Exception
     */
    public void closeChannel() {
        if (null != channel) {
            try {
                channel.disconnect();
            } catch (Exception e) {
                logger.error("关闭SFTP通道发生异常:", e);
            }
        }
        if (null != sshSession) {
            try {
                sshSession.disconnect();
            } catch (Exception e) {
                logger.error("SFTP关闭 session异常:", e);
            }
        }
    }

    /**
     * @param directory 上传ftp的目录
     * @param uploadFile 本地文件
     *
     */
    public void uploadFile(String directory, String uploadFile) throws Exception {
        try {
            //执行列表展示ls 命令
            channel.ls(directory);
            //执行盘符切换cd 命令
            channel.cd(directory);
            File file = new File(uploadFile);
            InputStream input = new BufferedInputStream(new FileInputStream(file));
            channel.put(input, file.getName());
            try {
                if (input != null) input.close();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(file.getName() + "关闭文件时.....异常!" + e.getMessage());
            }
            if (file.exists()) {
                boolean b = file.delete();
                logger.info(file.getName() + "文件上传完毕!删除标识:" + b);
            }
        }catch (Exception e) {
            logger.error("【子目录创建中】：",e);
            //创建子目录
            channel.mkdir(directory);
        }
    }

    /**
     * @param directory 上传ftp的目录
     * @param uploadFile 本地文件目录
     *
     */
    public void upload(String directory, String uploadFile) throws Exception {
        try {
            //执行列表展示ls 命令
            channel.ls(directory);
            //执行盘符切换cd 命令
            channel.cd(directory);
            List<File> files = getFiles(uploadFile, new ArrayList<File>());
            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
                InputStream input = new BufferedInputStream(new FileInputStream(file));
                channel.put(input, file.getName());
                try {
                    if (input != null) input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(file.getName() + "关闭文件时.....异常!" + e.getMessage());
                }
                if (file.exists()) {
                    boolean b = file.delete();
                    logger.info(file.getName() + "文件上传完毕!删除标识:" + b);
                }
            }
        }catch (Exception e) {
            logger.error("【子目录创建中】：",e);
            //创建子目录
            channel.mkdir(directory);
        }
    }

    /**
     * 下载文件
     *
     * @param dir  远程目录
     * @param name 远程文件名
     * @return 文件字节数组
     */
    public InputStream download(String dir, String name) throws Exception {
        channel.cd(dir);
        return channel.get(name);
    }

    /**
     * 删除文件
     *
     * @param dir  远程目录
     * @param name 远程文件名
     */
    public void delete(String dir, String name) throws Exception {
        channel.cd(dir);
        channel.rm(name);
    }

    //获取文件
    public List<File> getFiles(String realpath, List<File> files) {
        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    if (null == last_push_date ) {
                        return true;
                    } else {
                        long modifyDate = file.lastModified();
                        return modifyDate > last_push_date.getTime();
                    }
                }
            });
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                } else {
                    files.add(file);
                }
                if (null == last_push_date) {
                    last_push_date = new Date(file.lastModified());
                } else {
                    long modifyDate = file.lastModified();
                    if (modifyDate > last_push_date.getTime()) {
                        last_push_date = new Date(modifyDate);
                    }
                }
            }
        }
        return files;
    }

    /**
     * 远程 执行命令并返回结果调用过程 是同步的（执行完才会返回）
     * @param command 命令
     * @return
     */
    public String exec(String command){
        String result="";
        ChannelExec openChannel =null;
        try {
            openChannel = (ChannelExec) sshSession.openChannel("exec");
            openChannel.setCommand(command);
            // int exitStatus = openChannel.getExitStatus();
            openChannel.connect();
            InputStream in = openChannel.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String buf = null;
            while ((buf = reader.readLine()) != null) {
                result+= new String(buf.getBytes("gbk"),"UTF-8")+" ";
            }
        } catch (JSchException | IOException e) {
            result+=e.getMessage();
        }finally{
            if(openChannel!=null&&!openChannel.isClosed()){
                openChannel.disconnect();
            }
        }
        return result.trim();
    }

    /**
     * 使用 BufferedWriter 写文件
     * @param filepath 文件目录
     * @param content  待写入内容
     * @throws IOException
     */
    public static void bufferedWriter(String filepath, String content) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath))) {
            bufferedWriter.write(content);
        }
    }

    public static void inputStreamToFile(InputStream inputStream, File file)
            throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}