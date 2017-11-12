package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.storage.serializer.ObjectStreamSerializer;

import static ru.mshatunov.basejava.TestData.STORAGE_DIR;

public class ObjectPathStorageTest extends AbstractStorageTest {

    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}
