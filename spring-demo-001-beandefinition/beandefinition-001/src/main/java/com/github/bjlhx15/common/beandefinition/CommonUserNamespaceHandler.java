package com.github.bjlhx15.common.beandefinition;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class CommonUserNamespaceHandler extends NamespaceHandlerSupport {


    @Override
    public void init() {
        //将组件注册到 Spring 容器中。
        registerBeanDefinitionParser("commonuser", new CommonUserDefinitionParser());
    }
}
