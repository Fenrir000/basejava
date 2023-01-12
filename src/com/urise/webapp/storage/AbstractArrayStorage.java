package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public final void save(Resume r) {// Template method using findResumeIndex() and  insertResume() that is different in subclasses
        int index = findResumeIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else if (size == storage.length) {// if no more place in storage
            throw new StorageException("ERROR : Out of bound!", r.getUuid());
        } else {
            insertResume(r, index);
            size++;
        }
    }

    public final void update(Resume r) {// Template method using findResumeIndex() that is different in subclasses
        int index = findResumeIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[index] = r;
        }
    }

    public final Resume get(String uuid) {// Template method using findResumeIndex()  that is different in subclasses
        int index = findResumeIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public final void delete(String uuid) {// Template method using findResumeIndex() and removeResume() that is different in subclasses
        int index = findResumeIndex(uuid);
        if (index >= 0) {
            removeResume(index);
            storage[size - 1] = null;
            size--;
        } else {
            throw new NotExistStorageException(uuid);

        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int findResumeIndex(String uuid); // primitive operation that is overrided in subclasses

    protected abstract void removeResume(int index);// primitive operation that is overrided in subclasses

    protected abstract void insertResume(Resume r, int indexOfInsertion);// primitive operation that is overrided in subclasses
}
