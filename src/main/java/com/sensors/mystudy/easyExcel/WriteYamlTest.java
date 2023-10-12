package com.sensors.mystudy.easyExcel;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class WriteYamlTest {
    public static void main(String[] args) {
         List<FieldInfo> feildInfos = ReadExcelExample.readExcel("D:\\Project\\mystudy\\基金.xlsx");
        List<FieldInfo> feildInfos2 = ReadExcelExample.readExcel("D:\\Project\\mystudy\\基金2.xlsx");
         FieldConfig fieldConfig = new FieldConfig();
         fieldConfig.setTbdxfundtranscfm(feildInfos2);
         fieldConfig.setTbdxfundtransreq(feildInfos);
         write(fieldConfig);
         FieldConfig feildInfos1 = readYaml("data.yaml");
        System.out.println(feildInfos1);
    }
    public static void write( FieldConfig feildInfos){
        try {
            Yaml yaml = new Yaml();
            // 将数据写入 YAML 文件
            FileWriter writer = new FileWriter("D:\\Project\\mystudy\\src\\main\\resources\\data.yaml");
            yaml.dump(feildInfos, writer);
            writer.close();
            System.out.println("YAML 文件写入成功！");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("错误：无法写入 YAML 文件。");
        }
    }
    public static FieldConfig readYaml(String path){


        Yaml yaml = new Yaml(new Constructor(FieldConfig.class));
        FieldConfig fieldConfig = new FieldConfig();

        try (InputStream inputStream = WriteYamlTest.class.getClassLoader().getResourceAsStream(path)) {
            if (inputStream != null) {
                fieldConfig = yaml.load(inputStream);
            } else {

            }
        } catch (Exception e) {

        }
        return fieldConfig;

    }

}
