package ru.mshatunov.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.mshatunov.basejava.exception.ResumeAlreadyExistsStorageException;
import ru.mshatunov.basejava.exception.ResumeNotExistsStorageException;
import ru.mshatunov.basejava.model.Resume;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.mshatunov.basejava.TestData.*;

public abstract class AbstractStorageTest {


    Storage storage;

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertEquals(storage.get(UUID_4).getUuid(), UUID_4);
    }

    @Test(expected = ResumeAlreadyExistsStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
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
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_1)));
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
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

}