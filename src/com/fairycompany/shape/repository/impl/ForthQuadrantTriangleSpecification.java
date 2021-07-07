package com.fairycompany.shape.repository.impl;

import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.repository.TriangleSpecification;

public class ForthQuadrantTriangleSpecification implements TriangleSpecification {
    @Override
    public boolean specify(Triangle triangle) {
        if(triangle == null) {
            return false;
        }

        boolean pointAFirstQuadrant = triangle.getPointA().x() >= 0 && triangle.getPointA().y() <= 0;
        boolean pointBFirstQuadrant = triangle.getPointB().x() >= 0 && triangle.getPointB().y() <= 0;
        boolean pointCFirstQuadrant = triangle.getPointC().x() >= 0 && triangle.getPointC().y() <= 0;

        return pointAFirstQuadrant && pointBFirstQuadrant && pointCFirstQuadrant;
    }
}
