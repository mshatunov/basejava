package ru.mshatunov.basejava;

import ru.mshatunov.basejava.model.Resume;

import java.lang.reflect.Field;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException {

        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "uuid1");
        //TODO invoke r.toString via reflection
        System.out.println(field.get(r));
    }

}