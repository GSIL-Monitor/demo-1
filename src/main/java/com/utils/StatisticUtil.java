package com.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 统计
 *
 * @Author suosong
 * @Date 2018/8/17
 */
public abstract class StatisticUtil {

    /**
     * 统计文件的行数
     */
    public static int statisticFileLines(File file) throws IOException {
        if (!file.exists()) return 0;
        int count = 0;
        if (file.isFile()) {
            count = FileUtils.readLines(file).size();
        } else {
            for (File childFile : file.listFiles()) {
                count += statisticFileLines(childFile);
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {

        //计算文件的行数
        int count = statisticFileLines(new File("src/main/java"));
        System.out.println("demo项目一共有 " + count + " 行");
        //写入测试文件
        File file = new File("src/main/resources/lines.txt");
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        List<String> strings = FileUtils.readLines(file);
        if (strings != null && strings.size() > 1) {
            String lastLine = strings.get(strings.size() - 1);
            String lastDate = lastLine.split("\t")[0];
            int lastCount = Integer.parseInt(lastLine.split("\t")[1]);
            if (!today.equals(lastDate)) {
                int diffCount = count - lastCount;
                String content = today +
                        "\t" + count + "\t" + diffCount + "\n";
                FileUtils.write(file, content, true);
            }
        }
    }
}
