package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {


    /**
     * @return array, contains only Resumes in storage (without null)
     */

    protected void insertResume(Resume r, int indexOfInsertion) {
        storage[size] = r;
    }

    protected  void removeResume(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    protected int findResumeIndex(String uuid) { //return resume with such uuid in storage if present
        for (int i = 0; i < size; i++) {         //if not present return -1
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
