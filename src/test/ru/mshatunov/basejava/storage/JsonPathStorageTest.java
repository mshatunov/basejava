package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.storage.serializer.JsonStreamSerializer;

import static ru.mshatunov.basejava.TestData.STORAGE_DIR;

public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}
