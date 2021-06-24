package com.fairycompany.shape.util;

public class IDGenerator {
    private static long counter;

    private IDGenerator(){

    }

    public static long generateID() {
        return ++counter;
    }
}
