package ru.mshatunov.basejava.sql;

import org.postgresql.util.PSQLException;
import ru.mshatunov.basejava.exception.ResumeAlreadyExistsStorageException;
import ru.mshatunov.basejava.exception.StorageException;

import java.sql.SQLException;

public class ExceptionUtil {

    private ExceptionUtil() {
    }

    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException && e.getSQLState().equals("23505")) {
            return new ResumeAlreadyExistsStorageException(null);
        }
        return new StorageException(e);
    }

}
