package com.github.bjlhx15.common.help.poi.datarule.importexcel;

import java.util.AbstractMap;
import java.util.Map;

public class ImportRuleIntegerBetweenDecorator extends ImportRuleValidDecorator {
    private Integer min;
    private Integer max;

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public ImportRuleIntegerBetweenDecorator(IImportValidate iImportValidate) {
        super(iImportValidate);
    }

    public ImportRuleIntegerBetweenDecorator(IImportValidate iImportValidate, Integer min, Integer max) {
        super(iImportValidate);
        this.min = min;
        this.max = max;
    }

    @Override
    public Map.Entry<Boolean,String> handlerValid(Object value) {
        System.out.println("ImportRuleIntegerBetweenDecorator");
        if(value==null||value.toString().equals("")){
            return new AbstractMap.SimpleEntry<>(true,"ok");
        }
        try {
            Integer value1 = Integer.valueOf(value.toString());
            if(value1>max||value1<min){
                return new AbstractMap.SimpleEntry<>(false,"数据不合理");
            }
            return new AbstractMap.SimpleEntry<>(true,"ok");
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("转换异常");
            return new AbstractMap.SimpleEntry<>(false,"转换异常");
        }
    }
}
