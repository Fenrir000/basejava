/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size=0;
    void clear() {
        for(int i =0;i<size();i++){
            storage[i]=null;
        }
        size=0;
    }

    void save(Resume r) {
        if (size()==storage.length){// if no more place in storage
            Resume[] newStorage= new Resume[storage.length*2];
            for(int i=0;i<size();i++){
                newStorage[i]=storage[i];
            }
            storage=newStorage;
        }
        for(int i=0;i<size();i++){
            if(r.getUuid().equals(storage[i].getUuid())){
                return; //such uuid already in storage
            }
        }
        storage[size]=r;
        size++;

    }

    Resume get(String uuid) {
        for(int i=0;i<size();i++){
            if(uuid.equals(storage[i].getUuid())){
                return storage[i];
            }

        }
        return null; //no resume with such uuid
    }

    void delete(String uuid) {
        for(int i=0;i<size();i++){
            if(uuid.equals(storage[i].getUuid())){
                storage[i]=storage[size-1];
                storage[size-1]=null;
                size=size-1;
            }

        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] noNullStorage = new Resume[size];
        for(int i=0;i<size();i++){
            noNullStorage[i]=storage[i];
        }
        return noNullStorage;
    }

    int size() {
        return size;
    }
}
