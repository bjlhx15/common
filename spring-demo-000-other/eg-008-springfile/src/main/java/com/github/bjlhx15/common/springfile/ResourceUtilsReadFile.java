package com.github.bjlhx15.common.springfile;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;


@Component
public class ResourceUtilsReadFile {

    //在Spring中还有另一种检索资源的方法，但是ResourceUtils Javadoc很清楚说明该类主要是内部使用
    public File method5() throws FileNotFoundException {
        return ResourceUtils.getFile("classpath:waitReadFile.txt");
    }
}


