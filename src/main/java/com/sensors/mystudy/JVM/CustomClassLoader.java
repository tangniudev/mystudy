package com.sensors.mystudy.JVM;

import java.io.FileNotFoundException;

public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] result = getclassFromCustomPath(name);
        if (result == null){
            try {
                throw  new FileNotFoundException();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            return defineClass(name,result,0,result.length);
        }
        return super.findClass(name);
    }

    private byte[] getclassFromCustomPath(String name) {
        return null;
    }
}
