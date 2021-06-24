package com.fairycompany.shape.validator;

import com.fairycompany.shape.entity.Point;
import com.fairycompany.shape.entity.Triangle;

public class TriangleValidator {
    private static final String TRIANGLE_DATA_REGEX = "^-?\\d+(\\.\\d+)?(\\s+-?\\d+(\\.\\d+)?){5}$";

    private TriangleValidator() {

    }

    public static boolean isTrianglePossible(Triangle triangle) {
        Point pointA = triangle.getPointA();
        Point pointB = triangle.getPointB();
        Point pointC = triangle.getPointC();
        return isTrianglePossible(pointA, pointB, pointC);
    }

    public static boolean isTrianglePossible(Point pointA, Point pointB, Point pointC) {
        return ((pointC.X() - pointA.X()) * (pointB.Y() - pointA.Y()) -
                (pointC.Y() - pointA.Y()) * (pointB.X() - pointA.X()) != 0);
    }

    public static boolean isTriangleData(String data) {
        return data.matches(TRIANGLE_DATA_REGEX);
    }

}
