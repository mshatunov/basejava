package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> list = new ArrayList<>();

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object serchKey) {
        return serchKey != null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        list.set((Integer) searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        list.add(r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        list.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return list.get((Integer) searchKey);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        list.sort((o1, o2) -> o1.getFullName().compareTo(o2.getFullName()));
        return list;
    }

    @Override
    public int size() {
        return list.size();
    }
}
