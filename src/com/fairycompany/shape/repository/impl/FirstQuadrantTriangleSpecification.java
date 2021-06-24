package com.fairycompany.shape.repository.impl;

import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.repository.TriangleSpecification;

public class FirstQuadrantTriangleSpecification implements TriangleSpecification {
    @Override
    public boolean specify(Triangle triangle) {
        boolean PointAFirstQuadrant = triangle.getA().getX() >= 0 && triangle.getA().getY() >= 0;
        boolean PointBFirstQuadrant = triangle.getB().getX() >= 0 && triangle.getA().getY() >= 0;
        boolean PointCFirstQuadrant = triangle.getC().getX() >= 0 && triangle.getA().getY() >= 0;

        return  PointAFirstQuadrant && PointBFirstQuadrant && PointCFirstQuadrant;
    }
}
