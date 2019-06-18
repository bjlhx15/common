package com.github.bjlhx15.common.iostream;

import org.junit.Test;

import java.io.*;
import java.util.Vector;

/**
 * SequenceInputStream 表示其他输入流的逻辑串联。
 * 它从输入流的有序集合开始，并从第一个输入流开始读取，直到到达文件末尾；
 * 接着从第二个输入流读取，依次类推，直到到达包含的最后一个输入流的文件末尾为止。
 */

public class SequenceInputStreamDemo {
    // SequenceInputStream(InputStream s1, InputStream s2)

    @Test
    public void t1() throws Exception {

        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : BufferedStreamDemo.class.getClass().getResource("/").getPath();
        String filepath = path + "SequenceInputStreamDemo1.txt";
        String copyfilepath = path + "SequenceInputStreamDemo2.txt";

        InputStream s1 = new FileInputStream(filepath);
        InputStream s2 = new FileInputStream(copyfilepath);

        SequenceInputStream sis = new SequenceInputStream(s1, s2);

        InputStreamReader isr = new InputStreamReader(sis);
        BufferedReader br = new BufferedReader(isr);

        BufferedWriter bw = new BufferedWriter(new FileWriter(path + "SequenceInputStreamDemo--1.txt"));

        String line = null;
        while ((line = br.readLine()) != null) {
            // while(br.ready()) {  /* 为什么合并流之后，不能使用ready方法？结果只有S1 */
            // line = br.readLine();
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        s1.close();
        s2.close();
        br.close();
        bw.close();
    }

    @Test
    public void t2() throws Exception {

        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : BufferedStreamDemo.class.getClass().getResource("/").getPath();
        String filepath = path + "SequenceInputStreamDemo1.txt";
        String copyfilepath = path + "SequenceInputStreamDemo2.txt";

        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new SequenceInputStream(
                                new FileInputStream(filepath),
                                new FileInputStream(copyfilepath)
                        )
                )
        );
        BufferedWriter bw = new BufferedWriter(new FileWriter(path + "SequenceInputStreamDemo---2.txt"));
        String line = null;
        while ((line = br.readLine()) != null) {
            // while(br.ready()) {  /* 为什么合并流之后，不能使用ready方法？结果只有S1 */
            // line = br.readLine();
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        br.close();
        bw.close();

    }

    /**
     * // SequenceInputStream(Enumeration<? extends InputStream> e)
     * 	// 通过枚举类：进行多个流的合并
     * @throws Exception
     */
    @Test
    public void t3() throws Exception {

        String path = Thread.currentThread().getContextClassLoader().getResource("/") != null
                ? Thread.currentThread().getContextClassLoader().getResource("/").getPath()
                : BufferedStreamDemo.class.getClass().getResource("/").getPath();
        String filepath1 = path + "SequenceInputStreamDemo1.txt";
        String filepath2 = path + "SequenceInputStreamDemo2.txt";
        String filepath3 = path + "SequenceInputStreamDemo3.txt";
        InputStream s1 = new FileInputStream(filepath1);
        InputStream s2 = new FileInputStream(filepath2);
        InputStream s3 = new FileInputStream(filepath3);

        Vector<InputStream> v = new Vector<InputStream>();
        v.addElement(s1);
        v.addElement(s2);
        v.addElement(s3);
        SequenceInputStream sis = new SequenceInputStream(v.elements());

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path+"SequenceInputStreamDemo-----3.txt"));

        byte[] buf = new byte[1024];
        int len = buf.length;
        while((len = sis.read(buf, 0, len)) != -1) {
            bos.write(buf, 0, len);
        }

        sis.close();
        bos.close();
    }
}
