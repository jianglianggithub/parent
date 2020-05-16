package com;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Excel {

    public static void main(String[] args) throws IOException {
        HSSFWorkbook excel = new HSSFWorkbook();

        HSSFCellStyle style = createStyle(excel);
        HSSFSheet sheet1 = excel.createSheet("sheet1");
        cs:
        for (int i = 0; i <5; i++) {
            HSSFRow row = sheet1.createRow(i);

            boolean temp=true;
            for (int j = 0; j <5 ; j++) {

                HSSFCell cell = row.createCell(j);
                if (i==0){
                    //行首 标题
                    row.setHeightInPoints(50);
                    cell.setCellValue("标题");
                    cell.setCellStyle(createStyle2(excel));
                    continue cs;
                }
                row.setHeightInPoints(30);
                if (j==0) {
                    if (i==0) {
                        cell.setCellValue("头啊速度手动撒");
                        temp=!temp;
                    }
                    if (i==1) {
                        cell.setCellValue("是发去");
                        temp=!temp;
                    }
                    if (i==2) {
                        cell.setCellValue("额人他他有啊");
                        temp=!temp;
                    }
                    if (i==3) {
                        cell.setCellValue("是打是啊");
                        temp=!temp;
                    }
                    if (i==4) {
                        cell.setCellValue("去我额啊");
                        temp=!temp;
                    }
                }
                cell.setCellStyle(style);
                //设置列j代表一共有几列的宽度
                sheet1.setColumnWidth(  j, "数据".getBytes().length*2*256);
                if (j>1 && j <5)cell.setCellValue("数据");
            }
            /**
             * 参数1： 起始单元格的行数
             * 参数2：结束单元格的行数
             * 参数3： 起始单元格的列
             * 参数4： 结束单元格的列
             */

        }
        //当合并单元格 后会 自动取第一个格的值为 合并值
        CellRangeAddress cellAddresses = new CellRangeAddress(0, 0, 0, 4);
        sheet1.addMergedRegion(cellAddresses);

        excel.write(new FileOutputStream(new File("D:\\excel.xls")));
    }



    public static HSSFCellStyle createStyle(HSSFWorkbook wb){
        HSSFCellStyle cellStyle = wb.createCellStyle();
        //一、设置背景色:


        cellStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());// 设置背景色
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);



        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框

        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        cellStyle.setWrapText(true);//自动换行
        return cellStyle;
    }

    public static HSSFCellStyle createStyle2(HSSFWorkbook wb){
        HSSFCellStyle cellStyle = wb.createCellStyle();
        //一、设置背景色:


        cellStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());// 设置背景色
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        HSSFFont font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 30);
        font.setBold(true);
        cellStyle.setFont(font);


        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框

        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        cellStyle.setWrapText(true);//自动换行
        return cellStyle;
    }
}
