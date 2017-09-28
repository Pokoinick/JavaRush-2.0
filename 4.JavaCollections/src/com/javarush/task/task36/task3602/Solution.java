package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;


/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() throws ClassNotFoundException {
        Class[] classes = Class.forName("java.util.Collections").getDeclaredClasses();
        Class resulst = null;
        for (Class clazz : classes) {
            int mods  = clazz.getModifiers();

            if (!List.class.isAssignableFrom(clazz)){
                continue;
            }
            if (!Modifier.isStatic(mods)) {
                continue;
            }
            if (!Modifier.isPrivate(mods)) {
                continue;
            }
            try {
                Constructor constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                Object o = constructor.newInstance();
                for (Method method : clazz.getDeclaredMethods()) {
                    method.setAccessible(true);
                    method.invoke(o, 1);
                }
                System.out.println(clazz);
            } catch (InvocationTargetException e ) {
                if (e.getCause().equals(new IndexOutOfBoundsException()));
                    resulst = clazz;
            } catch (Exception e) {

            }
        }
        return resulst;
    }
}
