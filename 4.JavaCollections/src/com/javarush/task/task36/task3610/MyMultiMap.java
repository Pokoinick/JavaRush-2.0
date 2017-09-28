package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        int size = 0;

        return values().size();
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код
        List<V> list = map.get(key);
        if (list == null) {
            List<V> list1 = new ArrayList<>();
            list1.add(value);
            map.put(key, list1);
            return null;
        }
        else if (list.size() < repeatCount){
            list.add(value);
            return list.get(list.indexOf(value)-1);
        }
        else {
            list.remove(0);
            list.add(value);
            return list.get(list.indexOf(value)-1);
        }
    }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
        List<V> list = map.get(key);
        if (list == null) {
            return null;
        }
        else {
           Object o = list.get(0);
            list.remove(0);
            if (list.size() == 0)
                map.remove(key);
            return (V)o;
        }

    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        List<V> allList = new ArrayList<>();
        for (List<V> innerList : map.values()) {
            allList.addAll(innerList);
        }
        return allList;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        for (V innerValue: values()) {
            if (innerValue.equals(value))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}