import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    final static int STORAGE_SIZE = 10000;
    Resume[] storage = new Resume[STORAGE_SIZE];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {

        int size = this.size();

        if (size < STORAGE_SIZE) {
            storage[size] = r;
            this.sort();
        }

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

        for (int count = 0; count < storage.length; count++) {
            if (storage[count].uuid == uuid) {
                storage[count] = null;
                this.sort();
                return;
            } else if (storage[count] == null) {
                return;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

        int size = this.size();
        Resume[] allResume = new Resume[size];

        for (int count = 0; count < size; count++) {
            allResume[count] = storage[count];
        }
        return allResume;

    }

    int size() {

        this.sort();

        int count = 0;
        for (Resume r : storage) {
            if (r != null) {
                count++;
            } else {
                return count;
            }
        }
        return count;
    }

    void sort() {
        Arrays.sort(storage, new ResumeComparator());
    }

}
