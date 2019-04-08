package com.siit.thebigproject.service.recipesManager.db;

public class TheBigProjectDBException extends Exception {

    public TheBigProjectDBException(String s, Exception e) {
        super(s, e);
    }

    public TheBigProjectDBException(String s) {
        super(s);
    }
}
