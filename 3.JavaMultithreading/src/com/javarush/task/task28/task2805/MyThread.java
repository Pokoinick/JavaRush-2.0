package com.javarush.task.task28.task2805;

/**
 * Created by Станислав on 28.05.2017.
 */
public class MyThread extends Thread {
    private static int priority = 0;
    public MyThread() {
       this.setPriority(priority());
    }

    public MyThread(Runnable target) {
        super(target);
        this.setPriority(priority());
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        this.setPriority(priority());
    }

    public MyThread(String name) {
        super(name);
        this.setPriority(priority());
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        this.setPriority( priority() > group.getMaxPriority() ? group.getMaxPriority() : priority);
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        this.setPriority(priority());
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        this.setPriority( priority() > group.getMaxPriority() ? group.getMaxPriority() : priority);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        this.setPriority( priority() > group.getMaxPriority() ? group.getMaxPriority() : priority);
    }

    private int priority(){
        return priority < Thread.MAX_PRIORITY ? ++priority : (priority = 1);
    }
}
