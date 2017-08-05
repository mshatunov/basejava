package ru.mshatunov.basejava.exception;

public class ResumeAlreadyExistsStorageException extends StorageException {

    public ResumeAlreadyExistsStorageException(String uuid) {
        super("Resume " + uuid + " already exists", uuid);
    }
}
