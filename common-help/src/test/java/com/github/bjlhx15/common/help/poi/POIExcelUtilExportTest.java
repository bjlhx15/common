package com.github.bjlhx15.common.help.poi;

import com.github.bjlhx15.common.help.poi.datarule.exportexcel.PoiExcelValidateNumberBetweenStrategy;
import com.github.bjlhx15.common.help.poi.datarule.exportexcel.PoiExcelValidateSelectedStrategy;
import com.github.bjlhx15.common.help.poi.datarule.exportexcel.PoiExcelValidateUniqueueStrategy;
import com.github.bjlhx15.common.help.poi.datarule.exportexcel.StrategyContext;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class POIExcelUtilExportTest {


    @Test
    public void exportExcelBase() throws Exception {

        List<Person> datas = new ArrayList<>();
        datas.add(new Person("zhangsan", 30, "男"));
        datas.add(new Person("李四", 23, "女"));
        ByteArrayOutputStream outputStream = (ByteArrayOutputStream) PoiExcelUtil.exportExcel(datas, null, null);
        OutputStream out = new FileOutputStream("exportExcelBase.xlsx");
        outputStream.writeTo(out);
    }

    //导出基本结构以及增加校验
    @Test
    public void exportExcelBaseClass() throws Exception {
        List<Person> datas = new ArrayList<>();
        List<Map.Entry<String, Map.Entry<String, StrategyContext>>> headerMapping = new ArrayList<>();
        StrategyContext context = new StrategyContext(new PoiExcelValidateNumberBetweenStrategy(10000, 20000));
        StrategyContext selectContext = new StrategyContext(new PoiExcelValidateSelectedStrategy(Arrays.asList("25999", "17890", "wwww")));
        StrategyContext unqueueContext = new StrategyContext(new PoiExcelValidateUniqueueStrategy());

        headerMapping.add(new AbstractMap.SimpleEntry("name", new AbstractMap.SimpleEntry("序列号", context)));
        headerMapping.add(new AbstractMap.SimpleEntry("name", new AbstractMap.SimpleEntry("品牌", selectContext)));
        headerMapping.add(new AbstractMap.SimpleEntry("name", new AbstractMap.SimpleEntry("型号", unqueueContext)));
        headerMapping.add(new AbstractMap.SimpleEntry("name", new AbstractMap.SimpleEntry("内存", unqueueContext)));
        headerMapping.add(new AbstractMap.SimpleEntry("name", new AbstractMap.SimpleEntry("颜色", null)));
        ByteArrayOutputStream outputStream = (ByteArrayOutputStream) PoiExcelUtil.exportExcel(datas, headerMapping, null);
        OutputStream out = new FileOutputStream("exportExcelBaseClass.xlsx");
        outputStream.writeTo(out);
    }



    @Test
    public void exportExcelHeaderMapping() throws Exception {
        List<Map.Entry<String, Map.Entry<String, StrategyContext>>> headerMapping = new ArrayList<>();
        headerMapping.add(new AbstractMap.SimpleEntry("name", new AbstractMap.SimpleEntry("姓名", null)));
        List<Person> datas = new ArrayList<>();
        datas.add(new Person("zhangsan", 30, "男"));
        datas.add(new Person("李四", 23, "女"));
        ByteArrayOutputStream outputStream = (ByteArrayOutputStream) PoiExcelUtil.exportExcel(datas, headerMapping, null);
        OutputStream out = new FileOutputStream("exportExcelHeaderMapping.xlsx");
        outputStream.writeTo(out);
    }


    @Test
    public void exportExcelChangeText() throws Exception {
        List<Person> datas = new ArrayList<>();
        datas.add(new Person("zhangsan", 30, "男", new Date()));
        datas.add(new Person("李四", 23, "女"));

        List<Map.Entry<String, List<Map.Entry<String, String>>>> fieldReplaceValue = new ArrayList<>();
        List<Map.Entry<String, String>> keyV = new ArrayList<>();
        keyV.add(new AbstractMap.SimpleEntry<>("30", "中年"));
        keyV.add(new AbstractMap.SimpleEntry<>("23", "青年"));
        fieldReplaceValue.add(new AbstractMap.SimpleEntry<>("age", keyV));

        ByteArrayOutputStream outputStream = (ByteArrayOutputStream) PoiExcelUtil.exportExcel(datas, null, fieldReplaceValue);
        OutputStream out = new FileOutputStream("exportExcelChangeText.xlsx");
        outputStream.writeTo(out);

    }

    @Test
    public void exportExcelDateFormat() throws Exception {
        List<Person> datas = new ArrayList<>();
        datas.add(new Person("zhangsan", 30, "男", new Date()));
        datas.add(new Person("李四", 23, "女"));

        Map<String, String> mapDate = new HashMap<>();
        mapDate.put("date", "yyyy-MM");
        ByteArrayOutputStream outputStream = (ByteArrayOutputStream) PoiExcelUtil.exportExcel(datas, null, null, mapDate);
        OutputStream out = new FileOutputStream("exportExcelDateFormat.xlsx");
        outputStream.writeTo(out);

    }


    @Test
    public void exportExcelDataDate() throws Exception {
        List<Person> datas = new ArrayList<>();
        datas.add(new Person("zhangsan", 30, "男", new Date()));
        datas.add(new Person("李四", 23, "女"));

        ByteArrayOutputStream outputStream = (ByteArrayOutputStream)  PoiExcelUtil.exportExcel(datas);;
        OutputStream out = new FileOutputStream("exportExcelDataDate.xlsx");
        outputStream.writeTo(out);
    }
}