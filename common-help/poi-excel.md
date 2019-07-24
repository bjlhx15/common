一、工具类
    com.github.bjlhx15.common.help.poi.POIExcelUtilStrategy
    导出excel：exportExcel
    导入excel：importExcel
    导出的excel单元格规则：
        默认添加：
            数值范围：StrategyContext context=new StrategyContext(new PoiExcelValidateNumberBetweenStrategy(10000,20000));
            下拉范围：StrategyContext validateSelect = new StrategyContext(new PoiExcelValidateSelectedStrategy(Arrays.asList("25999", "17890","wwww")));
        新增单元格规则：
            1、实现 com.github.bjlhx15.common.help.poi.datarule.exportexcel.IPoiExcelValidateStrategy  接口
                上述中的Sheet,cloumnNum是供接口使用者使用，程序中默认会注入
            2、示例
```java
public class PoiExcelValidateXXXXXStrategy implements IPoiExcelValidateStrategy {
    @Override
    public void handlerValid( Sheet sheet, int columnNum) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        CellRangeAddressList addressList = new CellRangeAddressList(1, 65535, columnNum, columnNum);
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
```
    导入的excel单元格规则：
        新增： com.github.bjlhx15.common.help.poi.datarule.importexcel.ImportRuleValidDecorator 扩展
        如 非空
```java
public class ImportRuleNotEmptyDecorator extends ImportRuleValidDecorator {
    public ImportRuleNotEmptyDecorator(IImportValidate iImportValidate) {
        super(iImportValidate);
    }

    @Override
    public Map.Entry<Boolean,String> handlerValid(Object value) {
        System.out.println("importRuleNotEmptyDecorator");
        if(value==null||value.toString().equals("")){
            return new AbstractMap.SimpleEntry<>(false,"内容不能为空");
        }
        return new AbstractMap.SimpleEntry<>(true,"ok");
    }
}

```