package com.kanou.controller;

import com.kanou.util.SftpUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/12/21 21:06
 */
@Api(tags = "下载文件")
@RestController
@RequestMapping("/download")
public class DownLoadController {
    @GetMapping("/test")
    public void testRest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SftpUtil sftpUtil = new SftpUtil("47.100.60.104",22,"root","y@cp3winer");
        boolean connected = sftpUtil.isConnected();
        InputStream download = sftpUtil.download("/home/TRPG", "trpg-0.0.1-SNAPSHOT.jar");
        ServletOutputStream fileOutputStream = response.getOutputStream();
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String("trpg-0.0.1-SNAPSHOT.jar".getBytes("utf-8"), "ISO-8859-1"));//设置网页显示的文件编码
        byte[] buf = new byte[1024];
        int len = -1;
        while ((len = download.read(buf))!=-1) {
            fileOutputStream.write(buf,0,len);
        }
    }
}
