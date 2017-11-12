package ru.mshatunov.basejava;

import ru.mshatunov.basejava.model.*;

import java.io.File;
import java.time.Month;

public class TestData {

    public static final File STORAGE_DIR = Config.getInstance().getStorageDir();

    public static final String UUID_0 = "uuid0";
    public static final Resume RESUME_0 = new Resume(UUID_0, "Name0");

    public static final String UUID_1 = "uuid1";
    public static final Resume RESUME_1 = new Resume(UUID_1, "Name1");

    public static final String UUID_2 = "uuid2";
    public static final Resume RESUME_2 = new Resume(UUID_2, "Name2");

    public static final String UUID_3 = "uuid3";
    public static final Resume RESUME_3 = new Resume(UUID_3, "Name3");

    public static final String UUID_4 = "uuid4";
    public static final Resume RESUME_4 = new Resume(UUID_4, "Name4");

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
    
}
