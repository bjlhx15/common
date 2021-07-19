package com.github.bjlhx15.common.springfile;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ShowFileContent {
    public static void show(File file) throws IOException {
        List<String> strings = Files.readAllLines(file.toPath());
        for (String string : strings) {
            System.out.println(string);
        }
    }

    //传统方式
    public static byte[] fileToByteNonNio(String path) throws FileNotFoundException {
        File file = ResourceUtils.getFile(path);
        //init array with file length
        byte[] bytesArray = new byte[(int) file.length()];

        try {
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytesArray); //read file into bytes[]
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytesArray;
    }

    //NIO方式
    public static byte[] fileToByteNio(String path) throws IOException {
        byte[] bFile = Files.readAllBytes(new File(path).toPath());
        //or this
//        byte[] bFile = Files.readAllBytes(Paths.get(path));
        return bFile;
    }

    //Classic, < JDK7
    public static void writeBytesToFileClassic(byte[] bFile, String fileDest) {
        FileOutputStream fileOuputStream = null;
        try {
            fileOuputStream = new FileOutputStream(fileDest);
            fileOuputStream.write(bFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOuputStream != null) {
                try {
                    fileOuputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Since JDK 7 - try resources
    public static void writeBytesToFile(byte[] bFile, String fileDest) {
        try (FileOutputStream fileOuputStream = new FileOutputStream(fileDest)) {
            fileOuputStream.write(bFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Since JDK 7, NIO
    public static void writeBytesToFileNio(byte[] bFile, String fileDest) {
        try {
            Path path = Paths.get(fileDest);
            Files.write(path, bFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
