package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


public abstract class AbstractArrayStorageTest {
    protected Storage storage;// it will be declared according to subclasses constructors

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }// subclass constructor calls this parent constructor

    private final Resume r1 = new Resume("a");
    private final Resume r2 = new Resume("b");
    private final Resume r3 = new Resume("c");
    private final Resume r4 = new Resume("d");
    private final Resume r5 = new Resume("e");

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
        storage.save(r4);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void save() throws Exception {
        storage.save(r5);
        assertEquals(5, storage.size());
        assertSame(r5, storage.get("e"));
    }

    @Test
    public void update() throws Exception {
        Resume updateResume = new Resume("d");
        storage.update(updateResume);
        assertSame(storage.get("d"), updateResume);
    }

    @Test
    public void get() throws Exception {
        Resume r2Resume = storage.get("b");
        assertSame(r2, r2Resume);

    }

    @Test(expected = NotExistStorageException.class)
// test will be passed if  method will throw NotExistStorageException after trying to get deleted element
    public void delete() throws Exception {
        storage.delete("a");
        storage.get("a");

    }

    @Test
    public void getAll() throws Exception {
        assertEquals(4, storage.size());
        assertSame(r1, storage.get(r1.getUuid()));
        assertSame(r2, storage.get(r2.getUuid()));
        assertSame(r3, storage.get(r3.getUuid()));
        assertSame(r4, storage.get(r4.getUuid()));
    }

    @Test
    public void size() throws Exception {
        assertEquals(4, storage.size());
        storage.delete("c");
        assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    // test will be passed if method will throw NotExistStorageException
    public void getNotExist() throws Exception {
        storage.get("dummy");

    }

    @Test(expected = NotExistStorageException.class)
    // test will be passed if method will throw NotExistStorageException
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");

    }

    @Test(expected = NotExistStorageException.class)
    // test will be passed if method will throw NotExistStorageException
    public void updateNotExist() throws Exception {
        storage.update(new Resume("dummy"));

    }

    @Test(expected = ExistStorageException.class)// test will be passed if method will throw ExistStorageException
    public void saveExist() throws Exception {
        storage.save(r1);
    }

    @Test(expected = StorageException.class)// test will be passed if method will throw StorageException
    public void saveOverflowing() throws Exception {
        storage.clear();
        try {
            int i = 0;
            while (i < AbstractArrayStorage.STORAGE_LIMIT) { // we fill storage array
                storage.save(new Resume(String.valueOf(i)));
                i++;
            }
        } catch (StorageException e) {// we should not catch exception here, because there is no overflowing yet
            Assert.fail("Must have no Exception"); // if this block is running, it will fail test with  that message
        }
        storage.save(new Resume());// here storage must be overflown
    }

}