package com.example.damage;

import com.example.singleton.Singleton;

import java.io.*;

/**
 * 序列化+反序列化破坏单例模式
 *  反序列化使用的反射构造器和我们代码中使用反射的构造器不是同一个，反序列化用到的构造器并不会调用到我们对象中的构造函数
 */
public class Serialization {

    public static final String pathname = "E:/Projects/Java/Example/pattern/singleton-pattern/singleton";

    /**
     * 破坏单例
     * 先将单例对象序列化后保存到临时文件中，然后再从临时文件中反序列化出来
     */
    public static void damage() {
        Singleton singleton = Singleton.getInstance();

        File file = new File(pathname);
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file))) {
            //Write Obj to file
            output.writeObject(singleton);

            //Read Obj from file
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
            Singleton serialize = (Singleton) input.readObject();

            //判断是否是同一个对象
            System.out.println("singleton : " + singleton);
            System.out.println("serialize : " + serialize);
            System.out.println("singleton == serialize : " + (singleton == serialize));

            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
