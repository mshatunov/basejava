package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.storage.serializer.DataStreamSerializer;

import static ru.mshatunov.basejava.TestData.STORAGE_DIR;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}
