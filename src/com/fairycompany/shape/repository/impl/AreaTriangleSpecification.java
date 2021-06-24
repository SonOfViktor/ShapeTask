package com.fairycompany.shape.repository.impl;

import com.fairycompany.shape.action.impl.TriangleCalculation;
import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.repository.TriangleSpecification;

public class AreaTriangleSpecification implements TriangleSpecification {
    private double fromArea;
    private double toArea;

    AreaTriangleSpecification(double fromArea, double toArea) {
        this.fromArea = fromArea;
        this.toArea = toArea;
    }

    @Override
    public boolean specify(Triangle triangle) {
        var triangleCalculation = new TriangleCalculation();

        boolean areaTriangle = triangleCalculation.calculateArea(triangle) >= fromArea &&
                triangleCalculation.calculateArea(triangle) <= toArea;

        return areaTriangle;
    }
}
