package com.fairycompany.shape.action;

import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.exception.TriangleException;

public interface ShapeCalculation {
    double calculatePerimeter(Triangle triangle);

    double calculateArea(Triangle triangle);

    public boolean isRightTriangle(Triangle triangle);

    public boolean isIsoscelesTriangle(Triangle triangle);

    public boolean isEquilateralTriangle(Triangle triangle);
}
