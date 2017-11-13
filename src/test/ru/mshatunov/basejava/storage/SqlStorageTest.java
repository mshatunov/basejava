package ru.mshatunov.basejava.storage;

import ru.mshatunov.basejava.Config;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(Config.getInstance().getStorage());
    }
}
