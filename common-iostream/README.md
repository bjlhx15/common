### 一、概述
    IO流用来处理设备之间的数据传输，上传文件和下载文件，Java对数据的操作是通过流的方式，Java用于操作流的对象都在IO包中。  
    流是一组有顺序的，有起点和终点的字节集合，是对数据传输的总称或抽象。即数据在两设备间的传输称为流，流的本质是数据传输，根据数据传输特性将流抽象为各种类，方便更直观的进行数据操作。
#### 1.1、IO流分类
    Java中按照流的不同方向分为输入流和输出流，凡是含有in的，都可理解为输入流，输入流即从文件读取到程序，凡是含有out的，都可以理解为输出流，输出流即从程序输出到文件。
    
    按照数据流向：输入流 读入数据、输出流 写出数据
    按照实现功能不同可以分为：节点流和处理流。
    按照数据类型【单位分】
        字节流：InputStream（字节输入流）、OutputStream（字节输出流）；
        字符流：Reader（字符输入流）、Writer（字符输出流）；因为数据编码的不同，而有了对字符进行高效操作的流对象。本质其实就是基于字节流读取时，去查了指定的码表。
        
    字节流与字符流操作的本质区别只有一个：字节流是原生的操作，而字符流是经过处理后的操作。
        在进行网络数据传输、磁盘数据保存所保存所支持的数据类型只有：字节。
        而所有磁盘中的数据必须先读取到内存后才能进行操作，而内存中会帮助我们把字节变为字符。字符更加适合处理中文。
        读写单位不同：字节流以字节（8bit）为单位，字符流以字符为单位，根据码表映射字符，一次可能读多个字节。
        处理对象不同：字节流能处理所有类型的数据（如图片、avi等），而字符流只能处理字符类型的数据。
        字节流在操作的时候本身是不会用到缓冲区的，是文件本身的直接操作的；而字符流在操作的时候下后是会用到缓冲区的，是通过缓冲区来操作文件，
        结论：优先选用字节流。首先因为硬盘上的所有文件都是以字节的形式进行传输或者保存的，包括图片等内容。
        
    首先必须要明确一点，通过任何终端（网络、文件）读取或者输出的数据都一定是字节，字符是经过内存处理后的数据。
        字符输入：字节（磁盘）–> 自动转换为 –>字符（内存）；
        字符输出：字符（内存）–> 自动转换为–>字节（磁盘）；
      在利用字符流输出的时候，所有的内容实际上都只是输出到了缓冲区中（内存）。在使用close()方法关闭的时候会将我们缓冲区的数据进行输出，如果没有关闭，那么就将无法进行输出，此时可以利用flush()方法进行强制的输出。
      字符使用到了缓冲区，而字节流没有使用到缓冲区。
      如果处理中文使用字符流，其他的任何数据都使用字节流。        
        
#### 1.2、操作流程
    1、创建File类对象 ，主要是指明要操作的文件路径；
    2、根据字节流或字符流的子类实例化父类对象 ；
    3、进行数据的读取或写入操作；
    4、关闭流(close())；
    注意：对于IO操作属于资源处理，所有的资源处理操作(IO操作、数据库操作、网络)最后必须要进行关闭。

### 二、基本使用
字节流（读取-二进制文件。写入、拷贝-文本、二进制文件）中包括：

    InputStream--字节输入流的基类（抽象类）
    OutputStream--字节输出流的基类（抽象类）
    
    FileInputStream--文件字节输入流【节点流】
    FileOutputStream--文件字节输出流【节点流】
    
    FilterInputStream--过滤器字节输入流
    FilterOutputStream--过滤器字节输出流
    
    BufferedInputStream--带有缓冲区字节输入流【处理流】
    BufferedOutputStream--带有缓冲区字节输出流【处理流】
    
字符流（读取、写入、拷贝文本）包括：
    
    Reader--字符输入流（抽象类）
    Writer--字符输出流（抽象类）
    
    InputStreamReader--字符输入转换流   将字节流转化为字符流
    OutputStreamWriter--字符输出转换流  将字符流转化为字节流。
    
    FileReader--文件字符输入流
    FileWriter--文件字符输出流
    
    BufferedReader--带有缓冲区字符输入流
    BufferedWriter--带有缓冲区字符输出流
    
    缓冲区默认大小是8192个字符，即1024*8

其余的各种流：

    对象流：（在文件中存储对象）
        ObjectInputStream--对象输入流【处理流】
        ObjectOutputStream--对象输出流【处理流】

        要想类对象存入文件，此类必须实现序列化接口，即实现Serializable，这是一个标记型接口，接口中没有要实现的方法，序列化的过程是从程序到文件，而反序列化是从文件到程序。

    打印流：
        PrintStream：可以将字节流封装成打印流
        PrintWriter：可以将字节流、字符流封装成打印流

        可以用其进行重定向操作，即重新定义系统标准输入、输出流的方向

    随机访问流：RandomAccessFile
        有两种模式： r--读取  rw--读写

    内存流：
        ByteArrayInputStream--内存输入流【节点流】
        ByteArrayOutoutStream--内存输出流【节点流】
        内存流是关不掉的

    NIO-new io：面向缓冲区的IO流
        Java NIO 由以下几个核心部分组成：
            Buffers--缓冲区：针对系统的缓冲区
            Channels--通道：类似于BIO里面的流

    Selectors--选择器

小结
    1.Java IO是采用的是装饰模式，即采用处理流来包装节点流的方式，来达到代码通用性。
    
    2.处理流和节点流的区分方法，节点流在新建时需要一个数据源（文件、网络）作为参数，而处理流需要一个节点流作为参数。
    
    3.处理流的作用就是提高代码通用性，编写代码的便捷性，提高性能。
    
    4.节点流都是对应抽象基类的实现类，它们都实现了抽象基类的基础读写方法。其中read（）方法如果返回-1，代表已经读到数据源末尾。
    

分类	字节输入流	字节输出流	字符输入流	字符输出流
抽象基类	InputStream	OutputStream	Reader	Writer
访问文件	FileInputStream	FileOutputStream	FileReader	FileWriter
访问数组	ByteArrayInputStream	ByteArrayOutputStream	CharArrayReader	CharArrayWriter
访问管道	PipedInputStream	PipedOutputStream	PipedReader	PipedWriter
访问字符串	 	 	StringReader	StringWriter
缓冲流	BufferedInputStream	BufferedOutputStream	BufferedReader	BufferedWriter
转换流	 	 	InputStreamReader	OutputStreamWriter
对象流	ObjectInputStream	ObjectOutputStream	 	 
抽象基类	FilterInputStream	FilterOutputStream	FilterReader	FilterWriter
打印流	 	PrintStream	 	PrintWriter
推回输入流	PushbackInputStream	 	PushbackReader	 
特殊流	DataInputStream	DataOutputStream	 	     
