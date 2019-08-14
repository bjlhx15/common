package com.github.bjlhx15.common.help.poi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ReflectTest {

    @Test
    public void testDate() throws Exception {


    }

    @Test
    public void testMap() throws Exception {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("ccc", "cccc");
        map.put("ddd", "dddd");
        map.put("aaa", "aaaa");
        map.put("bbb", "bbbb");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

    }


    @Test
    public void testJson() throws Exception {
        String json = "[{\"skuId\":4181123,\"vendorCode\":\"xiangtai\",\"vendor\":\"北京鲜味优选商贸有限公司\",\"dataProvider\":\"海南翔泰渔业股份有限公司\",\"datas\":[{\"uniqueCode\":\"17708000000000210780\",\"contents\":[{\"dataName\":\"商品信息\",\"dataDesc\":\"\",\"data\":[{\"key\":\"产品名称\",\"val\":\"鲷鱼片\",\"filedType\":\"text\",\"filedName\":\"item1\"},{\"key\":\"生产日期\",\"val\":\"2017-04-26\",\"filedType\":\"date\",\"filedName\":\"item2\"},{\"key\":\"产地\",\"val\":\"海南\",\"filedType\":\"text\",\"filedName\":\"item3\"},{\"key\":\"生产商\",\"val\":\"海南远生渔业有限公司\",\"filedType\":\"text\",\"filedName\":\"item4\"}]}]}],\"uniqueCode\":\"17708000000000210780\",\"dataType\":\"1\"}]";

        List<Map<String, String>> mapList = new ArrayList<>();

        JSONArray objects = JSONArray.parseArray(json);
        for (Object objectBody : objects) {
            Map<String, String> map = new LinkedHashMap<>();
            JSONObject jsonObject = (JSONObject) objectBody;
            JSONArray datasJsonArray = jsonObject.getJSONArray("datas");
            for (Object objDatas : datasJsonArray) {
                JSONObject datasObj = (JSONObject) objDatas;
                JSONArray contentsArray = datasObj.getJSONArray("contents");
                for (Object objContent : contentsArray) {
                    JSONObject contentObj = (JSONObject) objContent;
                    String name = contentObj.getString("dataName");
                    JSONArray dataArray = contentObj.getJSONArray("data");
                    for (Object objData : dataArray) {
                        JSONObject dataObj = (JSONObject) objData;
                        String key = dataObj.getString("key");
                        map.put(MessageFormat.format("[{0}]{1}", name, key), dataObj.getString("val"));
                    }

                }

            }
            if (map.size() > 0) {
                mapList.add(map);
            }
        }
        for (Map<String, String> stringMap : mapList) {
            for (Map.Entry<String, String> entry : stringMap.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }
    }
}