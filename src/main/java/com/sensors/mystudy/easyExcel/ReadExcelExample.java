package com.sensors.mystudy.easyExcel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadExcelExample {

    public static void main(String[] args) {
        String excelFilePath = "C:\\Users\\唐跃文\\Desktop\\项目文档\\北京银行\\基金（流水&指标）\\基金\\基金流水采集方案标注版本0830.xlsx";
         List<FieldInfo> feildInfos = readExcel(excelFilePath);
        System.out.println(feildInfos);

    }


    public static List<FieldInfo> readExcel(String path) {
        List<FieldInfo> feildInfos = new ArrayList<>();
        try {
			/*// 如果需要通过URL获取资源的加上以下的代码，不需要的省略就行
			URL url = new URL(strURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			// 设置超时间为3秒
			conn.setConnectTimeout(3*1000);
			// 防止屏蔽程序抓取而返回403错误
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			// 获取输入流
			InputStream inputStream = conn.getInputStream();
			......*/

            // 获取文件输入流
            InputStream inputStream = new FileInputStream(path);
            // 定义一个org.apache.poi.ss.usermodel.Workbook的变量
            Workbook workbook = null;
            // 截取路径名 . 后面的后缀名，判断是xls还是xlsx
            // 如果这个判断不对，就把equals换成 equalsIgnoreCase()
            if (path.substring(path.lastIndexOf(".") + 1).equals("xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (path.substring(path.lastIndexOf(".") + 1).equals("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            }

            // 获取第一张表
            Sheet sheet = workbook.getSheetAt(0);
            // sheet.getPhysicalNumberOfRows()获取总的行数
            // 循环读取每一行

            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                // 循环读取每一个格
                Row row = sheet.getRow(i);
                // row.getPhysicalNumberOfCells()获取总的列数
//                for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
//                    // 获取数据，但是我们获取的cell类型
////代码上的内容自己根据实际需要自己调整就可以，这里只是展示一个样式···~
//                    Cell cell = row.getCell(index);
//                    // 转换为字符串类型
//                    cell.setCellType(CellType.STRING);
//                    // 获取得到字符串
//                    String id = cell.getStringCellValue();
//                    System.out.println(id);
//
//                }
                FieldInfo feildInfo = new FieldInfo();
                feildInfo.setFrom(row.getCell(1).getStringCellValue());
                feildInfo.setTo(row.getCell(0).getStringCellValue());
                feildInfo.setType(row.getCell(2).getStringCellValue());
                feildInfo.setIsUpdateforInsert(row.getCell(3).getBooleanCellValue());
                feildInfos.add(feildInfo);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feildInfos;
    }
}