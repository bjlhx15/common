package com.github.bjlhx15.common.springfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;


@Component
public class ResourceReadFile {

    //1、手工读取，访问classpath路径下文件
    public Resource method1ClassPath() {
        return new ClassPathResource("waitReadFile.txt");
    }


    //2、使用@Value注入Resource，@Value也支持其他前缀，如：file和url：
    @Value("classpath:waitReadFile.txt")
    Resource resourceFile;

    public Resource method2Value() {
        return resourceFile;
    }

    //3、使用ResourceLoader,
    @Autowired
    ResourceLoader resourceLoader;

    public Resource method3Value() {
        return resourceLoader.getResource("classpath:waitReadFile.txt");
    }

    //3.1、上述ResourceLoader是由所有具体的ApplicationContext实现的，也可以简单地通过ApplicationContext获取资源:
    @Autowired
    ApplicationContext context;

    public Resource method4Value() throws Exception {
        return context.getResource("classpath:waitReadFile.txt");
    }
}


