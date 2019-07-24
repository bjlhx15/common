package com.github.bjlhx15.common.help.poi.datarule.exportexcel;

import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;

import java.util.List;

public class PoiExcelValidateSelectedStrategy implements IPoiExcelValidateStrategy {
    private List<String> selectedValue;

    public PoiExcelValidateSelectedStrategy(List<String> selectedValue) {
        this.selectedValue = selectedValue;
    }

    public List<String> getSelectedValue() {
        return selectedValue;
    }


    @Override
    public void handlerValid( Sheet sheet, int columnNum) {

        String[] array = this.getSelectedValue().toArray(new String[this.getSelectedValue().size()]);
        excelRuleSelect(sheet, array, 1, 65535, columnNum, columnNum);
    }

    public void excelRuleSelect(Sheet sheet, String[] rule, int firstRow, int lastRow, int firstCol, int lastCol) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        DataValidationConstraint constraint = helper.createExplicitListConstraint(rule);
        DataValidation dataValidation = helper.createValidation(constraint, addressList);
        dataValidation.createErrorBox("输入有误", "请选择下拉参数");
        if (dataValidation instanceof XSSFDataValidation) {
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        } else {
            dataValidation.setSuppressDropDownArrow(false);
        }

        sheet.addValidationData(dataValidation);
    }
}