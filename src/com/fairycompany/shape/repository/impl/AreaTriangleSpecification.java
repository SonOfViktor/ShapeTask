package com.fairycompany.shape.repository.impl;

import com.fairycompany.shape.action.impl.TriangleCalculation;
import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.repository.TriangleSpecification;

public class AreaTriangleSpecification implements TriangleSpecification {
    private static final double NULL_AREA = 0;
    private double fromArea;
    private double toArea;

    public AreaTriangleSpecification(double fromArea, double toArea) {
        this.fromArea = fromArea;
        this.toArea = toArea;
    }

    @Override
    public boolean specify(Triangle triangle) {
        var triangleCalculation = new TriangleCalculation();

        boolean areaTriangle = triangleCalculation.calculateArea(triangle).orElse(NULL_AREA) >= fromArea &&
                triangleCalculation.calculateArea(triangle).orElse(NULL_AREA) <= toArea;

        return areaTriangle;
    }
}
