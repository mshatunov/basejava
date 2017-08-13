package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.exception.ResumeAlreadyExistsStorageException;
import ru.mshatunov.basejava.exception.ResumeNotExistsStorageException;
import ru.mshatunov.basejava.exception.StorageException;
import ru.mshatunov.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int MAX_STORAGE_SIZE = 10000;

    Resume[] storage = new Resume[MAX_STORAGE_SIZE];
    int storageSize;

    public int size() {
        return storageSize;
    }

    public void save(Resume r) {

        if (storageSize == MAX_STORAGE_SIZE) {
            throw new StorageException("Maximum storage size reached", r.getUuid());
        }

        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ResumeAlreadyExistsStorageException(r.getUuid());
        } else {
            insertElement(r, index);
            storageSize++;
        }

    }

    public void delete(String uuid) {

        int index = getIndex(uuid);
        if (getIndex(uuid) >= 0) {
            fillDeletedElement(index);
            storage[storageSize - 1] = null;
            storageSize--;
        } else {
            throw new ResumeNotExistsStorageException(uuid);
        }

    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            throw new ResumeNotExistsStorageException(r.getUuid());
        }
    }

    public Resume get(String uuid) {

        int index = getIndex(uuid);
        if (index < 0) {
            throw new ResumeNotExistsStorageException(uuid);
        }
        return storage[index];

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, storageSize);
    }

    public void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);

}
