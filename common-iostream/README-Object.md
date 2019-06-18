### 一、概述    
    ObjectInputStream（对象输入流）继承于InputStream，ObjectOutputStream（对象输出流）继承于OutputStream。
    对象流是将对象的基本数据和图形实现持久存储。ObjectOutputStream实际是在对流进行序列化操作，ObjectInputStream实际是在对流进行反序列化操作，
    要实现序列化，必须实现Serializable接口，否则是无法进行序列化和反序列化的，如果对象中的属性加了transient和static关键字的话，则该属性不会被序列化。
    
    常用的方法是：readObject（），从ObjectInputStream读取对象；writeObject（Object obj），将指定的对象写入ObjecctOutputStream；close（），关闭该流
   
### 二、基本使用
