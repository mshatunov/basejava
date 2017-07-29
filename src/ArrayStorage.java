import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    final static int MAX_STORAGE_SIZE = 10000;
    Resume[] storage = new Resume[MAX_STORAGE_SIZE];
    int storageSize;

    void clear() {
        Arrays.fill(storage, null);
        storageSize = 0;
    }

    void save(Resume r) {
        storage[storageSize] = r;
        storageSize++;
    }

    Resume get(String uuid) {

        for (Resume r : storage) {
            if (r != null && r.uuid == uuid) {
                return r;
            } else if (r == null) {
                return null;
            }
        }

        return null;

    }

    void delete(String uuid) {

        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = storage[storageSize-1];
                storage[storageSize-1] = null;
                storageSize--;
                return;
            } else if (storage[i] == null) {
                return;
            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

        Resume[] allResume = new Resume[storageSize];

        for (int i = 0; i < storageSize; i++) {
            allResume[i] = storage[i];
        }
        return allResume;

    }

    int size() {

        return storageSize;
    }

}