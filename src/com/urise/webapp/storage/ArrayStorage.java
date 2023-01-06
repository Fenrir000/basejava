package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage,null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == storage.length) {// if no more place in storage
            System.out.println("ERROR : Out of bound!");
            return;
        }
        if (findResumeIndex(r.getUuid()) != -1) {
            System.out.println("ERROR : uuid + " + r.getUuid() + " already in storage!");
            return;
        }
        storage[size] = r;
        size++;
    }

    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index == -1) {
            System.out.println("ERROR :No " + r.getUuid() + " uuid in storage!");
            return;
        } else storage[index] = r;
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            System.out.println("ERROR :No " + uuid + " uuid in storage!");
        }
        return null;
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index > -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else System.out.println("ERROR :No " + uuid + " uuid in storage!");
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

    public int findResumeIndex(String uuid) { //return resume with such uuid in storage if present
        for (int i = 0; i < size; i++) {         //if not present return -1
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
