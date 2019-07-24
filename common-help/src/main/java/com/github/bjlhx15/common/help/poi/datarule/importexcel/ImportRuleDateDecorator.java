package com.github.bjlhx15.common.help.poi.datarule.importexcel;

import java.util.AbstractMap;
import java.util.Map;

public class ImportRuleDateDecorator extends ImportRuleValidDecorator {
    public ImportRuleDateDecorator(IImportValidate iImportValidate) {
        super(iImportValidate);
    }

    @Override
    public Map.Entry<Boolean,String> handlerValid(Object value) {
        if(value==null||value.toString().equals("")){
            return new AbstractMap.SimpleEntry<>(false,"内容不能为空");
        }
        return new AbstractMap.SimpleEntry<>(true,"ok");
    }
}
