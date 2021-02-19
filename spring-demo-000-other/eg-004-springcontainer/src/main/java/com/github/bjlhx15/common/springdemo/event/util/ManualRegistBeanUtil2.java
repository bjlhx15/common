package com.github.bjlhx15.common.springdemo.event.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lihongxu6
 * @version 1.0
 * @className RegisterBeanUtil2
 * @description TODO
 * @date 2021-01-11 22:13
 */
@Component
public class ManualRegistBeanUtil2 implements BeanDefinitionRegistryPostProcessor {

    private BeanDefinitionRegistry beanDefinitionRegistry;
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry1) throws
            BeansException {
        // 创建一个bean的定义类的对象
//        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(clazz);
        // 将Bean 的定义注册到Spring环境
        beanDefinitionRegistry=beanDefinitionRegistry1;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        // bean的名字为key, bean的实例为value
//        Map<String, Object> beanMap = configurableListableBeanFactory.getBeansWithAnnotation(AutoDiscoverClass.class);
    }

    public void registerBean(Class clazz){
        // 创建一个bean的定义类的对象
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(clazz);
        // 将Bean 的定义注册到Spring环境
        beanDefinitionRegistry.registerBeanDefinition("testService", rootBeanDefinition);
    }
}
