package com.github.bjlhx15.common.help.poi;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

public class ExcelTest {
    //创建 2003 excel 格式 xls
    @Test
    public void test() throws Exception {
        Workbook workbook = new HSSFWorkbook();//定义一个工作薄
        workbook.createSheet("sheet1");//需要默认创建一个sheet，否则打开时候报错
        FileOutputStream out = new FileOutputStream("test.xls");
        workbook.write(out);
        out.close();
    }

    //创建 2007 以后的excel格式 xlsx
    @Test
    public void test2() throws Exception {
        Workbook workbook = new XSSFWorkbook();//定义一个工作薄
        workbook.createSheet("sheet1");//需要默认创建一个sheet，否则打开时候报错
        FileOutputStream out = new FileOutputStream("test.xlsx");
        workbook.write(out);
        out.close();
    }

    // 基本测试 创建一个sheet 一行 几列数据
    @Test
    public void testBase() throws Exception {
        Workbook wk = new HSSFWorkbook();//创建一个工作薄
        Sheet sh = wk.createSheet("第一个sheet页");//创建一个sheet页
        Row row = sh.createRow(0);//创建第一行
        Cell cell = row.createCell(0);//创建第一行的第一个单元格
        cell.setCellValue(1);//为第一行第一个单元格塞值
        row.createCell(1).setCellValue(1.2);//创建第一行第2个单元格并赋值
        row.createCell(2).setCellValue("这是一个字符串");//创建第一行第3个单元格并赋值
        row.createCell(3).setCellValue(true);//创建第一行第4个单元格并赋值
        FileOutputStream out = new FileOutputStream("cells和sheet页.xls");
        wk.write(out);
        out.close();
    }

    // 设置单元格边框 颜色
    @Test
    public void testCellBorderColor() throws Exception {
        Workbook wb = new HSSFWorkbook();//创建工作簿
        Sheet sh = wb.createSheet("第一个sheet页");//创建一个sheet页
        Row row = sh.createRow(2);//创建一行
        Cell cell = row.createCell(2);//创建一个单元格
        cell.setCellValue(4);//设置值
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN);//设置底部边框
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());//设置底部边框颜色
        cellStyle.setBorderLeft(BorderStyle.THIN);//设置左部边框
        cellStyle.setLeftBorderColor(IndexedColors.BLUE.getIndex());//设置左部边框颜色
        cellStyle.setBorderRight(BorderStyle.THIN);//设置右部边框
        cellStyle.setRightBorderColor(IndexedColors.RED.getIndex());//设置右部边框颜色
        cellStyle.setBorderTop(BorderStyle.THIN);//设置顶部边框
        cellStyle.setTopBorderColor(IndexedColors.ORANGE.getIndex());//设置顶部边框颜色
        cell.setCellStyle(cellStyle);
        FileOutputStream out = new FileOutputStream("test.xls");
        wb.write(out);
        out.close();
    }

    @Test
    public void testDiffCell() throws Exception {
        Workbook wk = new HSSFWorkbook();//创建工作薄
        Sheet sh = wk.createSheet();//创建sheet页
        Row row = sh.createRow(0);//创建第一行
        row.createCell(0).setCellValue(1);
        row.createCell(1).setCellValue("字符串");
        row.createCell(2).setCellValue(true);
        //1、日期默认为 数值
        row.createCell(3).setCellValue(new Date());

        row.createCell(4).setCellValue(HSSFCell.ENCODING_COMPRESSED_UNICODE);
        row.createCell(5).setCellValue(false);
        //2、日期变成字符串
        row.createCell(6).setCellValue("2019-07-19");

        //3、单元格设置日期类型样式
        // 定义Cell格式
        CellStyle cellStyle = wk.createCellStyle();
        CreationHelper creationHelper = wk.getCreationHelper();
        cellStyle.setDataFormat(
                creationHelper.createDataFormat().getFormat("yyyy-MM-dd  hh:mm:ss")
        );

        Cell cell = row.createCell(7);//创建一个单元格
        cell.setCellValue(new Date()); // 插入格式化日期
        cell.setCellStyle(cellStyle);

        cell = row.createCell(8);        // 插入格式化日期
        cell.setCellValue(Calendar.getInstance());
        cell.setCellStyle(cellStyle);


        FileOutputStream out = new FileOutputStream("testDiffCell.xls");
        wk.write(out);
        out.close();
    }


    @Test
    public void testFont() throws Exception {
        Workbook workbook=new HSSFWorkbook();
        Sheet sheet=workbook.createSheet();
        Row row=sheet.createRow(1);
        //字体处理类
        Font font=workbook.createFont();
        font.setFontHeightInPoints((short)18);//设置字体高度
        font.setItalic(true);//字体是否是斜体
        font.setFontName("Courier New");//设置字体名字
        font.setColor(Font.COLOR_RED);

        CellStyle cellStyle=workbook.createCellStyle();
        cellStyle.setFont(font);
        Cell cell=row.createCell(1);
        cell.setCellValue("This is test fonts");
        cell.setCellStyle(cellStyle);

        FileOutputStream out=new FileOutputStream("testFont.xls");
        workbook.write(out);
        out.close();
    }

    @Test
    public void testRead() throws Exception {
        InputStream inputStream=new FileInputStream("/Users/lihongxu6/IdeaProjects/common/common-help/test.xls");//创建一个输入流读取单元格
        POIFSFileSystem fileSystem=new POIFSFileSystem(inputStream);//包装类，将读取的内容放入内存中
        Workbook wb=new HSSFWorkbook(fileSystem);
        Sheet sheet=wb.getSheetAt(0);//获取第一个sheet页
        Row row=sheet.getRow(0);//获取第一行
        Cell cell=row.getCell(0);//获取第一个单元格
        if(cell == null||"".equals(cell)) {
            cell=row.createCell(3);
        }
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue("测试单元格");

        FileOutputStream out=new FileOutputStream("test.xls");
        wb.write(out);
        out.close();
        inputStream.close();
    }


    @Test
    public void testDataStyle() throws Exception {
        Workbook workbook=new HSSFWorkbook();
        Sheet sheet=workbook.createSheet("第一个sheet页");
        CellStyle style;
        DataFormat format=workbook.createDataFormat();
        Row row;
        Cell cell;

        short rowNum=0;
        short cellNume=1;

        row=sheet.createRow(rowNum++);
        cell=row.createCell(cellNume);
        cell.setCellValue(111111.25);

        style=workbook.createCellStyle();
        style.setDataFormat(format.getFormat("0.0"));
        cell.setCellStyle(style);

        row=sheet.createRow(rowNum++);
        cell=row.createCell(cellNume);
        cell.setCellValue(11111111.25);

        style=workbook.createCellStyle();
        style.setDataFormat(format.getFormat("#,##0.000"));
        cell.setCellStyle(style);

        FileOutputStream out=new FileOutputStream("testDataStyle.xls");
        workbook.write(out);
        out.close();
    }

    @Test
    public void testText() throws Exception {
        InputStream in= new FileInputStream("/Users/lihongxu6/IdeaProjects/common/common-help/test.xls");
        POIFSFileSystem pfs=new POIFSFileSystem(in);
        HSSFWorkbook hwb=new HSSFWorkbook(pfs);
        ExcelExtractor excelExtractor=new ExcelExtractor(hwb);//提取文本
        excelExtractor.setIncludeSheetNames(false);//不需要sheet页名字
        System.out.println(excelExtractor.getText());
        in.close();
    }


    @Test
    public void testItor() throws Exception {

        InputStream in= new FileInputStream("/Users/lihongxu6/IdeaProjects/common/common-help/test.xls");
        POIFSFileSystem pfs=new POIFSFileSystem(in);//文件系统可接受一个输入流
        HSSFWorkbook hwb=new HSSFWorkbook(pfs);
        HSSFSheet sheet=hwb.getSheetAt(0);//获取第一个sheet页
        if(sheet == null) {
            return;
        }
        //遍历row
        for(int rowNum= 0;rowNum <= sheet.getLastRowNum();rowNum++) {
            HSSFRow hssfRow=sheet.getRow(rowNum);
            if(hssfRow== null) {
                continue;
            }
            //遍历cells
            for(int cellNum=0;cellNum <= hssfRow.getLastCellNum();cellNum++) {
                HSSFCell hssfcell=hssfRow.getCell(cellNum);
                if(hssfcell== null) {
                    continue;
                }
                System.out.print(" "+getValue(hssfcell));
            }
            System.out.println();
        }
        in.close();
    }
    /**
     * 判断cell的数据格式
     * @param hssfcell
     * @return
     */
    private static String getValue(HSSFCell hssfcell) {
        if(hssfcell.getCellType() ==HSSFCell.CELL_TYPE_BOOLEAN ) {
            return String.valueOf(hssfcell.getBooleanCellValue());
        }else if(hssfcell.getCellType() ==HSSFCell.CELL_TYPE_NUMERIC ) {
            return String.valueOf(hssfcell.getNumericCellValue());
        }else {
            return String.valueOf(hssfcell.getStringCellValue());
        }
    }


    @Test
    public void testAlign() throws Exception {
        Workbook wb=new HSSFWorkbook();//创建工作薄
        Sheet sh=wb.createSheet();//创建sheet页
        Row row=sh.createRow(2);//创建一行
        row.setHeightInPoints(30);//设置行高

        createCell(wb,row,(short)0,HorizontalAlignment.CENTER,VerticalAlignment.BOTTOM);
        createCell(wb,row,(short)1,HorizontalAlignment.CENTER_SELECTION,VerticalAlignment.CENTER);
        createCell(wb,row,(short)2,HorizontalAlignment.FILL,VerticalAlignment.JUSTIFY);
        createCell(wb,row,(short)3,HorizontalAlignment.GENERAL,VerticalAlignment.TOP);
        FileOutputStream out=new FileOutputStream("testAlign.xls");
        wb.write(out);
        out.close();
    }

    /**
     * 设置单元格对齐方式
     * @param wb 工作薄
     * @param row 行
     * @param column 列
     * @param halign 水平
     * @param valign 垂直
     */
    private static void createCell(Workbook wb,Row row,short column,HorizontalAlignment halign,VerticalAlignment valign) {
        Cell cells=row.createCell(column);//创建单元格
        cells.setCellValue(new HSSFRichTextString("Align it"));//设置值
        CellStyle cellstyle=wb.createCellStyle();//创建单元格样式
        cellstyle.setAlignment(halign);//设置单元格水平方向对齐方式
        cellstyle.setVerticalAlignment(valign);//设置单元格垂直方向对齐方式
        cells.setCellStyle(cellstyle);//设置单元格样式
    }

    @Test
    public void testBg() throws Exception {
        Workbook wb=new HSSFWorkbook();
        Sheet sheet=wb.createSheet("第一个Sheet页");
        Row row=sheet.createRow(2);

        Cell cell=row.createCell(1);
        cell.setCellValue("xx");
        CellStyle cellStyle=wb.createCellStyle();
        cellStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());//前景色
        cellStyle.setFillPattern(FillPatternType.BIG_SPOTS);
        cell.setCellStyle(cellStyle);

        Cell cell2=row.createCell(3);
        cell2.setCellValue("yy");
        CellStyle cellStyle2=wb.createCellStyle();
        cellStyle2.setFillForegroundColor(IndexedColors.PINK.getIndex());//背景色
        cellStyle2.setFillPattern(FillPatternType.SPARSE_DOTS);
        cell2.setCellStyle(cellStyle2);

        FileOutputStream out=new FileOutputStream("testBg.xls");
        wb.write(out);
        out.close();
    }


    @Test
    public void testLine() throws Exception {
        Workbook workbook=new HSSFWorkbook();
        Sheet sheet=workbook.createSheet();
        Row row=sheet.createRow(0);
        Cell cell=row.createCell(2);
        cell.setCellValue("我要换行了\n有没有成功？");

        CellStyle cellStyle=workbook.createCellStyle();
        cellStyle.setWrapText(true);//设置可以换行
        cell.setCellStyle(cellStyle);

        row.setHeightInPoints(2*sheet.getDefaultRowHeightInPoints());//设置2倍的行高
        sheet.autoSizeColumn(2);//设置单元格宽度

        FileOutputStream out=new FileOutputStream("testLine.xls");
        workbook.write(out);
        out.close();
    }


    @Test
    public void testSpan() throws Exception {
        Workbook wb=new HSSFWorkbook();//创建工作薄
        Sheet sheet=wb.createSheet();//创建sheet页
        Row row=sheet.createRow(1);//创建行

        Cell cell=row.createCell(1);//创建单元格
        cell.setCellValue("单元格合并测试");
        /**
         * 合并单元格的API
         */
        sheet.addMergedRegion(new CellRangeAddress(
                1,//起始行
                2,//结束行
                1,//起始列
                2//结束列
        ));
        CellStyle cellStyle=wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setCellStyle(cellStyle);

        FileOutputStream out=new FileOutputStream("testSpan.xls");
        wb.write(out);
        out.close();
    }
}