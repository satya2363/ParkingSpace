package com.parkingspace.Exceptions;

public class NoResultForQueryException extends Exception {

    private static final long serialVersionUID = 1L;

    public NoResultForQueryException(String s) {
        super(s);
    }
}
