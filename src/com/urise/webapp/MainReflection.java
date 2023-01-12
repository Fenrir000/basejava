package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("ui");
        Class cl = r.getClass();
        Method toString = cl.getMethod("toString");
        System.out.println(toString.invoke(r));

    }
}
