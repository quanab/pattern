package com.example.singleton;

import java.io.Serializable;
import java.util.Objects;

public class Singleton implements Serializable {

    private volatile static Singleton singleton;

    /**
     * 私有类型的构造函数
     */
    private Singleton() {
        super();
//        抛异常制止破坏单例模式
//        this.prevent();
    }

    /**
     * 使用双重校验锁方式实现单例
     *
     * @return Singleton
     */
    public static Singleton getInstance() {
        if (Objects.isNull(singleton)) {
            synchronized (Singleton.class) {
                if (Objects.isNull(singleton)) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    /**
     * 抛异常制止破坏单例模式
     */
    private void prevent() {
        if(Objects.isNull(singleton)) {
            throw new RuntimeException("禁止破坏单例模式");
        }
    }

//    /**
//     * 防止反序列破坏单例模式
//     *  反序列化执行过程中会执行到 ObjectInputStream.readOrdinaryObject()，
//     *  这个方法会判断对象是否包含 readResolve()，如果包含的话会直接调用这个方法获得对象实例
//     * @return Object
//     */
//    private Object readResolve() {
//        return getInstance();
//    }
}
