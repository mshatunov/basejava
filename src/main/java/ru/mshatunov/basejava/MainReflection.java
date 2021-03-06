package ru.mshatunov.basejava;

import ru.mshatunov.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Resume r = new Resume("Name");
        Class resumeClass = r.getClass();

        Field field = resumeClass.getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "uuid1");
        System.out.println(field.get(r));

        Method method = resumeClass.getMethod("toString");
        System.out.print("Reflection toString: ");
        System.out.println(method.invoke(r));

    }

}
