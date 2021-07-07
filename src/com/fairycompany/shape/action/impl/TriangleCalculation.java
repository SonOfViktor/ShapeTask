package com.fairycompany.shape.action.impl;

import com.fairycompany.shape.action.ShapeCalculation;
import com.fairycompany.shape.entity.Point;
import com.fairycompany.shape.entity.Triangle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.OptionalDouble;


public class TriangleCalculation implements ShapeCalculation {
    private static Logger logger = LogManager.getLogger();
    private static final double ROUND_SCALE = 1000;
    private static final String WARN_MESSAGE = "Given triangle is null";

    @Override
    public OptionalDouble calculatePerimeter(Triangle triangle) {
        if (triangle == null) {
            logger.log(Level.WARN, WARN_MESSAGE);
            return OptionalDouble.empty();
        }

        double[] sides = calculateTriangleSides(triangle);
        double perimeter = 0;

        for (double side : sides) {
            perimeter += side;
        }

        perimeter = roundDouble(perimeter);

        logger.log(Level.INFO, "Perimeter of triangle #{} is {}", triangle.getTriangleId(), perimeter);

        return OptionalDouble.of(perimeter);
    }

    @Override
    public OptionalDouble calculateArea(Triangle triangle) {
        if (triangle == null) {
            logger.log(Level.WARN, WARN_MESSAGE);
            return OptionalDouble.empty();
        }

        double area = Math.abs((triangle.getPointA().x() - triangle.getPointC().x()) *
                (triangle.getPointB().y() - triangle.getPointC().y()) -
                (triangle.getPointB().x() - triangle.getPointC().x()) *
                        (triangle.getPointA().y() - triangle.getPointC().y())) / 2;

        area = roundDouble(area);

        logger.log(Level.INFO, "Area of triangle #{} is {}", triangle.getTriangleId(), area);

        return OptionalDouble.of(area);
    }

    public boolean isRightTriangle(Triangle triangle) {
        if (triangle == null) {
            logger.log(Level.WARN, WARN_MESSAGE);
            return false;
        }

        double[] sides = calculateTriangleSides(triangle);

        Arrays.sort(sides);

        boolean rightTriangle = sides[2] == roundDouble(Math.hypot(sides[0], sides[1]));

        logger.log(Level.INFO, () -> rightTriangle ? "Triangle " + triangle.getTriangleId() + " is right" :
                "Triangle " + triangle.getTriangleId() + " isn't right");

        return rightTriangle;
    }

    public boolean isAcuteTriangle(Triangle triangle) {
        if (triangle == null) {
            logger.log(Level.WARN, WARN_MESSAGE);
            return false;
        }

        double[] sides = calculateTriangleSides(triangle);

        Arrays.sort(sides);

        boolean acuteTriangle = sides[2] < roundDouble(Math.hypot(sides[0], sides[1]));

        logger.log(Level.INFO, () -> acuteTriangle ? "Triangle " + triangle.getTriangleId() + " is acute" :
                "Triangle " + triangle.getTriangleId() + " isn't acute");

        return acuteTriangle;
    }

    public boolean isObtuseTriangle(Triangle triangle) {
        if (triangle == null) {
            logger.log(Level.WARN, WARN_MESSAGE);
            return false;
        }

        double[] sides = calculateTriangleSides(triangle);

        Arrays.sort(sides);

        boolean obtuseTriangle = sides[2] > roundDouble(Math.hypot(sides[0], sides[1]));;

        logger.log(Level.INFO, () -> obtuseTriangle ? "Triangle " + triangle.getTriangleId() + " is obtuse" :
                "Triangle " + triangle.getTriangleId() + " isn't obtuse");

        return obtuseTriangle;
    }

    public boolean isIsoscelesTriangle(Triangle triangle) {
        if (triangle == null) {
            logger.log(Level.WARN, WARN_MESSAGE);
            return false;
        }

        double[] sides = calculateTriangleSides(triangle);

        boolean isoscelesTriangle = sides[0] == sides[1] ||
                                    sides[0] == sides[2] ||
                                    sides[1] == sides[2];

        logger.log(Level.INFO, () -> isoscelesTriangle ? "Triangle " + triangle.getTriangleId() + " is isosceles" :
                "Triangle " + triangle.getTriangleId() + " isn't isosceles");

        return isoscelesTriangle;
    }

    public boolean isEquilateralTriangle(Triangle triangle) {
        if (triangle == null) {
            logger.log(Level.WARN, WARN_MESSAGE);
            return false;
        }

        double[] sides = calculateTriangleSides(triangle);

        boolean equilateralTriangle = sides[0] == sides[1] &&
                                      sides[0] == sides[2];

        logger.log(Level.INFO, () -> equilateralTriangle ? "Triangle " + triangle.getTriangleId() + " is equilateral" :
                "Triangle " + triangle.getTriangleId() + " isn't equilateral");

        return equilateralTriangle;
    }

    private double calculateSide(Point pointA, Point pointB) {
        double side = roundDouble(Math.hypot(pointB.x() - pointA.x(), pointB.y() - pointA.y()));

        logger.log(Level.DEBUG, "Сторона равна {}", side);

        return side;
    }

    private double[] calculateTriangleSides(Triangle triangle) {
        double sideAB = calculateSide(triangle.getPointA(), triangle.getPointB());
        double sideBC = calculateSide(triangle.getPointB(), triangle.getPointC());
        double sideAC = calculateSide(triangle.getPointA(), triangle.getPointC());

        return new double[]{sideAB, sideBC, sideAC};
    }

    private double roundDouble(double value) {
        return Math.round(value * ROUND_SCALE) / ROUND_SCALE;
    }
}
