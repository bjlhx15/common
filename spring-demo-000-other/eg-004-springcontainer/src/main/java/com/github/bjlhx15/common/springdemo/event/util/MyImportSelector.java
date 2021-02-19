package com.github.bjlhx15.common.springdemo.event.util;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lihongxu6
 * @version 1.0
 * @className MyImportSelector
 * @description TODO
 * @date 2021-01-09 14:07
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //返回值为String数组类型，实际值应该是注入Bean类的全类名。
        return new String[]{"com.github.bjlhx15.common.springdemo.springcontainer.domain.auto.TestJoinBean"};
    }
}