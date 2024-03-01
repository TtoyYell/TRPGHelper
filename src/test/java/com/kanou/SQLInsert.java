package com.kanou;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2024/2/29 17:24
 */
public class SQLInsert {

    private static final String FILE_PATH = "C:\\Users\\Admin\\Desktop\\测试文件\\数据库导入\\huwai000.sql";
    private static final String URL = "http://10.192.18.226:30080/api/datasync_outdoor/execute_sql_file";

    public static void main(String[] args) throws IOException {
        File file = new File(FILE_PATH);
        post(file);
//        List<String> lines = readLinesFromFile(FILE_PATH);
//
//        int batchSize = 500;
//        int numBatches = lines.size() / batchSize;
//
//        for (int i = 0; i < numBatches; i++) {
//            List<String> batch = lines.subList(i * batchSize, (i + 1) * batchSize);
//            post(batch);
//        }
//
//        // handle remaining rows
//        int remaining = lines.size() % batchSize;
//        if (remaining > 0) {
//            List<String> batch = lines.subList(numBatches * batchSize, lines.size());
//            post(batch);
//        }

    }



    private static List<String> readLinesFromFile(String filePath) throws IOException {

        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private static void post(File file) {

        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建HttpPost请求
        HttpPost httpPost = new HttpPost(URL);

        // 设置请求体参数
        // 创建MultipartEntityBuilder，并添加参数
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("sql_file", file, ContentType.APPLICATION_OCTET_STREAM, file.getName());
        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);
        // 计算Content-Length并设置请求头
        try {
            //httpPost.setHeader("Content-Length", String.valueOf(1900));
            //httpPost.setHeader("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryDCfDFBAzpV6AOO7N");
            httpPost.setHeader("Cookie", "remember=true; adminer_version=4.8.1; username=admin; password=Js11025&; timestamp=1709273789756");
            httpPost.setHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIzaEt6bndvemJSQXk3cUYzenZmcXF4YlRNMnFJSWtnUCIsImV4cCI6MTcwOTMzMTM3NCwibmJmIjoxNzA5MjU5Mzc0LCJpYXQiOjE3MDkyNTkzNzR9.UuGCf0B1vNlOlTivem602hoRm3Ws6RI8rDRU6mpLyOk");
            httpPost.setHeader("Connection", "keep-alive");
            httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
            httpPost.setHeader("Accept", "*/*");
            httpPost.setHeader("Host", "10.192.18.226:30080");
            httpPost.setHeader("Origin", "http://10.192.18.226:30080");
            httpPost.setHeader("User-Agent", "PostmanRuntime/7.36.3");
            httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
            httpPost.setHeader("Cache-Control", "max-age=0");
            httpPost.setHeader("Referer", "http://10.192.18.226:30080/frontend/");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36 Edg/123.0.0.0");

            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(httpPost);

            // 处理响应
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                String responseStr = EntityUtils.toString(responseEntity);
                System.out.println("Response: " + responseStr);
                // 在这里处理返回的内容
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭HttpClient连接
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
