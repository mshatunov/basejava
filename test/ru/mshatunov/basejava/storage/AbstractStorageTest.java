package ru.mshatunov.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.mshatunov.basejava.exception.ResumeAlreadyExistsStorageException;
import ru.mshatunov.basejava.exception.ResumeNotExistsStorageException;
import ru.mshatunov.basejava.exception.StorageException;
import ru.mshatunov.basejava.model.*;

import java.io.File;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = new File("/Users/mshatunov/IdeaProjects/basejava/test/resources");

    static final String UUID_0 = "uuid0";
    static final Resume RESUME_0 = new Resume(UUID_0, "Name0");

    static final String UUID_1 = "uuid1";
    static final Resume RESUME_1 = new Resume(UUID_1, "Name1");

    static final String UUID_2 = "uuid2";
    static final Resume RESUME_2 = new Resume(UUID_2, "Name2");

    static final String UUID_3 = "uuid3";
    static final Resume RESUME_3 = new Resume(UUID_3, "Name3");

    static final String UUID_4 = "uuid4";
    static final Resume RESUME_4 = new Resume(UUID_4, "Name4");

    static {
        RESUME_1.addContact(ContactType.MAIL, "mail1@ya.ru");
        RESUME_1.addContact(ContactType.PHONE, "11111");
        RESUME_1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        RESUME_1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        RESUME_1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        RESUME_1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        RESUME_1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        RESUME_1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
        RESUME_2.addContact(ContactType.SKYPE, "skype2");
        RESUME_2.addContact(ContactType.PHONE, "22222");
        RESUME_2.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));
    }

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