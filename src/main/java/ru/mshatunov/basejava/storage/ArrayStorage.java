package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.model.Resume;

import java.util.Arrays;

public class ArrayStorage {

    private static final int MAX_STORAGE_SIZE = 10000;
    private Resume[] storage = new Resume[MAX_STORAGE_SIZE];
    private int storageSize;

    public void clear() {
        Arrays.fill(storage, null);
        storageSize = 0;
    }

    public void save(Resume r) {

        if (storageSize + 1 > MAX_STORAGE_SIZE) {
            System.out.println("ERROR: Maximum storage size reached");
            return;
        }

        if (get(r.getUuid()) != null) {
            System.out.println("ERROR: Resume already exists");
            return;
        } else {
            storage[storageSize] = r;
            storageSize++;
        }

    }

    public void update(Resume r) {
        if (get(r.getUuid()) != null) {
            //update
        } else {
            System.out.println("ERROR: Resume doesn't exist");
            return;
        }
    }

    public Resume get(String uuid) {

        for (Resume r : storage) {
            if (r != null && r.getUuid().equals(uuid)) {
                return r;
            } else if (r == null) {
                return null;
            }
        }

        return null;

    }

    public void delete(String uuid) {

        if (get(uuid) != null) {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = storage[storageSize - 1];
                    storage[storageSize - 1] = null;
                    storageSize--;
                    return;
                } else if (storage[i] == null) {
                    return;
                }
            }
        } else {
            System.out.println("ERROR: Resume doesn't exist");
            return;
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {

        Resume[] allResume = new Resume[storageSize];

        for (int i = 0; i < storageSize; i++) {
            allResume[i] = storage[i];
        }
        return allResume;

    }

    public int size() {
        return storageSize;
    }

}