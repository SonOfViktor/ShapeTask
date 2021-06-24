package com.fairycompany.shape.action.impl;

import com.fairycompany.shape.action.ShapeCalculation;
import com.fairycompany.shape.entity.Point;
import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.exception.TriangleException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TriangleCalculation implements ShapeCalculation {                  // todo BigDecimal
    private static final Logger logger = LogManager.getLogger();

    @Override
    public double calculatePerimeter(Triangle triangle) throws TriangleException {
        if (triangle == null) {
            throw new TriangleException();
        }

        double sideAB = calculateTriangleSide(triangle.getA(), triangle.getB());
        double sideBC = calculateTriangleSide(triangle.getB(), triangle.getC());
        double sideAC = calculateTriangleSide(triangle.getA(), triangle.getC());

        double perimeter = sideAB + sideBC + sideAC;

        logger.log(Level.INFO, "Perimeter of triangle #{} is {}", triangle.getTriangleID(), perimeter);

        return perimeter;
    }

    @Override
    public double calculateArea(Triangle triangle) throws TriangleException {
        if (triangle == null) {
            throw new TriangleException();
        }

        double area = Math.abs((triangle.getA().getX() - triangle.getC().getX()) *
                                       (triangle.getB().getY() - triangle.getC().getY()) -
                                       (triangle.getB().getX() - triangle.getC().getX()) *
                                       (triangle.getA().getY() - triangle.getC().getY())) / 2;

        logger.log(Level.INFO, "Area of triangle #{} is {}", triangle.getTriangleID(), area);

        return area;
    }

    public boolean isRightTriangle(Triangle triangle) throws TriangleException {      // todo here or in validator?
        if (triangle == null) {
            throw new TriangleException();
        }

        double delta = 0.001;

        double sideAB = calculateTriangleSide(triangle.getA(), triangle.getB());
        double sideBC = calculateTriangleSide(triangle.getB(), triangle.getC());
        double sideAC = calculateTriangleSide(triangle.getA(), triangle.getC());

        boolean rightTriangle = Math.pow(sideAC, 2) - Math.hypot(sideAB, sideBC) == delta;

        if (rightTriangle) {
            logger.log(Level.INFO, "Triangle #{} is right", triangle.getTriangleID());
        } else {
            logger.log(Level.INFO, "Triangle #{} isn't right", triangle.getTriangleID());
        }

        return rightTriangle;
    }

    public boolean isIsoscelesTriangle(Triangle triangle) throws TriangleException {
        if (triangle == null) {
            throw new TriangleException();
        }

        double sideAB = calculateTriangleSide(triangle.getA(), triangle.getB());
        double sideBC = calculateTriangleSide(triangle.getB(), triangle.getC());
        double sideAC = calculateTriangleSide(triangle.getA(), triangle.getC());

        boolean isoscelesTriangle = sideAB == sideAC || sideBC == sideAC || sideAB == sideBC;

        if (isoscelesTriangle) {
            logger.log(Level.INFO, "Triangle #{} is isosceles", triangle.getTriangleID());
        } else {
            logger.log(Level.INFO, "Triangle #{} isn't isosceles", triangle.getTriangleID());
        }

        return isoscelesTriangle;
    }

    public boolean isEquilateralTriangle(Triangle triangle) throws TriangleException {
        if (triangle == null) {
            throw new TriangleException();
        }

        double sideAB = calculateTriangleSide(triangle.getA(), triangle.getB());
        double sideBC = calculateTriangleSide(triangle.getB(), triangle.getC());
        double sideAC = calculateTriangleSide(triangle.getA(), triangle.getC());

        boolean equilateralTriangle = sideAB == sideBC && sideAB == sideAC;

        if (equilateralTriangle) {
            logger.log(Level.INFO, "Triangle #{} is equilateral", triangle.getTriangleID());
        } else {
            logger.log(Level.INFO, "Triangle #{} isn't equilateral", triangle.getTriangleID());
        }

        return equilateralTriangle;
    }

    // todo acute or obtuse

    private double calculateTriangleSide(Point A, Point B) {
        double side = Math.hypot(B.getX() - A.getX(), B.getY() - A.getY());

        logger.log(Level.DEBUG, "Сторона равна {}", side);

        return side;
    }
}
