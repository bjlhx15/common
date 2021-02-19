package com.github.bjlhx15.common.help.poi.datarule.exportexcel;


import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;

public class PoiExcelValidateNumberBetweenStrategy implements IPoiExcelValidateStrategy {
    private Integer min;
    private Integer max;

    public PoiExcelValidateNumberBetweenStrategy(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void handlerValid(Sheet sheet, int columnNum) {
        excelRuleNumberBetween(sheet, this.getMin(), this.getMax(), 1, 65535,columnNum, columnNum);
    }

    public void excelRuleNumberBetween(Sheet sheet, int min, int max, int firstRow, int lastRow, int firstCol, int lastCol){
        DataValidationHelper helper = sheet.getDataValidationHelper();
        CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);//设置行列范围
        //设置数据
        DataValidationConstraint constraint = helper.createIntegerConstraint(DataValidationConstraint.OperatorType.BETWEEN,
                String.valueOf(min),String.valueOf(max));
        DataValidation dataValidation = helper.createValidation(constraint, addressList);
        dataValidation.createErrorBox("输入值类型或大小有误", String.format("请输入%s~%s之间的数值",min,max));
        //处理Excel兼容性问题
        if(dataValidation instanceof XSSFDataValidation) {
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        }else {
            dataValidation.setSuppressDropDownArrow(false);
        }
        sheet.addValidationData(dataValidation);
    }


    public Integer getMin() {
        return min;
    }


    public Integer getMax() {
        return max;
    }
}
