package com.java;

public interface Sneaky {

    @SuppressWarnings("unchecked")
    static <E extends Exception, R> R sneakyThrow(Exception e) throws E {
        throw (E) e;
    }
}
