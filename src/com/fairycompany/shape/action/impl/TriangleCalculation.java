package com.fairycompany.shape.action.impl;

import com.fairycompany.shape.action.ShapeCalculation;
import com.fairycompany.shape.entity.Point;
import com.fairycompany.shape.entity.Triangle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.fairycompany.shape.validator.TriangleValidator.*;


public class TriangleCalculation implements ShapeCalculation {
    private static Logger logger = LogManager.getLogger();
    private static final String WARN_MESSAGE = "Given triangle is null or it's parameters aren't valid";

    @Override
    public double calculatePerimeter(Triangle triangle) {
        if (triangle == null || !isTrianglePossible(triangle)) {
            logger.log(Level.WARN, WARN_MESSAGE);
            return 0;
        }

        double sideAB = calculateTriangleSide(triangle.getPointA(), triangle.getPointB());
        double sideBC = calculateTriangleSide(triangle.getPointB(), triangle.getPointC());
        double sideAC = calculateTriangleSide(triangle.getPointA(), triangle.getPointC());

        double perimeter = sideAB + sideBC + sideAC;

        logger.log(Level.INFO, "Perimeter of triangle #{} is {}", triangle.getTriangleId(), perimeter);

        return perimeter;
    }

    @Override
    public double calculateArea(Triangle triangle) {
        if (triangle == null || !isTrianglePossible(triangle)) {
            logger.log(Level.WARN, WARN_MESSAGE);
            return 0;
        }

        double area = Math.abs((triangle.getPointA().x() - triangle.getPointC().x()) *
                (triangle.getPointB().y() - triangle.getPointC().y()) -
                (triangle.getPointB().x() - triangle.getPointC().x()) *
                        (triangle.getPointA().y() - triangle.getPointC().y())) / 2;

        logger.log(Level.INFO, "Area of triangle #{} is {}", triangle.getTriangleId(), area);

        return area;
    }

    public boolean isRightTriangle(Triangle triangle) {
        if (triangle == null || !isTrianglePossible(triangle)) {
            logger.log(Level.WARN, WARN_MESSAGE);
            return false;
        }

        double sideAB = calculateTriangleSide(triangle.getPointA(), triangle.getPointB());
        double sideBC = calculateTriangleSide(triangle.getPointB(), triangle.getPointC());
        double sideAC = calculateTriangleSide(triangle.getPointA(), triangle.getPointC());

        boolean rightTriangle = roughDoubleComparing(Math.pow(sideAC, 2), Math.hypot(sideAB, sideBC));

        logger.log(Level.INFO, () -> rightTriangle ? "Triangle " + triangle.getTriangleId() + " is right" :
                "Triangle " + triangle.getTriangleId() + " isn't right");

        return rightTriangle;
    }

    public boolean isIsoscelesTriangle(Triangle triangle) {
        if (triangle == null || !isTrianglePossible(triangle)) {
            logger.log(Level.WARN, WARN_MESSAGE);
            return false;
        }

        double sideAB = calculateTriangleSide(triangle.getPointA(), triangle.getPointB());
        double sideBC = calculateTriangleSide(triangle.getPointB(), triangle.getPointC());
        double sideAC = calculateTriangleSide(triangle.getPointA(), triangle.getPointC());

        boolean isoscelesTriangle = roughDoubleComparing(sideAB, sideAC) ||
                roughDoubleComparing(sideBC, sideAC) ||
                roughDoubleComparing(sideAB, sideBC);

        logger.log(Level.INFO, () -> isoscelesTriangle ? "Triangle " + triangle.getTriangleId() + " is isosceles" :
                "Triangle " + triangle.getTriangleId() + " isn't isosceles");

        return isoscelesTriangle;
    }

    public boolean isEquilateralTriangle(Triangle triangle) {
        if (triangle == null || !isTrianglePossible(triangle)) {
            logger.log(Level.WARN, WARN_MESSAGE);
            return false;
        }

        double sideAB = calculateTriangleSide(triangle.getPointA(), triangle.getPointB());
        double sideBC = calculateTriangleSide(triangle.getPointB(), triangle.getPointC());
        double sideAC = calculateTriangleSide(triangle.getPointA(), triangle.getPointC());

        boolean equilateralTriangle = roughDoubleComparing(sideAB, sideBC) &&
                roughDoubleComparing(sideAB, sideAC);

        logger.log(Level.INFO, () -> equilateralTriangle ? "Triangle " + triangle.getTriangleId() + " is equilateral" :
                "Triangle " + triangle.getTriangleId() + " isn't equilateral");

        return equilateralTriangle;
    }

    // todo acute or obtuse

    private double calculateTriangleSide(Point pointA, Point pointB) {
        double side = Math.hypot(pointB.x() - pointA.x(), pointB.y() - pointA.y());

        logger.log(Level.DEBUG, "Сторона равна {}", side);

        return side;
    }

    // todo sides method

    private boolean roughDoubleComparing(double element1, double element2) {
        double delta = 0.0001;
        return Math.abs(element1 - element2) <= delta;
    }
}
