package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        try {
            File[] files = new File(URLDecoder.decode(packageName, "UTF-8")).listFiles();
            for (File f : files) {
                if (f.getName().endsWith(".class")) {
                    Class clazz = new ClassFromPath().load(f.toPath(), Solution.class.getPackage().getName() + ".data.second");
                    hiddenClasses.add(clazz);
                }
            }
        } catch (Exception e) {
        }

    }
    public static class ClassFromPath extends ClassLoader {
        public Class<?> load(Path path, String packageName) {
            try {
                String className = packageName + "." + path.getFileName().toString().replace(".class", "");
                byte[] b = Files.readAllBytes(path);
                return defineClass(className, b, 0, b.length); //here main magic
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class clazz : hiddenClasses) {
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                Constructor[] constructors = clazz.getDeclaredConstructors();
                for (Constructor c :constructors) {
                    if (c.getParameterCount() == 0) {
                        c.setAccessible(true);
                        try {
                            return (HiddenClass) c.newInstance();
                        } catch (Exception e) {
                            System.out.println("oups!");
                        }
                    }
                }
            }
        }
        return null;
    }
}

