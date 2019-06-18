### 一、概述    

### 二、基本使用
#### 2.1、字节输出流（OutputStream）[从内存向文件输出]
##### 2.1.1、基本定义
    字节输出流主要以操作byte数据为主，观察java.io.OutputStream类的定义结构： 
    public abstract class OutputStream implements Closeable, Flushable 
        OutputStream类实现了Closeable，Flushable两个接口，这两个接口中的方法： 
             1. Closeable: public void close() throws IOException; 
             2. Flushable: public void flush() throws IOException;
##### 2.1.2、重要方法
      1. 将给定的字节数组内容全部输出：public void write(byte b[]) throws IOException 
      2. 将部分字节数组内容输出：public void write(byte b[], int off, int len) throws IOException(重点) 
      3. 输出单个字节：public abstract void write(int b) throws IOException

    是一个抽象类，按照抽象类的基本原则来讲，如果想要取得OutputStream类的实例化对象那么一定需要子类，如果要进行文件的操作，可以使用FileOutputStream类来处理，

###### 1、FileOutputStream
    参看 FileOutputStreamDemo

#### 2.2、字节输入流（InputStream）[从文件向内存输入]
##### 2.2.1、基本定义
    字节输入流主要以操作byte数据为主，实现数据的读取操作，观察java.io.InputStream 类的定义结构 
    public abstract class InputStream implements Closeable 
##### 2.2.2、重要方法
      1. 读取数据到字节数组中：public int read(byte b[]) throws IOException.最常用方法
        每次讲数据读取到数组之中，那么会返回一个读取长度的数据，如果没有数据则返回的长度为-1，可是要考虑两种情况： 
            要读取的内容大于开辟的数组内容：长度就是整个数组的长度。 
            要读取的内容小于开辟数组的内容， 
        长度就是全部最后的内容长度，数组装不满。
      2. 读取部分内容到字节数组中：public int read(byte b[], int off,int len) throws IOException 
        每次读取内容到部分字节数组，只允许读取满限制的数组的字节个数。此方法依然会返回读取的长度。
      3. 读取单个字节：public abstract int read() throws IOException; 
            每次执行此方法将读取当个字节数据，如果已经读取完成了，那么最后返回-1。

    是一个抽象类，按照抽象类的基本原则来讲，如果想要取得InputStream类的实例化对象那么一定需要子类，如果要进行文件的操作，可以使用FileInputStream类来处理，

###### 1、FileInputStream
    byte data[] = new byte[1024];
    注意默认只能读取数组大小的数据。
    参看demo：FileInputStreamDemo
    单个读取：FileInputStreamSingleDemo
        
        
#### 2.3、字符输出流（Writer）[从文件向内存输入]
##### 2.3.1、基本定义
    Writer是进行字符输出操作使用的类，这个类属于抽象类 
    public abstract class Writer implements Appendable, Closeable, Flushable
##### 2.3.2、重要方法：
      1. 直接输出字符串：public void write(String str) throws IOException

    Writer是一个抽象类，要进行文件字符流操作

###### 1、FileWriter
    代码：FileWriterDemo
    虽然Writer类提供有字符数组的输出操作能力，但是从本质上来讲使用Writer类就意味着要执行字符串的直接输出。
    字符流最适合操作中文，但并不意味着字节流就无法操作中文。      
    
#### 2.4、字符输入流（Reader）[从文件向内存输入]
##### 2.4.1、基本定义
    Writer是进行字符输出操作使用的类，这个类属于抽象类 
    public abstract class Reader extends Object implements Readable,Closeable
##### 2.4.2、重要方法：
      1. 读取字符串：public int read(char[] cbuf) throws IOException

    是一个抽象类，要进行文件字符流操作

###### 1、FileReader
    代码：FileReaderDemo 