package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Станислав on 17.07.2017.
 */
public class AmigoSet <E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {

    private final static Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<>(Math.max(16, (int) (Math.ceil(collection.size()/0.75f))));
        this.addAll(collection);
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    public boolean add(E e) {
        Object prev = map.put(e, PRESENT);
        if (prev == null)
            return true;
        else
            return false;

    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }



    @Override
    public Object clone() {
        try {
            AmigoSet amigoSet = new AmigoSet<>();
            amigoSet.addAll(this);
            amigoSet.map.putAll((Map) this.map.clone());
            return amigoSet;
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }

    @Override
    public boolean contains(Object o) {
        return map.keySet().contains(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return map.keySet().remove(o);
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }
}
