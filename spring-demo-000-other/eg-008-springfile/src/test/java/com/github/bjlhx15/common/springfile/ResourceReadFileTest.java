package com.github.bjlhx15.common.springfile;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResourceReadFileTest {
    @Autowired
    ResourceReadFile resourceReadFile;

    @Test
    public void testMethod1ClassPath() throws Exception {
        Resource result = resourceReadFile.method1ClassPath();
        String filename = result.getFilename();
        ShowFileContent.show(result.getFile());
        Assert.assertEquals("waitReadFile.txt", filename);
    }

    @Test
    public void testMethod2Value() throws Exception {
        Resource result = resourceReadFile.method2Value();
        String filename = result.getFilename();
        ShowFileContent.show(result.getFile());
        Assert.assertEquals("waitReadFile.txt", filename);
    }
    @Test
    public void testMethod3Value() throws Exception {
        Resource result = resourceReadFile.method3Value();
        String filename = result.getFilename();
        ShowFileContent.show(result.getFile());
        Assert.assertEquals("waitReadFile.txt", filename);
    }
    @Test
    public void testMethod4Value() throws Exception {
        Resource result = resourceReadFile.method4Value();
        String filename = result.getFilename();
        ShowFileContent.show(result.getFile());
        Assert.assertEquals("waitReadFile.txt", filename);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme