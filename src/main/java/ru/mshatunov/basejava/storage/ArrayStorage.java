package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.model.Resume;

import java.util.Arrays;

public class ArrayStorage {

    private static final int MAX_STORAGE_SIZE = 10000;
    private Resume[] storage = new Resume[MAX_STORAGE_SIZE];
    private int storageSize;

    public void save(Resume r) {

        if (storageSize == MAX_STORAGE_SIZE) {
            System.out.println("ERROR: Maximum storage size reached");
            return;
        }

        if (getIndex(r.getUuid()) >= 0) {
            System.out.println("ERROR: Resume already exists");
            return;
        } else {
            storage[storageSize] = r;
            storageSize++;
        }

    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("ERROR: Resume doesn't exist");
            return;
        }
    }

    public void delete(String uuid) {

        int index = getIndex(uuid);
        if (getIndex(uuid) >= 0) {
            storage[index] = storage[storageSize - 1];
            storage[storageSize - 1] = null;
            storageSize--;
            return;
        } else {
            System.out.println("ERROR: Resume doesn't exist");
            return;
        }

    }

    public void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    public Resume get(String uuid) {

        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            return null;
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, storageSize);
    }

    public int size() {
        return storageSize;
    }

    private int getIndex(String uuid) {
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