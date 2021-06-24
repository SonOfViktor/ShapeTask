package com.fairycompany.shape.repository.impl;

import com.fairycompany.shape.action.impl.TriangleCalculation;
import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.repository.TriangleSpecification;

public class IsoscelesTriangleTriangleSpecification implements TriangleSpecification {
    @Override
    public boolean specify(Triangle triangle) {

        boolean isoscelesTriangle = new TriangleCalculation().isIsoscelesTriangle(triangle);

        return isoscelesTriangle;
    }
}
