package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.exception.StorageException;
import ru.mshatunov.basejava.model.Resume;

import java.io.*;
import java.util.List;

public class ObjectStreamStorage extends AbstractFileStorage {

    protected ObjectStreamStorage(File directory) {
        super(directory);
    }

    @Override
    protected void doWrite(Resume r, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(r);
        }
    }

    @Override
    protected Resume doRead(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            try {
                return (Resume) ois.readObject();
            } catch (ClassNotFoundException e) {
                throw new StorageException("Error while reading resume", null, e);
            }
        }
    }
}
