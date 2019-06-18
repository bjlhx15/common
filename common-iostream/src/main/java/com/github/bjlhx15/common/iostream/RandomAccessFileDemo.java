package com.github.bjlhx15.common.iostream;

import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
    /**
     * 利用随机访问流向文件写入数据
     */
    @Test
    public void t1() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : RandomAccessFileDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"RandomAccessFileDemo.txt";
        RandomAccessFile raf = new RandomAccessFile(filepath, "rw");
        raf.write("ABC我是测试用例123".getBytes());
        raf.close();
    }

    /**
     * 利用随机访问流读取文件的数据
     * @throws Exception
     */
    @Test
    public void t2() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : RandomAccessFileDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"RandomAccessFileDemo.txt";
        RandomAccessFile raf =  new RandomAccessFile(filepath, "r");
        byte[] b = new byte[1024];
        int len;
        while((len = raf.read(b)) != -1){
            System.out.println(new String(b, 0, len));
        }
        raf.close();
    }

    /**
     * 直接从某一位置开始读取数据，即跳过某一位置之前，使用seek
     * @throws Exception
     */
    @Test
    public void t3() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : RandomAccessFileDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"RandomAccessFileDemo.txt";
        RandomAccessFile raf = new RandomAccessFile(filepath, "r");
        //使用seek设置指针位置为3
        raf.seek(3);
        byte[] b = new byte[1024];
        int len;
        while((len = raf.read(b)) != -1){
            System.out.println(new String(b, 0, len));
        }
        raf.close();
    }

    /**
     * 如果不设置文件的指针，写入文件是从0下标开始，如果文件里面有数据，会覆盖掉从该下标开始到写入数据的结束位置，其它数据不变
     * @throws Exception
     */
    @Test
    public void t4() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : RandomAccessFileDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"RandomAccessFileDemo.txt";
        RandomAccessFile raf = new RandomAccessFile(filepath, "rw");
        raf.write("测试".getBytes());
        raf.close();
    }

    /**
     * 文件末尾追加信息
     * @throws Exception
     */
    @Test
    public void t5() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : RandomAccessFileDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"RandomAccessFileDemo.txt";
        RandomAccessFile raf = new RandomAccessFile(filepath, "rw");
        raf.seek(raf.length());
        raf.write("追加信息".getBytes());
        raf.close();
    }

    /**
     * 大家在网上下载文件的时候，是不是有时候会遇到什么网络中断、电脑出问题的情况，本来下载好了80%，如果出了问题，
     * 有些就只有重新下载，而有些可以从失败处继续下载，不用再重新开始下载。它多的实现原理就是使用到了指针。
     * 我们用一个下载的文件实例来记录下载到的位置，然后通过读写操作，每次读写的位置就是从已经下载好的文件的末尾开始，下面通过代码来展示
     * @throws Exception
     */
    @Test
    public void t6() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : RandomAccessFileDemo.class.getClass().getResource("/").getPath();
        String filepath=path+"RandomAccessFileDemo.txt";
        String copyfilepath=path+"RandomAccessFileDemo--2.txt";
        RandomAccessFile r = new RandomAccessFile(filepath, "r");
        //这个file就是我们下载到本地的文件，之后用file.length来记录下载的大小，每次读取和写入就从file.length开始
        File file = new File(copyfilepath);
        RandomAccessFile w = new RandomAccessFile(file,"rw" );
        r.seek(file.length());
        w.seek(file.length());
        byte[] b = new byte[1024];
        int len;
        while((len = r.read(b)) != -1){
            w.write(b, 0, len);
        }
        w.close();
        r.close();
    }
}
