package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class SortedArrayStorage extends AbstractArrayStorage {

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    @Override
    protected void removeResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected void insertResume(Resume r, int indexOfInsertion) {
        indexOfInsertion = -indexOfInsertion - 1;
        System.arraycopy(storage, indexOfInsertion, storage, indexOfInsertion + 1, size() - indexOfInsertion);
        storage[indexOfInsertion] = r;
    }

    @Override
    public int findResumeIndex(String uuid) {
        Resume key = new Resume();
        key.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, key);
    }
}
