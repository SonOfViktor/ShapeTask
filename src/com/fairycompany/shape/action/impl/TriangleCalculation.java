package com.fairycompany.shape.action.impl;

import com.fairycompany.shape.action.ShapeCalculation;
import com.fairycompany.shape.entity.Point;
import com.fairycompany.shape.entity.Triangle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.fairycompany.shape.validator.TriangleValidator.*;


public class TriangleCalculation implements ShapeCalculation {
    private static final Logger logger = LogManager.getLogger();
    private static final String WARN_MESSAGE = "Given triangle is null or it's parameters aren't valid";

    @Override
    public double calculatePerimeter(Triangle triangle) {
        if (triangle == null || !isTrianglePossible(triangle)) {
            logger.log(Level.WARN, WARN_MESSAGE);
            return -1;
        }

        double sideAB = calculateTriangleSide(triangle.getPointA(), triangle.getPointB());        //todo make method with array
        double sideBC = calculateTriangleSide(triangle.getPointB(), triangle.getPointC());            // if checkers stay here
        double sideAC = calculateTriangleSide(triangle.getPointA(), triangle.getPointC());

        double perimeter = sideAB + sideBC + sideAC;

        logger.log(Level.INFO, "Perimeter of triangle #{} is {}", triangle.getTriangleID(), perimeter);

        return perimeter;
    }

    @Override
    public double calculateArea(Triangle triangle) {
        if (triangle == null || !isTrianglePossible(triangle)) {
            logger.log(Level.WARN, WARN_MESSAGE);
            return -1;
        }

        double area = Math.abs((triangle.getPointA().X() - triangle.getPointC().X()) *
                (triangle.getPointB().Y() - triangle.getPointC().Y()) -
                (triangle.getPointB().X() - triangle.getPointC().X()) *
                        (triangle.getPointA().Y() - triangle.getPointC().Y())) / 2;

        logger.log(Level.INFO, "Area of triangle #{} is {}", triangle.getTriangleID(), area);

        return area;
    }

    public boolean isRightTriangle(Triangle triangle) {      // todo here or in validator?
        if (triangle == null || !isTrianglePossible(triangle)) {
            logger.log(Level.WARN, WARN_MESSAGE);
            return false;
        }

        double sideAB = calculateTriangleSide(triangle.getPointA(), triangle.getPointB());
        double sideBC = calculateTriangleSide(triangle.getPointB(), triangle.getPointC());
        double sideAC = calculateTriangleSide(triangle.getPointA(), triangle.getPointC());

        boolean rightTriangle = roughDoubleComparing(Math.pow(sideAC, 2), Math.hypot(sideAB, sideBC));

        if (rightTriangle) {
            logger.log(Level.INFO, "Triangle #{} is right", triangle.getTriangleID());
        } else {
            logger.log(Level.INFO, "Triangle #{} isn't right", triangle.getTriangleID());
        }

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

        if (isoscelesTriangle) {
            logger.log(Level.INFO, "Triangle #{} is isosceles", triangle.getTriangleID());
        } else {
            logger.log(Level.INFO, "Triangle #{} isn't isosceles", triangle.getTriangleID());
        }

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

        if (equilateralTriangle) {
            logger.log(Level.INFO, "Triangle #{} is equilateral", triangle.getTriangleID());
        } else {
            logger.log(Level.INFO, "Triangle #{} isn't equilateral", triangle.getTriangleID());
        }

        return equilateralTriangle;
    }

    // todo acute or obtuse

    private double calculateTriangleSide(Point pointA, Point pointB) {
        double side = Math.hypot(pointB.X() - pointA.X(), pointB.Y() - pointA.Y());

        logger.log(Level.DEBUG, "Сторона равна {}", side);

        return side;
    }

    private boolean roughDoubleComparing(double element1, double element2) {
        double delta = 0.0001;
        return Math.abs(element1 - element2) <= delta;
    }
}
