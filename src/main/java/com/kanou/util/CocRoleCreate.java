package com.kanou.util;

import com.kanou.constant.CoordinateConstant;
import com.kanou.entity.CocRole;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

/**
 * 创建COC调查员角色卡工具类
 * @author  Yetianyi
 * @version 1.0
 * @date  2022/5/23 19:57
 */
public class CocRoleCreate {

    /** 模板图片名 */
    private static final String demoRoleFilename = "mode_1.png";

    /**
     * 根据角色卡数据生成角色卡图片并返回图片
     * @param role 角色卡信息
     * @param id 用户id
     * */
    public static BufferedImage getImageByRole(CocRole role,int id) throws IOException {
        // 获取角色卡第一页demo资源
        Resource resource = new ClassPathResource("COC_mode/mode_1.png");
        // 获取字节输入流
        InputStream inputStream = resource.getInputStream();
        // 获取图片对象
        BufferedImage image = ImageIO.read(inputStream);
        // 获取画笔对象
        Graphics2D pen = image.createGraphics();
        // 设为黑色
        pen.setColor(Color.BLACK);
        // 设置字体
        pen.setFont(new Font(null, Font.BOLD,16));
        // 写入文字
        pen.drawString(role.getPcName(), CoordinateConstant.PL_NAME[0], CoordinateConstant.PL_NAME[1]);

        // 创建输出路径和字符输出流
        String path = resource.getURL().getPath()
                .replace(demoRoleFilename, "role_out/" + System.currentTimeMillis() + "COC" + id + ".png");
        File file = new File(path);
        // 把文件路径存入db
        // TODO

        if (!file.exists()){
            // 如果输出文件夹不存在则创建
            file.getParentFile().mkdir();
        }
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(file);
        // 写出图片
        ImageIO.write(image,"png",imageOutputStream);
        return image;
    }
}
