package com.sensors.mystudy.temp;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description
 *
 * @author tyw
 * @since 2022/6/20
 */

public class Demo {
    public static void main(String[] args)  throws Exception {
      System.out.println("测试回滚，线上代码2");
//
            List<String> savePaths = new ArrayList<>();
            SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
            String finalFileName = df.format(new Date()) + ".txt";
            String localPath = "111.txt";
            savePaths.add(localPath);
            File file = new File(localPath);



            InputStream inputStream = new FileInputStream("C:\\Users\\唐跃文\\Desktop\\split.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            BufferedWriter bufferedWriter = new BufferedWriter(new java.io.FileWriter(file, true));
            Integer i = 0, j = 0;

                while ((line = reader.readLine()) != null) {
                    try {

                        System.out.println("文件大小"+file.length());
                        if (file.length() > Double.parseDouble("5")) {//1.5G
                            bufferedWriter.flush();
                            bufferedWriter.close();
                            finalFileName = df.format(new Date()) + ".txt";
                            localPath = "222.txt";
                            savePaths.add(localPath);
                            file = new File(localPath);
                            bufferedWriter = new BufferedWriter(new java.io.FileWriter(file, true));
                        }


                        j++;
                        //文件列分割符为 "\1"
                        String[] split = line.split(",");
                        if (!"\\N".equals(split[1]) && !"$second_id".equals(split[1])) {
                            bufferedWriter.write(split[1] + "\n");
                            i++;
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println(e.getMessage() + line + "报错行数：" + j);
                    }finally {
                        bufferedWriter.flush();
                    }
                }

            bufferedWriter.close();
            inputStream.close();
            System.out.println("download success" + "，一共" + j + "行,有效数据"+i+"行");



    }
}
