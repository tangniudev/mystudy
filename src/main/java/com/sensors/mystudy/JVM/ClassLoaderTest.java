package com.sensors.mystudy.JVM;

public class ClassLoaderTest {

    public static void main(String[] args) {
        //系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2


        //扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);//sun.misc.Launcher$ExtClassLoader@3cd1a2f1



        //获取上层，获取不到引导类加载器
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);//null


        //用户自定义类,默认使用系统类加载器
         ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

        //String类使用引导类加载器进行加载 ----》java的核心类库都是使用引导类加载器进行加载的
         ClassLoader stringclassLoader = String.class.getClassLoader();
        System.out.println(stringclassLoader);//null
    }
}
