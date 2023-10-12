package com.sensors.mystudy.JVM;

import sun.misc.Launcher;
import sun.security.ec.CurveDB;

import java.net.URL;
import java.security.Provider;

public class ClassLoaderTest1 {

    public static void main(String[] args) {
        System.out.println("启动类加载器");
         URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
         for (URL url: urLs){
             System.out.println(url.toExternalForm());
         }
        //从上面的jar里面随意选择一个类，看看他的加载器是什么类加载器
         ClassLoader classLoader = Provider.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println("扩展类加载器");
        //扩展类加载器
        final String extDirs = System.getProperty("java.ext.dirs");
        for (String path : extDirs.split(";")){
            System.out.println(path);
        }

        //从上面的jar里面随意选择一个类，看看他的加载器是什么类加载器
        ClassLoader classLoader1 = CurveDB.class.getClassLoader();
        System.out.println(classLoader1);//sun.misc.Launcher$ExtClassLoader@36baf30c
    }
}
