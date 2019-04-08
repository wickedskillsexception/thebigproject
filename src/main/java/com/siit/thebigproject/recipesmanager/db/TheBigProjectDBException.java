package com.siit.thebigproject.recipesmanager.db;

public class TheBigProjectDBException extends Exception {

    public TheBigProjectDBException(String s, Exception e) {
        super(s, e);
    }

    public TheBigProjectDBException(String s) {
        super(s);
    }
}
