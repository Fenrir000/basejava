package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage implements Storage{
    protected final static int STORAGE_LIMIT = 100000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume r) {
        int index=findResumeIndex(r.getUuid());
        if (index>=0) {
            System.out.println("ERROR : uuid  " + r.getUuid() + " already in storage!");
        } else if (size == storage.length) {// if no more place in storage
            System.out.println("ERROR : Out of bound!");
        } else {
            insertElement(r,index);
            size++;
        }
    }

    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index == -1) {
            System.out.println("ERROR :No " + r.getUuid() + " uuid in storage!");
        } else {
            storage[index] = r;
        }
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            System.out.println("ERROR :No " + uuid + " uuid in storage!");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index >= 0) {
           properElementRemove(index);
           storage[size - 1] = null;
           size--;
        } else {
            System.out.println("ERROR :No " + uuid + " uuid in storage!");
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

    protected abstract int findResumeIndex(String uuid);
    protected abstract void properElementRemove(int index);
    protected abstract void insertElement(Resume r,int indexOfInsertion);
}
