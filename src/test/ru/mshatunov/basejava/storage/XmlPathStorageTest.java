package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.storage.serializer.XmlSerializer;

import static ru.mshatunov.basejava.TestData.STORAGE_DIR;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlSerializer()));
    }
}
