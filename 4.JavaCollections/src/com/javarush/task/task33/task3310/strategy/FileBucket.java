package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Станислав on 07.08.2017.
 */
public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (Exception e) {
            System.out.println("File bucket error!");
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (Exception e) {
            System.out.println("Size error!");
            return 0;
        }
    }

    public void putEntry(Entry entry) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            objectOutputStream.writeObject(entry);
        } catch (Exception e) {
            System.out.println("Error in put Entry!");
        }
    }

    public Entry getEntry() {
        if (getFileSize() == 0)
            return null;

        Entry entry = null;

        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path))) {
            entry =  (Entry) objectInputStream.readObject();
        } catch (Exception e) {
            System.out.println("Error in read Entry!");
        }
        return entry;
    }

    public void remove () {
        try {
            Files.delete(path);
        } catch (Exception e) {
            System.out.println("Error on delete!");
        }
    }
}
