package com.fairycompany.shape.repository.impl;

import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.repository.TriangleSpecification;

public class FirstQuadrantTriangleSpecification implements TriangleSpecification {
    @Override
    public boolean specify(Triangle triangle) {
        boolean PointAFirstQuadrant = triangle.getA().X() >= 0 && triangle.getA().Y() >= 0;
        boolean PointBFirstQuadrant = triangle.getB().X() >= 0 && triangle.getA().Y() >= 0;
        boolean PointCFirstQuadrant = triangle.getC().X() >= 0 && triangle.getA().Y() >= 0;

        return PointAFirstQuadrant && PointBFirstQuadrant && PointCFirstQuadrant;
    }
}
