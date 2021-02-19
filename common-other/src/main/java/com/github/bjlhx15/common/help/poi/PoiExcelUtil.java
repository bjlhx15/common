package com.github.bjlhx15.common.help.poi;

import com.github.bjlhx15.common.help.poi.datarule.importexcel.ImportRuleValidDecorator;
import com.github.bjlhx15.common.help.poi.datarule.exportexcel.StrategyContext;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class PoiExcelUtil {
    /**
     * @param datas 数据
     * @param <T>
     * @throws Exception
     */
    public static <T> OutputStream exportExcel(List<T> datas) throws Exception {
        return exportExcel(datas, null, null, null);
    }

    /**
     * @param datas         数据
     * @param headerMapping 表头 以及过滤
     * @param <T>
     * @throws Exception
     */
    public static <T> OutputStream exportExcel(List<T> datas, List<Map.Entry<String, Map.Entry<String, StrategyContext>>> headerMapping) throws Exception {
        return exportExcel(datas, headerMapping, null, null);
    }


    /**
     * @param datas             数据
     * @param headerMapping     表头 以及过滤
     * @param fieldReplaceValue 实际值以及映射
     * @param <T>
     * @throws Exception
     */
    public static <T> OutputStream exportExcel(List<T> datas, List<Map.Entry<String, Map.Entry<String, StrategyContext>>> headerMapping,
                                               List<Map.Entry<String, List<Map.Entry<String, String>>>> fieldReplaceValue) throws Exception {
        return exportExcel(datas, headerMapping, fieldReplaceValue, null);
    }

    /**
     * @param datas             数据
     * @param headerMapping     表头 以及过滤 name 姓名
     * @param fieldReplaceValue 实际值以及映射
     * @param datePattern       日期模式 默认日期类型 yyyy-MM-dd HH:mm:ss
     * @param <T>
     * @throws Exception
     */
    public static <T> OutputStream exportExcel(List<T> datas, List<Map.Entry<String, Map.Entry<String, StrategyContext>>> headerMapping,
                                               List<Map.Entry<String, List<Map.Entry<String, String>>>> fieldReplaceValue, Map<String, String> datePattern) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        boolean flagHeaderCreate = true;
        int rowIndex = 0;
        List<Field> listField = new ArrayList<>();//字段类型
        List<Map.Entry<String, Integer>> listMapHeader = new ArrayList<>();//字段类型
//        Map<String, Integer> listMapHeader = new LinkedHashMap<>();//字段类型
//        List<Map.Entry<Field, CellDataType>> listField = new ArrayList<>();//字段类型

        // 设置列宽和设置单元格格式为纯文本
        DataFormat format = workbook.createDataFormat();
        CellStyle textStyle = workbook.createCellStyle();
        textStyle.setDataFormat(format.getFormat("@"));

        //列头mapping
        //headerMapping 有值 使用 headerMapping 列头
        Row rowHeader = sheet.createRow(rowIndex);
        if (headerMapping != null && headerMapping.size() > 0) {
            rowIndex = 1;
            // headerMapping 存在 按照映射导出
            for (int j = 0; j < headerMapping.size(); j++) {//遍历头
                Cell cell = rowHeader.createCell(j);
                Map.Entry<String, Map.Entry<String, StrategyContext>> entry = headerMapping.get(j);
//                entry.getValue().getValue();
                StrategyContext bean = entry.getValue().getValue();
                if (bean != null) {
                    bean.setSheet(sheet);
                    bean.setColumnNum(j);
                    bean.executeStrategy();
                }
                cell.setCellValue(entry.getValue().getKey());
            }
        }

        // 行数据处理
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                //列
                T data = datas.get(i);

                List<Field> fieldList = BaseUtil.getFieldAll(data.getClass());

                // 列头匹配
                if (flagHeaderCreate) {
                    flagHeaderCreate = false;
                    if (headerMapping != null && headerMapping.size() > 0) {
                        // headerMapping 存在 按照映射导出
                        // 使用它的列 和 类属性匹配
                        for (int j = 0; j < headerMapping.size(); j++) {////遍历mapping 遍历配置头 对应
//                            Cell cell = rowHeader.createCell(j);
//                            Map.Entry<String, String> entry = headerMapping.get(j);
                            Map.Entry<String, Map.Entry<String, StrategyContext>> entry = headerMapping.get(j);

                            for (int k = 0; k < fieldList.size(); k++) {//遍历类字段
                                Field field = fieldList.get(k);
                                if (entry.getKey().equalsIgnoreCase(field.getName())) {
                                    listField.add(field);
//                                    listField.add(new AbstractMap.SimpleEntry<>(field, entry.getValue().getValue()));
                                    break;
                                }
                            }
                        }
                    } else {
                        // headerMapping  不存在 按照类属性导出
                        rowIndex = 1;
                        if (data.getClass().getTypeName().equals("java.util.Map")
                                || data.getClass().getTypeName().equals("java.util.LinkedHashMap")
                                || data.getClass().getTypeName().equals("java.util.HashMap")
                                || data.getClass().getTypeName().equals("com.alibaba.fastjson.JSONObject")) {
                            Map<String, String> tmp = (Map) data;
                            int tmpKeyIndex = 0;
                            for (String s : tmp.keySet()) {
                                Cell cell = rowHeader.createCell(tmpKeyIndex);
                                cell.setCellValue(s);
                                listMapHeader.add(new AbstractMap.SimpleEntry<>(s, tmpKeyIndex));
                                tmpKeyIndex++;
                            }
                        } else {
                            for (int j = 0; j < fieldList.size(); j++) {
                                Field field = fieldList.get(j);
                                listField.add(field);
//                            listField.add(new AbstractMap.SimpleEntry<>(field, CellDataType._NONE));
                                Cell cell = rowHeader.createCell(j);
                                cell.setCellValue(field.getName());
                            }
                        }
                    }
                }
                if (headerMapping != null && headerMapping.size() > 0) {
                    for (int g = 0, length = headerMapping.size(); g < length; g++) {
                        //sheet.setColumnWidth(i, 6000);
                        sheet.setDefaultColumnStyle(i, textStyle);
                    }
                }
                if (listMapHeader != null && listMapHeader.size() > 0) {
                    for (int g = 0, length = listMapHeader.size(); g < length; g++) {
                        //sheet.setColumnWidth(i, 6000);
                        sheet.setDefaultColumnStyle(i, textStyle);
                    }
                }

                //行 数据
                Row row = sheet.createRow(i + rowIndex);

                if (data.getClass().getTypeName().equals("java.util.Map")
                        || data.getClass().getTypeName().equals("java.util.LinkedHashMap")
                        || data.getClass().getTypeName().equals("java.util.HashMap")
                        || data.getClass().getTypeName().equals("com.alibaba.fastjson.JSONObject")) {
                    Map<String, String> tmp = (Map) data;
                    for (Map.Entry<String, String> entry : tmp.entrySet()) {//数据
//                        for (Map.Entry<String, Integer> integerEntry : listMapHeader.entrySet()) {//头
                        for (Map.Entry<String, Integer> integerEntry : listMapHeader) {//头
                            if (entry.getKey().equals(integerEntry.getKey())) {
                                Cell cell = row.createCell(integerEntry.getValue());
                                cell.setCellValue(entry.getValue());
                                break;
                            }
                        }
                    }
                } else {
                    // 列值—
                    for (int j = 0; j < listField.size(); j++) {
                        Field field = listField.get(j);

//                    List<Field> typeEntry = listField.get(j);
                        String fieldName = field.getName();
//                    Field field = typeEntry.getKey();

                        Method m = (Method) data.getClass().getMethod("get" + BaseUtil.getMethodName(fieldName));
                        String val = BaseUtil.handleDateField(field, m.invoke(data), datePattern);
                        String value = BaseUtil.handlerReplaceValue(fieldName, val, fieldReplaceValue);

                        Cell cell = row.createCell(j);
                        cell.setCellValue(value);

                    }
                }
            }
        }

//        OutputStream out = new FileOutputStream("testPersom.xls");
        OutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
//        out.close();
        return out;
    }


    //############################## 导入 ##############################
    //指定sheet
    public static Map.Entry<Boolean, List<List<Object>>> importExcel(InputStream inputStream, int sheet,
                                                                     Map.Entry<Class, Map.Entry<Boolean, Map<String, Map.Entry<String, ImportRuleValidDecorator>>>> toClass,
                                                                     OutputStream outputStream) throws Exception {
        List<Map.Entry<Class, Map.Entry<Boolean, Map<String, Map.Entry<String, ImportRuleValidDecorator>>>>> sheetToClass = new ArrayList<>();
        for (int i = 0; i < sheet; i++) {
            sheetToClass.add(null);
        }
        sheetToClass.add(toClass);
        return importExcel(inputStream, sheetToClass, outputStream);
    }

    //指定sheet class
    public static Map.Entry<Boolean, List<List<Object>>> importExcel(InputStream inputStream, int sheet, Class clazz,
                                                                     Map.Entry<Boolean, Map<String, Map.Entry<String, ImportRuleValidDecorator>>> toHeader,
                                                                     OutputStream outputStream) throws Exception {
        Map.Entry<Class, Map.Entry<Boolean, Map<String, Map.Entry<String, ImportRuleValidDecorator>>>> toClass = new AbstractMap.SimpleEntry<>(clazz, toHeader);
        return importExcel(inputStream, sheet, toClass, outputStream);
    }

    //指定sheet class header headerField
    public static Map.Entry<Boolean, List<List<Object>>> importExcel(InputStream inputStream, int sheet, Class clazz,
                                                                     Boolean header, Map<String, Map.Entry<String, ImportRuleValidDecorator>> toHeaderField,
                                                                     OutputStream outputStream) throws Exception {
        Map.Entry<Boolean, Map<String, Map.Entry<String, ImportRuleValidDecorator>>> toHeader = new AbstractMap.SimpleEntry<>(header, toHeaderField);
        return importExcel(inputStream, sheet, clazz, toHeader, outputStream);
    }

    //第 0个 sheet class header headerField
    public static Map.Entry<Boolean, List<List<Object>>> importExcel(InputStream inputStream, Class clazz, Boolean header,
                                                                     Map<String, Map.Entry<String, ImportRuleValidDecorator>> toHeaderField,
                                                                     OutputStream outputStream) throws Exception {
        Map.Entry<Boolean, Map<String, Map.Entry<String, ImportRuleValidDecorator>>> toHeader = new AbstractMap.SimpleEntry<>(header, toHeaderField);
        return importExcel(inputStream, 0, clazz, toHeader, outputStream);
    }

    /**
     * @param inputStream
     * @param sheetToClass 每个 sheet Class fistHeader firstLineHeaderToFiled<年龄,age>
     * @return
     * @throws Exception
     */
    public static Map.Entry<Boolean, List<List<Object>>> importExcel(InputStream inputStream,
                                                                     List<Map.Entry<Class, Map.Entry<Boolean, Map<String, Map.Entry<String, ImportRuleValidDecorator>>>>> sheetToClass,
                                                                     OutputStream outputStream) throws Exception {
        return BaseUtil.importExcel(inputStream, sheetToClass, outputStream);
    }
}
