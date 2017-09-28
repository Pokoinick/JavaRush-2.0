package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Станислав on 11.07.2017.
 */
public class CustomInvocationHandler implements InvocationHandler {
    private SomeInterfaceWithMethods interfaceWithMethods;

    public CustomInvocationHandler (SomeInterfaceWithMethods interfaceWithMethods) {
        this.interfaceWithMethods = interfaceWithMethods;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + " in");
        Object obj = method.invoke(interfaceWithMethods, args);
        System.out.println(method.getName() + " out");
        return obj;
    }
}
