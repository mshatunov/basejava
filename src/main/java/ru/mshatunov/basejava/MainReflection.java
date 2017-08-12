package ru.mshatunov.basejava;

import ru.mshatunov.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException {

        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "uuid1");
        Method[] methods = r.getClass().getMethods();
        for (Method m : methods) {
            if (m.getName().equals("toString")) {
                try {
                    System.out.print("Reflection toString: ");
                    System.out.println(m.invoke(r));
                    break;
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(field.get(r));
    }

}
