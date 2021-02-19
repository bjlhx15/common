package com.github.bjlhx15.common.help.poi.datarule.exportexcel;

import org.apache.poi.ss.usermodel.Sheet;

public interface IPoiExcelValidateStrategy {
    void handlerValid(Sheet sheet, int columnNum);
}
