package com.github.bjlhx15.common.help.poi.datarule.importexcel;

public abstract class ImportRuleValidDecorator implements IImportValidate{
    private IImportValidate iImportValidate;

    public ImportRuleValidDecorator(IImportValidate iImportValidate) {
        this.iImportValidate = iImportValidate;
    }
}
