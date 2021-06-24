package com.fairycompany.shape.repository.impl;

import com.fairycompany.shape.action.impl.TriangleCalculation;
import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.repository.TriangleSpecification;

public class PerimeterTriangleSpecification implements TriangleSpecification {
    private double fromPerimeter;
    private double toPerimeter;

    public PerimeterTriangleSpecification(double fromPerimeter, double toPerimeter) {
        this.fromPerimeter = fromPerimeter;
        this.toPerimeter = toPerimeter;
    }

    @Override
    public boolean specify(Triangle triangle) {
        TriangleCalculation triangleCalculation = new TriangleCalculation();

        boolean perimeter = (triangleCalculation.calculatePerimeter(triangle) >= fromPerimeter &&
                triangleCalculation.calculatePerimeter(triangle) <= toPerimeter);

        return perimeter;
    }
}
