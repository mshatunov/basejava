package ru.mshatunov.basejava.storage;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Test
    public void insertElement() throws Exception {
        storage.save(RESUME_0);
        assertEquals(0, storage.getIndex(UUID_0));
    }

    @Test
    public void fillDeletedElement() throws Exception {
        storage.fillDeletedElement(0);
        assertEquals(UUID_2, storage.storage[0].getUuid());
    }

}