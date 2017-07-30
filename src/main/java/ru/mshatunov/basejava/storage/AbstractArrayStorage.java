package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.model.Resume;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int MAX_STORAGE_SIZE = 10000;

    protected Resume[] storage = new Resume[MAX_STORAGE_SIZE];
    protected int storageSize;

    public int size() {
        return storageSize;
    }

    public Resume get(String uuid) {

        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            return null;
        }

    }

    protected abstract int getIndex(String uuid);

}
