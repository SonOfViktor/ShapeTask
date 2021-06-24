package com.fairycompany.shape.util;

public class IDGenerator {
    private static long counter;

    public static long generateID() {
        return ++counter;
    }
}
