package ru.mshatunov.basejava.storage;

public class ArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            } else if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}