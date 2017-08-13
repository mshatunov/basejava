package ru.mshatunov.basejava.storage;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Test
    public void insertElement() throws Exception {
        storage.save(RESUME_0);
        assertEquals(3, storage.getIndex(UUID_0));
    }

    @Test
    public void fillDeletedElement() throws Exception {
        storage.fillDeletedElement(0);
        assertEquals(UUID_3, storage.storage[0].getUuid());
    }


}
