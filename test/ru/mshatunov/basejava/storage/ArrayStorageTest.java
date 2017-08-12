package ru.mshatunov.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.mshatunov.basejava.model.Resume;

import static org.junit.Assert.assertEquals;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    ArrayStorage storage = new ArrayStorage();

    @Override
    @Before
    public void setUp() throws Exception {
        super.storage = storage;
        super.setUp();
    }

    @Test
    public void insertElement() throws Exception {
        storage.save(new Resume(UUID_0));
        assertEquals(3, storage.getIndex(UUID_0));
    }

    @Test
    public void fillDeletedElement() throws Exception {
        storage.fillDeletedElement(0);
        assertEquals(UUID_3, storage.storage[0].getUuid());
    }


}
