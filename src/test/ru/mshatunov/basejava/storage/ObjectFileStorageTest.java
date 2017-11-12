package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.storage.serializer.ObjectStreamSerializer;

import static ru.mshatunov.basejava.TestData.STORAGE_DIR;

public class ObjectFileStorageTest extends AbstractStorageTest {

    public ObjectFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}
