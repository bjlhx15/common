package com.github.bjlhx15.common.help.poi.datarule.importexcel;

import java.util.Map;

public abstract class ImportRuleValidDecorator implements IImportValidate{
    private IImportValidate iImportValidate;

    public ImportRuleValidDecorator(IImportValidate iImportValidate) {
        this.iImportValidate = iImportValidate;
    }

    @Override
    public Map.Entry<Boolean, String> handlerValid(Object value) {
        return iImportValidate.handlerValid(value);
    }
}
