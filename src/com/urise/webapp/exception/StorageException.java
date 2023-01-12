package com.urise.webapp.exception;

public class StorageException extends RuntimeException{ //we made it RunTimeExceptions subclass, so it will be unchecked, and we should not handle it
    private final String uuid;// we store here uuid to find out what went wrong

    public String getUuid() {//
        return uuid;
    }

    public StorageException(String message, String uuid){
        super(message);// RunTimeException has a String message field
        this.uuid=uuid;

    }

}
