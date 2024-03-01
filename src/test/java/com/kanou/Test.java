package com.kanou;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2024/3/1 17:10
 */
public class Test {

    private static final String FILE_PATH = "C:\\Users\\Admin\\Desktop\\测试文件\\数据库导入\\xianxia_suspect_illegal.sql";

    public static void main(String[] args) throws IOException {
        int lineCount = readLineCount(FILE_PATH); // 获取总行数
        int batchSize = 500;


        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));

        for(int i=0; i<lineCount; i+=batchSize) {
            System.out.println("当前处理到第"+(i+batchSize)+"行");
            List<String> batch = new ArrayList<>();

            for(int j=0; j<batchSize && i+j<lineCount; j++) {
                batch.add(br.readLine());
            }

            File tempFile = writeBatchToTempFile(batch);

            readFileContent(tempFile);

            tempFile.delete(); // 删除临时文件

        }

        if(lineCount%batchSize > 0) {
            List<String> remainBatch = new ArrayList<>();
            for(int j=0; j<lineCount%batchSize; j++) {
                remainBatch.add(br.readLine());
            }

            File tempFile = writeBatchToTempFile(remainBatch);

            readFileContent(tempFile);
            tempFile.delete(); // 删除临时文件
        }

        br.close();

    }

    // 读取行数
    public static int readLineCount(String filePath) throws IOException {

        int count = 0;

        BufferedReader br = new BufferedReader(new FileReader(filePath));

        while(br.readLine() != null) {
            count++;
        }

        br.close();

        return count;

    }


    // 写入临时文件
    public static File writeBatchToTempFile(List<String> batch) throws IOException {
        File tempFile = File.createTempFile("sql", ".sql");
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
        bw.write(String.join("\n", batch));
        bw.close();
        return tempFile;
    }

    private static void readFileContent(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
    }
}
