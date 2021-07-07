package com.fairycompany.shape.repository.impl;

import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.repository.TriangleSpecification;

public class IDTriangleSpecification implements TriangleSpecification {
    private long fromId;
    private long toId;

    public IDTriangleSpecification(long fromId, long toId) {
        this.fromId = fromId;
        this.toId = toId;
    }

    @Override
    public boolean specify(Triangle triangle) {
        boolean result = false;

        if (triangle == null) {
            return result;
        }

        result = triangle.getTriangleId() >= fromId && triangle.getTriangleId() <= toId;

        return result;
    }
}
