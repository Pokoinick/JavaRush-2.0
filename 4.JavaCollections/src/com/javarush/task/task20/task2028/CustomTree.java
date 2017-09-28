package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root = new Entry<>("0");
    Entry<String> lastAdded;

    @Override
    public boolean add(String s) {
        Entry<String> addition = new Entry<>(s);
        Entry<String> parent = walkWithCustomTree(root);
        if (parent != null) {
            addition.parent = parent;
            addition.lineNumber = parent.lineNumber + 1;
            if (parent.availableToAddLeftChildren)
                parent.leftChild = addition;
            else if (parent.availableToAddRightChildren)
                parent.rightChild = addition;
            lastAdded = addition;
            return true;
        }

        return false;
    }

    private Entry<String> walkWithCustomTree(Entry localRoot) {

        Queue<Entry<String>> queue = new LinkedList<>();

        do {
            if (localRoot.isAvailableToAddChildren()) {
                return localRoot;
            }
            if (localRoot.leftChild != null)
                queue.add(localRoot.leftChild);
            if (localRoot.rightChild != null)
                queue.add(localRoot.rightChild);
            if (!queue.isEmpty())
                localRoot = queue.poll();
        } while (!queue.isEmpty());

        return null;
    }

    public boolean remove(Object o) {
        Queue<Entry<String>> queue = new LinkedList<>();
        Entry<String> localRoot = root;
        String compare = (String) o;
        queue.add(root);

        while (!queue.isEmpty()){
            if (localRoot.elementName.equals(compare)) {

                if (localRoot.elementName.equals(localRoot.parent.leftChild.elementName)) {
                    localRoot.parent.leftChild = null;
                    localRoot.parent.availableToAddLeftChildren = true;
                }
                else {
                    localRoot.parent.rightChild = null;
                    localRoot.parent.availableToAddRightChildren = true;
                }

                return true;
            }
            if (localRoot.leftChild != null)
                queue.add(localRoot.leftChild);
            if (localRoot.rightChild != null)
                queue.add(localRoot.rightChild);
            if (!queue.isEmpty())
                localRoot = queue.poll();
        }

        return false;
    }

    @Override
    public int size() {
        Queue<Entry<String>> queue = new LinkedList<>();
        Entry<String> localRoot = root;
        queue.add(root);
        int localSize = 0;

        while (!queue.isEmpty()) {
            if (localRoot.leftChild != null) {
                queue.add(localRoot.leftChild);
                localSize++;
            }
            if (localRoot.rightChild != null) {
                queue.add(localRoot.rightChild);
                localSize++;
            }
            if (!queue.isEmpty())
                localRoot = queue.poll();
        }
        return localSize / 2;
    }

    public String getParent(String s) {
        Queue<Entry<String>> queue = new LinkedList<>();
        Entry<String> localRoot = root;

        if (lastAdded.elementName.equals(s))
            return lastAdded.parent.elementName;

        do {
            if (localRoot.elementName.equals(s)) {
                return localRoot.parent.elementName;
            }
            if (localRoot.leftChild != null)
                queue.add(localRoot.leftChild);
            if (localRoot.rightChild != null)
                queue.add(localRoot.rightChild);
            if (!queue.isEmpty())
                localRoot = queue.poll();
        } while (!queue.isEmpty());

        return "null";
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry (String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public void checkChildren() {
            if (leftChild != null)
                availableToAddLeftChildren = false;
            else
                availableToAddLeftChildren = true;
            if (rightChild != null)
                availableToAddRightChildren = false;
            else
                availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            checkChildren();
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }

    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
        list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println(list);
        System.out.println("size:" + list.size() + "; removing 14: " + list.remove("14") + "; size after:" + list.size());
        list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("size:" + list.size() + "; removing 2: " + list.remove("2") + "; size after:" + list.size());
        System.out.println("size:" + list.size() + "; removing 2: " + list.remove("5") + "; size after:" + list.size());
        list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        list.remove("3");
        ((CustomTree) list).add("16");
        System.out.println(list);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 1; i < this.size(); i++) {
            Queue<Entry<String>> queue = new LinkedList<>();
            queue.add(root);
            boolean flag = true;
            while (queue.size() != 0) {
                Entry<String> currentElement = queue.remove();
                if (currentElement.lineNumber == i) {
                    if (flag) {
                        s += i + ":";
                    }
                    s += currentElement.elementName + ",";
                    flag = false;
                }
                if (currentElement.leftChild != null) {
                    queue.add(currentElement.leftChild);
                }
                if (currentElement.rightChild != null) {
                    queue.add(currentElement.rightChild);
                }
            }
            if (flag) { break; }
            s += "\r\n";
        }
        return s;
    }
}
