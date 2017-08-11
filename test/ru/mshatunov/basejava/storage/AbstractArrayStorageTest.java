package ru.mshatunov.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.mshatunov.basejava.exception.ResumeNotExistsStorageException;
import ru.mshatunov.basejava.model.Resume;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public abstract class AbstractArrayStorageTest {

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    Storage storage;

    @Before
    public void setUp() throws Exception {
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());

        storage.save(new Resume(UUID_4));
        assertEquals(4, storage.size());
    }

    @Test
    public void save() throws Exception {
        storage.save(new Resume(UUID_4));
        assertEquals(storage.get(UUID_4).getUuid(), UUID_4);
    }

    @Test(expected = ResumeNotExistsStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_3);
        storage.get(UUID_3);
    }

    @Test(expected = ResumeNotExistsStorageException.class)
    public void deleteNotExists() throws Exception {
        storage.delete(UUID_4);
    }

    @Test
    public void update() throws Exception {
        //no update implemented yet
    }

    @Test
    public void get() throws Exception {
        assertEquals(storage.get(UUID_1).getUuid(), UUID_1);
    }

    @Test(expected = ResumeNotExistsStorageException.class)
    public void getNotExists() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAll() throws Exception {
        assertArrayEquals(storage.getAll(), new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)});
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertArrayEquals(storage.getAll(), new Resume[]{});
    }

}