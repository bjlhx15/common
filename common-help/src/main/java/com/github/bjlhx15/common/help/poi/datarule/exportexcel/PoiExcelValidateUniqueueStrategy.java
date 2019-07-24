package com.github.bjlhx15.common.help.poi.datarule.exportexcel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;

import java.text.MessageFormat;

public class PoiExcelValidateUniqueueStrategy implements IPoiExcelValidateStrategy {


    @Override
    public void handlerValid(Sheet sheet, int columnNum) {

//        String[] array = this.getSelectedValue().toArray(new String[this.getSelectedValue().size()]);
        excelRuleSelect(sheet, 1, 65535, columnNum, columnNum);
    }

    public void excelRuleSelect(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(firstCol);
        String r = ((XSSFCell) cell).getCTCell().getR();
        r = r.substring(0, 1);
        DataValidationHelper helper = sheet.getDataValidationHelper();
        CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        //唯一
        DataValidationConstraint constraint = helper.createCustomConstraint(MessageFormat.format("COUNTIF({0}:{0},{0}2)=1",r));
        DataValidation dataValidation = helper.createValidation(constraint, addressList);
        dataValidation.createErrorBox("错误：", "赋值属性列不允许重复");
        dataValidation.setShowErrorBox(true);
        dataValidation.setEmptyCellAllowed(true);
        dataValidation.setSuppressDropDownArrow(true);
        dataValidation.setShowPromptBox(true);
        dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);

        sheet.addValidationData(dataValidation);
    }
}