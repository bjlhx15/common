package com.github.bjlhx15.common.help.poi;

import com.github.bjlhx15.common.help.poi.datarule.importexcel.ImportRuleValidDecorator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BaseUtil {
    // 替换字段值
    public static String handlerReplaceValue(String field, String oldValue, List<Map.Entry<String, List<Map.Entry<String, String>>>> fieldReplaceValue) throws Exception {
        if (fieldReplaceValue != null && fieldReplaceValue.size() > 0) {
            for (Map.Entry<String, List<Map.Entry<String, String>>> entry : fieldReplaceValue) {
                if (entry.getKey().equalsIgnoreCase(field)) {
                    for (Map.Entry<String, String> stringEntry : entry.getValue()) {
                        if (stringEntry.getKey().equalsIgnoreCase(oldValue)) {
                            return stringEntry.getValue();
                        }
                    }
                }
            }
        }
        return oldValue;
    }

    public static String handleDateField(Field field, Object val, Map<String, String> datePattern) throws Exception {
        if (val == null) {
            return "";
        }
        if (field.getGenericType().toString().equals("class java.util.Date")) {
            DateTimeFormatter formatter = null;
            if (datePattern != null && datePattern.size() > 0) {
                for (Map.Entry<String, String> entry : datePattern.entrySet()) {
                    if (entry.getKey().equalsIgnoreCase(field.getName())) {
                        formatter = DateTimeFormatter.ofPattern(entry.getValue() == null || entry.getValue() == "" ? "yyyy-MM-dd HH:mm:ss" : entry.getValue());
                        break;
                    } else {
                        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    }
                }
            } else {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            }
            Date date = (Date) val;
            LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            return localDateTime.format(formatter);
        }
        return val.toString();
    }


    // 把一个字符串的第一个字母大写、效率是最高的、
    public static String getMethodName(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    public static void setValue(Object o, Field field, Object value) throws Exception {
        //属性方法赋值
        String methodName = "set" + getMethodName(field.getName());
        String typeStr = field.getGenericType().toString();

        if (typeStr.equals("class java.lang.String")) {
            Method m = o.getClass().getMethod(methodName, String.class);
            m.invoke(o, value.toString());
        } else if (typeStr.equals("class java.lang.Integer")) {
            Method m = o.getClass().getMethod(methodName, Integer.class);
            m.invoke(o, Integer.parseInt(value.toString()));
        } else if (typeStr.equals("class java.lang.Long")) {
            Method m = o.getClass().getMethod(methodName, Long.class);
            m.invoke(o, Long.valueOf(value.toString()));
        } else if (typeStr.equals("class java.lang.Boolean")) {
            Method m = o.getClass().getMethod(methodName, Boolean.class);
            m.invoke(o, Boolean.valueOf(value.toString()));
        } else if (typeStr.equals("class java.lang.Double")) {
            Method m = o.getClass().getMethod(methodName, Double.class);
            m.invoke(o, Double.valueOf(value.toString()));
        } else if (typeStr.equals("class java.lang.Float")) {
            Method m = o.getClass().getMethod(methodName, Float.class);
            m.invoke(o, Double.valueOf(value.toString()));
        } else if (typeStr.equals("class java.util.Date")) {
            Method m = o.getClass().getMethod(methodName, Date.class);
            Date date;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime localDateTime = LocalDateTime.parse(value.toString(), formatter);
                date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            } catch (Exception e) {
                date = null;
            }
            m.invoke(o, date);
        }
    }


    /**
     * @param inputStream
     * @param sheetToClass 每个 sheet Class fistHeader firstLineHeaderToFiled<年龄,age>
     *                     List<Map.Entry<Class, Map.Entry<fistHeader, List<Map.Entry<年龄, age>>>>>
     * @return
     * @throws Exception
     */
    public static Map.Entry<Boolean,List<List<Object>>> importExcel(InputStream inputStream,
                                                                    List<Map.Entry<Class, Map.Entry<Boolean, Map<String,Map.Entry<String, ImportRuleValidDecorator>>>>> sheetToClass,
                                                                    OutputStream outputStream)
            throws Exception {
        Boolean successFlag=true;
        if (sheetToClass == null || sheetToClass.size() == 0) {
            return null;
        }
//        POIFSFileSystem fileSystem = new POIFSFileSystem(inputStream);//包装类，将读取的内容放入内存中
        Workbook workbook = new XSSFWorkbook(inputStream);
        List<List<Object>> result = new ArrayList<>();
        //遍历sheet
        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
//            Map.Entry<Class, Map.Entry<Boolean, List<Map.Entry<String, String>>>> sheetEntryEntry = sheetToClass.get(i);
            Map.Entry<Class, Map.Entry<Boolean, Map<String, Map.Entry<String, ImportRuleValidDecorator>>>> sheetEntryEntry = sheetToClass.get(i);
            if (sheetEntryEntry == null) {
                return null;
            }
            Class clazz = sheetEntryEntry.getKey();//类型
            boolean firstLineHeader = sheetEntryEntry.getValue().getKey(); // 第一行是否是头
//            List<Map.Entry<String, String>> firstLineHeaderToFiled = sheetEntryEntry.getValue().getValue(); //第一行 头字段与类对应关系
            Map<String, Map.Entry<String, ImportRuleValidDecorator>> firstLineHeaderToFiled = sheetEntryEntry.getValue().getValue();//第一行 头字段与类对应关系
            Sheet sheet = workbook.getSheetAt(i);//获取sheet页
//            Field[] fields = clazz.getDeclaredFields();

            List<Field> fields = BaseUtil.getFieldAll(clazz.getClass());
            List<Object> list = new ArrayList<>();
            result.add(list);

//            Map<Integer, Map.Entry<String, Field>> mapField = new TreeMap<>();//列 字段 类型
            Map<Integer, Map.Entry<ImportRuleValidDecorator, Field>> mapField = new TreeMap<>();//列 字段 类型

            //第一行是头 并且 需要 mapping
            if (firstLineHeader && firstLineHeaderToFiled != null && firstLineHeaderToFiled.size() > 0) {
//                if(headerFlag)
                // 第一行 头匹配
                Row rowHeader = sheet.getRow(0);//获取一行
                short cellNumHeader = rowHeader.getLastCellNum();//获取列数，比最后一列列标大1
                for (Map.Entry<String, Map.Entry<String, ImportRuleValidDecorator>> entry : firstLineHeaderToFiled.entrySet()) {

                    for (int k = 0; k < cellNumHeader; k++) { //遍历列
                        Cell cell = rowHeader.getCell(k);//获取一个单元格
//                        Object value = getValue(cell);
                        if (entry.getKey().equals(getValue(cell))) {
                            for (int l = 0; l < fields.size(); l++) {//遍历字段
                                Field field = fields.get(l);
                                if (field.getName().equals(entry.getValue().getKey())) {
                                    mapField.put(k, new AbstractMap.SimpleEntry<>(entry.getValue().getValue(), field));

                                    break;
                                }
                            }
                        }
                    }
                }
//                for (Map.Entry<String, String> entry : firstLineHeaderToFiled) {
//                    for (int k = 0; k < cellNumHeader; k++) { //遍历列
//                        Cell cell = rowHeader.getCell(k);//获取一个单元格
////                        Object value = getValue(cell);
//                        if (entry.getKey().equals(getValue(cell))) {
//                            for (int l = 0; l < fields.length; l++) {//遍历字段
//                                Field field = fields[l];
//                                if (field.getName().equals(entry.getValue())) {
//                                    mapField.put(k, new AbstractMap.SimpleEntry<>(entry.getValue(), field));
//
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }
                //后续数据
                int rowNum = sheet.getLastRowNum() + 1;//行数 获取的是最后一行的编号（编号从0开始）。
                for (int j = 1; j < rowNum; j++) {//遍历行
                    Object o = clazz.newInstance();
                    list.add(o);
                    Row row = sheet.getRow(j);//获取一行
                    for (Map.Entry<Integer, Map.Entry<ImportRuleValidDecorator, Field>> entry : mapField.entrySet()) {
                        Integer k = entry.getKey();
                        Cell cell = row.getCell(k);//获取一个单元格
                        Object value = getValue(cell);
                        //校验
                        ImportRuleValidDecorator validateDecorator = entry.getValue().getKey();
                        Map.Entry<Boolean, String> handlerValid = validateDecorator.handlerValid(value);
                        if(!handlerValid.getKey()){
                            successFlag=false;
                            short lastCellNum = row.getLastCellNum();
                            setCellValue(workbook,sheet,j,k,lastCellNum,handlerValid.getValue());
                        }else {
                            //反射读取值
                            BaseUtil.setValue(o, entry.getValue().getValue(), value);
                        }
                    }
//
//                    for (Map.Entry<Integer, Map.Entry<String, Field>> entry : mapField.entrySet()) {
//                        Integer k = entry.getKey();
//                        Cell cell = row.getCell(k);//获取一个单元格
//                        Object value = getValue(cell);
//
//                        BaseUtil.setValue(o, entry.getValue().getValue(), value);
//                    }
                }
            } else {
                int rowIndex = 0;
                //第一行是头 但是没有mapping
                if (firstLineHeader && (firstLineHeaderToFiled == null || firstLineHeaderToFiled.size() == 0)) {
                    rowIndex = 1;
                }
                int rowNum = sheet.getLastRowNum() + 1;//行数 获取的是最后一行的编号（编号从0开始）。
                for (rowIndex = rowIndex; rowIndex < rowNum; rowIndex++) {//遍历行
                    Object o = clazz.newInstance();
                    list.add(o);
                    Row row = sheet.getRow(rowIndex);//获取一行

                    //遍历类 字段
                    for (int l = 0; l < fields.size(); l++) {//遍历字段
                        Field field = fields.get(l);//类字段
                        Cell cell = row.getCell(l);//获取一个单元格
                        Object value = getValue(cell);
                        BaseUtil.setValue(o, field, value);
                    }
                }
            }
        }

//        inputStream.close();
        if(outputStream!=null){
            workbook.write(outputStream);
        }
        return new AbstractMap.SimpleEntry<>(successFlag,result);
    }

    /**
     * 判断cell的数据格式
     *
     * @return
     */
    private static Object getValue(Cell cell) {
        if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
            return cell.getBooleanCellValue();
        } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else {
            return cell.getStringCellValue();
        }
    }

    public static void setCellValue(Workbook workbook, Sheet sheet,int rowNum,int columnNum,int columnMsg,String value){
        Row row = sheet.getRow(rowNum);
        //原单元格提醒
        Cell rowCell = row.getCell(columnNum);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THICK);//设置底部边框
        cellStyle.setBottomBorderColor(IndexedColors.RED.getIndex());//设置底部边框颜色
        cellStyle.setBorderLeft(BorderStyle.THICK);//设置左部边框
        cellStyle.setLeftBorderColor(IndexedColors.RED.getIndex());//设置左部边框颜色
        cellStyle.setBorderRight(BorderStyle.THICK);//设置右部边框
        cellStyle.setRightBorderColor(IndexedColors.RED.getIndex());//设置右部边框颜色
        cellStyle.setBorderTop(BorderStyle.THICK);//设置顶部边框
        cellStyle.setTopBorderColor(IndexedColors.RED.getIndex());//设置顶部边框颜色
        //字体处理类
        Font font=workbook.createFont();
        font.setColor(Font.COLOR_RED);
        cellStyle.setFont(font);

        rowCell.setCellStyle(cellStyle);


        //消息
        Cell cell = row.createCell(columnMsg);
        CellStyle cellStyle2 = workbook.createCellStyle();

        Font font2=workbook.createFont();
        font2.setColor(Font.COLOR_RED);
        font2.setBold(true);
        cellStyle2.setFont(font2);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("单元格["+(columnNum+1)+"]异常："+value);
    }

    public static List<Field>  getFieldAll(Class clazz){
        List<Field> list =new ArrayList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        list.addAll(Arrays.asList(declaredFields));
        if(clazz.getSuperclass()!=null){
            list.addAll(getFieldAll(clazz.getSuperclass()));
        }
        return list;
    }
}
