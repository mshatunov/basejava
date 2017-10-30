package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.exception.ResumeAlreadyExistsStorageException;
import ru.mshatunov.basejava.exception.ResumeNotExistsStorageException;
import ru.mshatunov.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract boolean isExist(Object serchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    public void update(Resume r) {
        Object searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {

        Object searchKey = getNotExistedSearchKey(r.getUuid());
        doSave(r, searchKey);

    }

    public void delete(String uuid) {

        Object searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);

    }

    public Resume get(String uuid) {

        Object searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);

    }

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new ResumeNotExistsStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ResumeAlreadyExistsStorageException(uuid);
        }
        return searchKey;
    }

}