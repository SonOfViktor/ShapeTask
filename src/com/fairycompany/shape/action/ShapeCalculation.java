package com.fairycompany.shape.action;

import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.exception.TriangleException;

import java.util.OptionalDouble;

public interface ShapeCalculation {
    OptionalDouble calculatePerimeter(Triangle triangle);

    OptionalDouble calculateArea(Triangle triangle);

    public boolean isRightTriangle(Triangle triangle);

    public boolean isIsoscelesTriangle(Triangle triangle);

    public boolean isEquilateralTriangle(Triangle triangle);
}
