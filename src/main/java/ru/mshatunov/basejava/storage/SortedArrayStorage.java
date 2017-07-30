package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, storageSize, searchKey);
    }

    @Override
    public void save(Resume r) {
        super.save(r);
        sort();
    }

    @Override
    public void delete(String uuid) {
        super.delete(uuid);
        sort();
    }

    private void sort() {
        Arrays.sort(storage, 0, storageSize);
    }

}
