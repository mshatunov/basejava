package ru.mshatunov.basejava.model;

import java.util.UUID;

public class Resume implements Comparable<Resume> {

    private final String uuid;

    private final String fullName;

    public Resume() {
        this(UUID.randomUUID().toString(), null);
    }

    public Resume(String uuid) {
        this(uuid, null);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName == null ? " " : fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (getUuid() != null ? !getUuid().equals(resume.getUuid()) : resume.getUuid() != null) return false;
        return getFullName() != null ? getFullName().equals(resume.getFullName()) : resume.getFullName() == null;
    }

    @Override
    public int hashCode() {
        int result = getUuid() != null ? getUuid().hashCode() : 0;
        result = 31 * result + (getFullName() != null ? getFullName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Resume o) {
        return this.getUuid().compareTo(o.getUuid());
    }
}
