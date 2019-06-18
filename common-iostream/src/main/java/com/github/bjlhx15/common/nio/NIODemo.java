package com.github.bjlhx15.common.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NIODemo {

    /**
     * 进行Buffers的测试，值得注意的是在需要获取数据的时候，需要使用flip（）方法将指针反转，即归零操作
     */
    @Test
    public void t1(){
        //创建缓冲区
        ByteBuffer bb = ByteBuffer.allocate(10);
        //获取此缓冲区的指针位置
        System.out.println("指针的位置:" + bb.position());
        //获取此缓冲区的限制位置
        System.out.println("限制的位置:" + bb.limit());
        //获取此缓冲区的容量
        System.out.println("缓冲区的容量:" + bb.capacity());
        //获取此缓冲区指针到限制之间的元素数
        System.out.println("指针到限制之间的元素数:" + bb.remaining());
        //存储元素
        bb.put("abcd".getBytes());
        System.out.println("------------------------");
        //获取此缓冲区的指针位置
        System.out.println("指针的位置:" + bb.position());
        //获取此缓冲区的限制位置
        System.out.println("限制的位置:" + bb.limit());
        //获取此缓冲区的容量
        System.out.println("缓冲区的容量:" + bb.capacity());
        //获取此缓冲区指针到限制之间的元素数
        System.out.println("指针到限制之间的元素数:" + bb.remaining());

        System.out.println("------------------------");
        //获取数据，在获取之前，要先反转指针，让指针归零，否则无法获取
        bb.flip();
        //获取此缓冲区的指针位置
        System.out.println("指针的位置:" + bb.position());
        //获取此缓冲区的限制位置
        System.out.println("限制的位置:" + bb.limit());
        //获取此缓冲区的容量
        System.out.println("缓冲区的容量:" + bb.capacity());
        //获取此缓冲区指针到限制之间的元素数
        System.out.println("指针到限制之间的元素数:" + bb.remaining());
        System.out.println("------------------------");
        for(int i = 0;i < bb.remaining();i++){
            System.out.print((char)bb.get(i));
        }
    }

    /**
     * 进行文件的拷贝,直接将整个文件一次性读入缓冲区，然后直接一次性将文件从缓冲区写出来
     * @throws Exception
     */
    @Test
    public void t2() throws Exception{

        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : NIODemo.class.getClass().getResource("/").getPath();
        String filepath=path+"NIODemo.txt";
        String copyfilepath=path+"NIODemo--2.txt";

        File file = new File(filepath);
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(copyfilepath);

        //获取通道
        FileChannel rChannel = fis.getChannel();
        FileChannel wChannel = fos.getChannel();

        //创建缓冲区
        ByteBuffer bb = ByteBuffer.allocate((int) file.length());
        rChannel.read(bb);
        bb.flip();
        wChannel.write(bb);
        fos.close();
        fis.close();
    }

    /**
     * 进行文件的拷贝，1024个字节的读和1024个字节的写
     * @throws Exception
     */
    @Test
    public void t3() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : NIODemo.class.getClass().getResource("/").getPath();
        String filepath=path+"NIODemo.txt";
        String copyfilepath=path+"NIODemo--2.txt";
        File file = new File(filepath);
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(copyfilepath);

        //获取通道
        FileChannel rChannel = fis.getChannel();
        FileChannel wChannel = fos.getChannel();

        //创建缓冲区，大小为1024个字节
        ByteBuffer bb = ByteBuffer.allocate(1024);
        while(rChannel.read(bb) != -1){
            bb.flip();
            wChannel.write(bb);
            bb.clear();
        }

        fos.close();
        fis.close();
    }

    /**
     * 进行文件拷贝，使用带有映射关系的MappedByteBuffer进行拷贝
     * @throws Exception
     */
    @Test
    public void t4() throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : NIODemo.class.getClass().getResource("/").getPath();
        String filepath=path+"NIODemo.txt";
        String copyfilepath=path+"NIODemo--2.txt";

        File file = new File(filepath);
        long length = file.length();
        RandomAccessFile rraf = new RandomAccessFile(file, "r");
        RandomAccessFile wraf = new RandomAccessFile(copyfilepath, "rw");

        //获取通道
        FileChannel rChannel = rraf.getChannel();
        FileChannel wChannel = wraf.getChannel();

        //创建带有映射关系的缓冲区
        MappedByteBuffer rmbb = rChannel.map(FileChannel.MapMode.READ_ONLY, 0, length);
        MappedByteBuffer wmbb = wChannel.map(FileChannel.MapMode.READ_WRITE, 0, length);

        for (int i = 0; i < length; i++) {
            byte value = rmbb.get(i);
            wmbb.put(value);
        }
        rraf.close();
        wraf.close();

    }
}
