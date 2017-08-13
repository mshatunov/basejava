package ru.mshatunov.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.mshatunov.basejava.exception.ResumeAlreadyExistsStorageException;
import ru.mshatunov.basejava.exception.ResumeNotExistsStorageException;
import ru.mshatunov.basejava.exception.StorageException;
import ru.mshatunov.basejava.model.Resume;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {

    static final String UUID_0 = "uuid0";
    static final Resume RESUME_0 = new Resume(UUID_0);

    static final String UUID_1 = "uuid1";
    static final Resume RESUME_1 = new Resume(UUID_1);

    static final String UUID_2 = "uuid2";
    static final Resume RESUME_2 = new Resume(UUID_2);

    static final String UUID_3 = "uuid3";
    static final Resume RESUME_3 = new Resume(UUID_3);

    static final String UUID_4 = "uuid4";
    static final Resume RESUME_4 = new Resume(UUID_4);

    AbstractArrayStorage storage;

    AbstractArrayStorageTest(AbstractArrayStorage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
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

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = 4; i <= AbstractArrayStorage.MAX_STORAGE_SIZE; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail();
        }
        storage.save(new Resume());
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
        assertArrayEquals(storage.getAll(), new Resume[]{RESUME_1, RESUME_2, RESUME_3});
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertArrayEquals(storage.getAll(), new Resume[]{});
    }

    @Test
    public void getIndex() throws Exception {
        assertEquals(1, storage.getIndex(UUID_2));
    }

}