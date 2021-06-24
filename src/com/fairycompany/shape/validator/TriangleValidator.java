package com.fairycompany.shape.validator;

import com.fairycompany.shape.entity.Point;
import com.fairycompany.shape.entity.Triangle;

public class TriangleValidator {
    private static final String TRIANGLE_DATA_REGEX = "^-?\\d+(\\.\\d+)?(\\s+-?\\d+(\\.\\d+)?){5}$";

    public static boolean isTrianglePossible(Point A, Point B, Point C) {
        return ((C.getX() - A.getX()) * (B.getY() - A.getY()) - (C.getY() - A.getY()) * (B.getX() - A.getX()) != 0);
    }

    public static boolean isTriangleData (String data) {
        return data.matches(TRIANGLE_DATA_REGEX);
    }

}
