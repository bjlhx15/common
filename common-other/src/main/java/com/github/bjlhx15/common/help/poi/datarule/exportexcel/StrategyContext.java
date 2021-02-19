package com.github.bjlhx15.common.help.poi.datarule.exportexcel;

import org.apache.poi.ss.usermodel.Sheet;

public class StrategyContext {
    private Sheet sheet;
    private Integer columnNum;

    private IPoiExcelValidateStrategy strategy;

    public StrategyContext(IPoiExcelValidateStrategy strategy) {
        this.strategy = strategy;
    }
    public void executeStrategy(){
        strategy.handlerValid(this.sheet,this.columnNum);
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public Integer getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(Integer columnNum) {
        this.columnNum = columnNum;
    }
}
