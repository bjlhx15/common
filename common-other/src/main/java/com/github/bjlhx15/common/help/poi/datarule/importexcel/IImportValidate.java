package com.github.bjlhx15.common.help.poi.datarule.importexcel;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;

public interface IImportValidate {
    Map.Entry<Boolean,String> handlerValid(Object value);
}
