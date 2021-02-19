package com.github.bjlhx15.common.other.xml.xstream;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author lihongxu6
 * @version 1.0
 * @className XStreamUtilTest
 * @description TODO
 * @since 2020-08-07 14:20
 */
public class XStreamUtilTest {

    String xml = "<?xml version=\"1.0\" encoding=\"utf-16\"?>\n" +
            "<Person>\n" +
            "  <order xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
            "    <Mobile>12345678999</Mobile>\n" +
            "    <Name>李宏旭</Name>\n" +
            "    <Address>北京</Address>\n" +
            "    <TypeId>0</TypeId>\n" +
            "    <Ver>0</Ver>\n" +
            "    <Tags>\n" +
            "      <int>2013</int>\n" +
            "      <int>3011</int>\n" +
            "    </Tags>\n" +
            "    <Yn>1</Yn>\n" +
            "  </order>\n" +
            "  <company xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
            "    <Num>2</Num>\n" +
            "    <Name>JD</Name>\n" +
            "  </company>\n" +
            "</Person>";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void xmlToJavaBean() {
        Person bean = XStreamUtil.xmlToJavaBean(xml, new Class[]{Person.class});
        System.out.println(bean.getOrder().getName());
    }
}