package com.github.bjlhx15.common.help.poi;

import org.junit.Test;

import java.lang.reflect.Field;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PersonExtendTest {
    @Test
    // 01. java.util.Date --> java.time.LocalDateTime
    public void UDateToLocalDateTime() {
        PersonSon3 personSon3=new PersonSon3();
//        Field[] declaredFields = personSon3.getClass().getDeclaredFields();
//        for (Field declaredField : declaredFields) {
//            System.out.println(declaredField.getName());
//        }
//
//        Field[] declaredFields2 = personSon3.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getDeclaredFields();
//        for (Field declaredField : declaredFields2) {
//            System.out.println(declaredField.getName());
//        }
        System.out.println("========");
        List<Field> fieldAll = BaseUtil.getFieldAll(personSon3.getClass());
        for (Field declaredField : fieldAll) {
            System.out.println(declaredField.getName());
        }
    }





}
