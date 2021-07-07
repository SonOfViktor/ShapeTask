package com.fairycompany.shape.action;

import com.fairycompany.shape.entity.Triangle;

import java.util.OptionalDouble;

public interface ShapeCalculation {
    OptionalDouble calculatePerimeter(Triangle triangle);

    OptionalDouble calculateArea(Triangle triangle);

    boolean isRightTriangle(Triangle triangle);

    boolean isAcuteTriangle(Triangle triangle);

    boolean isObtuseTriangle(Triangle triangle);

    boolean isIsoscelesTriangle(Triangle triangle);

    boolean isEquilateralTriangle(Triangle triangle);
}
