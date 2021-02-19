package com.github.bjlhx15.common.help.barcode;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class GoogleBarCodeUtilsTest {

    @Test
    public void insertWords() throws IOException {
        int width = 600;
        int height = 50;
        int wordHeight = 75;
        BufferedImage image = GoogleBarCodeUtils.insertWords(
                GoogleBarCodeUtils.getBarCode("12345678abc", width, height),
                "12345678abc李宏旭",
                width, height, wordHeight
        );
        ImageIO.write(image, "jpg", new File("/Users/lihongxu6/work/abc.jpg"));
    }
}