package com.github.bjlhx15.common.threaddemo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author lihongxu6
 * @version 1.0
 * @className ThreadDemo11PreventDuplicatedHook
 * @description TODO
 * @date 2021-02-18 17:12
 */
public class ThreadDemo11PreventDuplicatedHook {
    private final static String LOCK_PATH="/Users/lihongxu6/tmp/";
    private final static String LOCK_FILE=".lock";
    private final static String PERMISSIONS="rw-------";

    public static void main(String[] args) throws IOException {
        checkRunning();
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("The program received kill signal.");
            getLockFile().toFile().delete();
        }));
        while (true){
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("program is running.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static void checkRunning() throws IOException {
        Path path=getLockFile();
        if(path.toFile().exists())
            throw new RuntimeException("The program already running.");
        Set<PosixFilePermission> perms= PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(path,PosixFilePermissions.asFileAttribute(perms));
    }
    private static Path getLockFile(){
        return Paths.get(LOCK_PATH,LOCK_FILE);
    }
}
