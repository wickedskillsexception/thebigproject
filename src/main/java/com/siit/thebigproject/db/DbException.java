package com.siit.thebigproject.db;

public class DbException extends Exception {

    public DbException(String message) {
        super(message);
    }

    public DbException(String s, Exception e) {
        super(s, e);
    }
}
