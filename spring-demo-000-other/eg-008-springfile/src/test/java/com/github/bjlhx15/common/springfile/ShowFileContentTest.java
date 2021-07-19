package com.github.bjlhx15.common.springfile;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ShowFileContentTest {

    private static final String UPLOAD_FOLDER = "/";
    @Test
    public void testFileToByteNonNio() throws Exception {
        FileInputStream fileInputStream = null;

        try {

            File file = ResourceUtils.getFile("classpath:waitReadFile.txt");
            byte[] bFile = new byte[(int) file.length()];

            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);

            //save bytes[] into a file
            ShowFileContent.writeBytesToFile(bFile, UPLOAD_FOLDER + "test1.txt");
            ShowFileContent.writeBytesToFileClassic(bFile, UPLOAD_FOLDER + "test2.txt");
            ShowFileContent.writeBytesToFileNio(bFile, UPLOAD_FOLDER + "test3.txt");
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme