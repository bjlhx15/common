package com.github.bjlhx15.common.help.poi;

import com.github.bjlhx15.common.help.poi.datarule.importexcel.*;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class POIExcelUtilImportTest {
    // 导入excel 并且 增加数据校验规则
    @Test
    public void importExcelDataNoHeader() throws Exception {
        InputStream inputStream = new FileInputStream("/Users/lihongxu6/IdeaProjects/common/common-help/exportExcelBase.xlsx");//创建一个输入流读取单元格
        List<Map.Entry<Class, Map.Entry<Boolean, Map<String, Map.Entry<String, ImportRuleValidDecorator>>>>> sheetToClass = new ArrayList<>();


        Map<String, Map.Entry<String, ImportRuleValidDecorator>> toHeaderField = new HashMap<>();
        IImportValidate dataer = new ColumnDataor();
        ImportRuleValidDecorator decorator = new ImportRuleNotEmptyDecorator(dataer);
        toHeaderField.put("date", new AbstractMap.SimpleEntry<>("date", decorator));
        ImportRuleValidDecorator decorator2 = new ImportRuleIntegerDecorator(dataer);
        ImportRuleIntegerBetweenDecorator betweenDecorator = new ImportRuleIntegerBetweenDecorator(decorator2, 25, 80);


        toHeaderField.put("age", new AbstractMap.SimpleEntry<>("age", betweenDecorator));

        Map.Entry<Boolean, Map<String, Map.Entry<String, ImportRuleValidDecorator>>> header = new AbstractMap.SimpleEntry<>(true, toHeaderField);
        Map.Entry<Class, Map.Entry<Boolean, Map<String, Map.Entry<String, ImportRuleValidDecorator>>>> clazz = new AbstractMap.SimpleEntry<>(Person.class, header);
        sheetToClass.add(clazz);

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();

        Map.Entry<Boolean, List<List<Object>>> lists = PoiExcelUtil.importExcel(inputStream, sheetToClass,outputStream);

        if (lists.getKey()) {
            for (List<Object> list : lists.getValue()) {
                for (Object o : list) {
                    Person p = (Person) o;
                    System.out.println(p.toString());
                }
            }
        } else {
            System.out.println("--------导入失败");
            OutputStream out = new FileOutputStream("exportExcelBaseError.xlsx");
            outputStream.writeTo(out);
        }
    }

    @Test
    public void importExcelDataNoHeader2() throws Exception {
        InputStream inputStream = new FileInputStream("/Users/lihongxu6/IdeaProjects/common/common-help/exportExcelDataDate.xlsx");//创建一个输入流读取单元格
        List<Map.Entry<Class, Map.Entry<Boolean, Map<String,Map.Entry<String, ImportRuleValidDecorator>>>>> sheetToClass = new ArrayList<>();
        Map.Entry<Boolean, Map<String,Map.Entry<String, ImportRuleValidDecorator>>> header = new AbstractMap.SimpleEntry<>(true, null);
        Map.Entry<Class, Map.Entry<Boolean, Map<String,Map.Entry<String, ImportRuleValidDecorator>>>> clazz = new AbstractMap.SimpleEntry<>(Person.class, header);
        sheetToClass.add(clazz);
        Map.Entry<Boolean, List<List<Object>>> lists = PoiExcelUtil.importExcel(inputStream, sheetToClass,null);
        for (List<Object> list : lists.getValue()) {
            for (Object o : list) {
                Person p = (Person) o;
                System.out.println(p.toString());
            }
        }
    }

    @Test
    public void importExcelDataHeaderMapping() throws Exception {
        InputStream inputStream = new FileInputStream("/Users/lihongxu6/IdeaProjects/common/common-help/exportExcelDataDate2.xlsx");//创建一个输入流读取单元格
//        List<Map.Entry<String, String>> toHeaderField = new ArrayList<>();
        Map<String,Map.Entry<String, ImportRuleValidDecorator>> toHeaderField=new HashMap<>();

        IImportValidate dataer = new ColumnDataor();
        ImportRuleNotEmptyDecorator decorator = new ImportRuleNotEmptyDecorator(dataer);
        toHeaderField.put("年龄",new AbstractMap.SimpleEntry<>("age", decorator));
        toHeaderField.put("性别",new AbstractMap.SimpleEntry<>("gender", decorator));

        Map.Entry<Boolean, List<List<Object>>> lists = PoiExcelUtil.importExcel(inputStream, Person.class, true, toHeaderField,null);
        for (List<Object> list : lists.getValue()) {
            for (Object o : list) {
                Person p = (Person) o;
                System.out.println(p.toString());
            }
        }
    }



    @Test
    public void importExcelDataNoHeaderMap() throws Exception {
        InputStream inputStream = new FileInputStream("/Users/lihongxu6/IdeaProjects/common/common-help/exportExcelBase.xlsx");//创建一个输入流读取单元格
        List<Map.Entry<Class, Map.Entry<Boolean, Map<String, Map.Entry<String, ImportRuleValidDecorator>>>>> sheetToClass = new ArrayList<>();


        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();

        Map.Entry<Boolean, List<List<Object>>> lists = PoiExcelUtil.importExcel(inputStream,Map.class,true,null,outputStream);

        if (lists.getKey()) {
            for (List<Object> list : lists.getValue()) {
                for (Object o : list) {
                    Person p = (Person) o;
                    System.out.println(p.toString());
                }
            }
        } else {
            System.out.println("--------导入失败");
            OutputStream out = new FileOutputStream("exportExcelBaseError.xlsx");
            outputStream.writeTo(out);
        }
    }

}