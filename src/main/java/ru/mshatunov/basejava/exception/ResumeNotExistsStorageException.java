package ru.mshatunov.basejava.exception;

public class ResumeNotExistsStorageException extends StorageException {

    public ResumeNotExistsStorageException(String uuid) {
        super("Resume " + uuid + " does not exist", uuid);
    }
}
