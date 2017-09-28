package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    private transient FileOutputStream stream;
    String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.fileName = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
         in.defaultReadObject();
         this.stream = new FileOutputStream(fileName, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws Exception {

        Solution solution = new Solution("D:\\first1.txt");
        solution.writeObject("Первое");
        System.out.println(solution.stream);


        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\first.txt"));
        oos.writeObject(solution);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\first.txt"));
        Solution solution_loaded = (Solution) ois.readObject();
        solution_loaded.writeObject("Second");
        System.out.println(solution_loaded.fileName);
    }
}
