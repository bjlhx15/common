package com.github.bjlhx15.common.help.poi.datarule.importexcel;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractMap;
import java.util.Map;

public class ColumnDataor implements IImportValidate {
    private final Logger logger =LoggerFactory.getLogger(this.getClass().getName());
    @Override
    public Map.Entry<Boolean,String> handlerValid(Object value) {
        String log="校验数据="+ JSON.toJSONString(value);
        logger.info(log);
        System.out.println(log);
        return new AbstractMap.SimpleEntry<>(true,"ok");
    }
}
