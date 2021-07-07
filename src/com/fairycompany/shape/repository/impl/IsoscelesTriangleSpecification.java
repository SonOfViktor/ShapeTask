package com.fairycompany.shape.repository.impl;

import com.fairycompany.shape.action.impl.TriangleCalculation;
import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.repository.TriangleSpecification;

public class IsoscelesTriangleSpecification implements TriangleSpecification {
    @Override
    public boolean specify(Triangle triangle) {

        var triangleCalculation = new TriangleCalculation();

        return triangleCalculation.isIsoscelesTriangle(triangle);
    }
}
