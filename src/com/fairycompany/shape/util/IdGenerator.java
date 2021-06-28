package com.fairycompany.shape.util;

public class IdGenerator {
    private static long counter;

    private IdGenerator(){

    }

    public static long generateId() {
        return ++counter;
    }
}
