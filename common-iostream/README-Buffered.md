### 一、概述    
    BuffereInputStream（带有缓冲区的字节输入流）继承于FilterInputStream，而FilterInputStream继承于InputStream，
    BuffereOutputStream（带有缓冲区的字节输出流）继承于FilterOutputStream，而FilterOutputStream继承于OutputStream，
    关于FilterInputStream和FilterOutputStream，其实它们只是个“装饰器模式”的封装，也就是说它并没有给出具体的功能实现，它具体的功能实现都是通过它的子类来实现的。
    
    这两个其实主要就是一个缓冲的作用，我们知道，如果直接让文件或程序跟内存进行交互，效率是十分低下的，而通过缓冲流进行交互，能够大大提高效率，缓冲流的主要作用就是提高了效率。

    对于文件的存在与否的反应，和其它一样，不存在时，输入流会报错，输出流会自动创建。
    
    
    BuffereReader继承于Reader，BufferedWriter继承于Writer，是字符缓冲流。
### 二、基本使用
