package com.fairycompany.shape.repository.impl;

import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.repository.TriangleSpecification;

public class FirstQuadrantTriangleSpecification implements TriangleSpecification {
    @Override
    public boolean specify(Triangle triangle) {
        boolean pointAFirstQuadrant = triangle.getPointA().X() >= 0 && triangle.getPointA().Y() >= 0;
        boolean pointBFirstQuadrant = triangle.getPointB().X() >= 0 && triangle.getPointA().Y() >= 0;
        boolean pointCFirstQuadrant = triangle.getPointC().X() >= 0 && triangle.getPointA().Y() >= 0;

        return pointAFirstQuadrant && pointBFirstQuadrant && pointCFirstQuadrant;
    }
}
