package com.fairycompany.shape.validator;

import com.fairycompany.shape.entity.Point;
import com.fairycompany.shape.entity.Triangle;

public class TriangleValidator {
    private static final String TRIANGLE_DATA_REGEX = "^-?\\d+(\\.\\d+)?(\\s+-?\\d+(\\.\\d+)?){5}$";

    public static boolean isTrianglePossible(Triangle triangle) {
        Point A = triangle.getA();
        Point B = triangle.getB();
        Point C = triangle.getC();
        return isTrianglePossible(A, B, C);
    }

    public static boolean isTrianglePossible(Point A, Point B, Point C) {
        return ((C.X() - A.X()) * (B.Y() - A.Y()) - (C.Y() - A.Y()) * (B.X() - A.X()) != 0);
    }

    public static boolean isTriangleData(String data) {
        return data.matches(TRIANGLE_DATA_REGEX);
    }

}
