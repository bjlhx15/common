package com.github.bjlhx15.common.springdemo.event.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author lihongxu6
 * @version 1.0
 * @className SpringBeanUtil
 * @description TODO
 * @date 2021-01-09 09:16
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    // 获取ApplicationContext对象
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtil.applicationContext = applicationContext;
    }

    /**
     * @Title: getBeanByName
     * @Description: TODO  通过bean的名字来获取Spring容器中的bean
     * @param beanName
     * @return
     * @return: Object
     */
    public static Object getBeanByName(String beanName) {
        if (applicationContext == null){
            return null;
        }
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

    public static void getAllBean(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
