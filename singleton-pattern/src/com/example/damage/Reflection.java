package com.example.damage;

import com.example.singleton.Singleton;

import java.lang.reflect.Constructor;

/**
 * 反射破坏单例模式
 */
public class Reflection {

    /**
     * 破坏单例
     */
    public static void damage() {
        Singleton singleton = Singleton.getInstance();
        try {
            Class<Singleton> clazz = (Class<Singleton>) Class.forName(Singleton.class.getName());
//            通过反射的方式获取构造函数
            Constructor<Singleton> constructor = clazz.getDeclaredConstructor(null);
//            设置其访问权限
            constructor.setAccessible(true);
//            创建一个新的对象
            Singleton reflect = constructor.newInstance();

            System.out.println("singleton : " + singleton);
            System.out.println("reflect : " + reflect);
            System.out.println("singleton == reflect : " + (singleton == reflect));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
