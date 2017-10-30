package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.exception.StorageException;
import ru.mshatunov.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int MAX_STORAGE_SIZE = 10000;

    Resume[] storage = new Resume[MAX_STORAGE_SIZE];
    int storageSize;

    public int size() {
        return storageSize;
    }

    @Override
    protected void doSave(Resume r, Object index) {

        if (storageSize == MAX_STORAGE_SIZE) {
            throw new StorageException("Maximum storage size reached", r.getUuid());
        }
        insertElement(r, (Integer) index);
        storageSize++;
    }

    @Override
    protected void doDelete(Object index) {
        fillDeletedElement((Integer) index);
        storage[storageSize - 1] = null;
        storageSize--;
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
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

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);

}
