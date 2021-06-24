package com.fairycompany.shape.action;

import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.exception.TriangleException;

public interface ShapeCalculation {
    double calculatePerimeter(Triangle triangle) throws TriangleException;

    double calculateArea(Triangle triangle) throws TriangleException;
}
