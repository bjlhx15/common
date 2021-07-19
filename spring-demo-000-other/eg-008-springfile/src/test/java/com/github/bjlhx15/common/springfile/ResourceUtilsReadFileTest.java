package com.github.bjlhx15.common.springfile;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class ResourceUtilsReadFileTest {
    ResourceUtilsReadFile resourceUtilsReadFile = new ResourceUtilsReadFile();

    @Test
    public void testMethod5() throws Exception {
        File result = resourceUtilsReadFile.method5();
        String filename = result.getName();
        ShowFileContent.show(result);
        Assert.assertEquals("waitReadFile.txt", filename);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme