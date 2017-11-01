package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.exception.ResumeAlreadyExistsStorageException;
import ru.mshatunov.basejava.exception.ResumeNotExistsStorageException;
import ru.mshatunov.basejava.model.Resume;

import java.util.List;

public abstract class AbstractStorage<T> implements Storage {

    protected abstract T getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, T searchKey);

    protected abstract boolean isExist(T serchKey);

    protected abstract void doSave(Resume r, T searchKey);

    protected abstract void doDelete(T searchKey);

    protected abstract Resume doGet(T searchKey);

    protected abstract List<Resume> doCopyAll();

    public void update(Resume r) {
        T searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {

        T searchKey = getNotExistedSearchKey(r.getUuid());
        doSave(r, searchKey);

    }

    public void delete(String uuid) {

        T searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);

    }

    public Resume get(String uuid) {

        T searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);

    }

    private T getExistedSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new ResumeNotExistsStorageException(uuid);
        }
        return searchKey;
    }

    private T getNotExistedSearchKey(String uuid) {
        T searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ResumeAlreadyExistsStorageException(uuid);
        }
        return searchKey;
    }

}
