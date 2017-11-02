package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.model.Resume;

import java.util.List;

public interface Storage {

    void save(Resume r);

    void update(Resume r);

    void delete(String uuid);

    void clear();

    Resume get(String uuid);

    int size();

}
