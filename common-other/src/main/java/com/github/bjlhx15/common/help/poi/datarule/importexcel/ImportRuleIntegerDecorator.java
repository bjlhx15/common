package com.github.bjlhx15.common.help.poi.datarule.importexcel;

import java.util.AbstractMap;
import java.util.Map;

public class ImportRuleIntegerDecorator extends ImportRuleValidDecorator {
    public ImportRuleIntegerDecorator(IImportValidate iImportValidate) {
        super(iImportValidate);
    }

    @Override
    public Map.Entry<Boolean,String> handlerValid(Object value) {
        System.out.println("importRuleIntegerDecorator");
        if(value==null||value.toString().equals("")){
            return new AbstractMap.SimpleEntry<>(true,"ok");
        }
        try {
            Integer value1 = Integer.valueOf(value.toString());
            return new AbstractMap.SimpleEntry<>(true,"ok");
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("转换异常");
            return new AbstractMap.SimpleEntry<>(false,"转换异常");
        }
    }
}
