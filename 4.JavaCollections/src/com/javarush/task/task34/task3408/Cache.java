package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        if (!cache.containsKey(key)) {
            Constructor v = clazz.getConstructor(key.getClass());
            v.setAccessible(true);
            cache.put(key, (V)v.newInstance(key));
            return (V)v.newInstance(key);
        }
        else
            return cache.get(key);
    }

    public boolean put(V obj) {
        //TODO add your code here
        try {
            Method getKey = obj.getClass().getDeclaredMethod("getKey");
            getKey.setAccessible(true);
            K key = (K) getKey.invoke(obj);
            cache.put(key, obj);
            return true;
        } catch (Exception e) {
            System.out.println("No such method!");
            return false;
        }
    }

    public int size() {
        return cache.size();
    }
}
