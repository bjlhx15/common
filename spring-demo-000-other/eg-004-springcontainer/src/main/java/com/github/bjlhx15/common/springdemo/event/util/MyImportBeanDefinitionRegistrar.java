package com.github.bjlhx15.common.springdemo.event.util;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lihongxu6
 * @version 1.0
 * @className MyImportBeanDefinitionRegistrar
 * @description TODO
 * @date 2021-01-09 14:09
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //可以从importingClassMetadata和registry对象获取一些上下文信息进行其他业务逻辑的判断
        boolean b1 = registry.containsBeanDefinition("com.github.bjlhx15.common.springdemo.springcontainer.domain.auto.TestJoinBeann");

        if(b1){
            registry.registerBeanDefinition("Class", new RootBeanDefinition(Class.class));
        }

        //需要的业务逻辑
        //指定bean定义信息（包括bean的类型、作用域...）
//        RootBeanDefinition beanDefinition = new RootBeanDefinition();
//        beanDefinition.setBeanClass(TestJoinBean.class);
//        registry.registerBeanDefinition("user",beanDefinition);
    }
}
