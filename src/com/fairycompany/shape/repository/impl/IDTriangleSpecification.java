package com.fairycompany.shape.repository.impl;

import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.repository.TriangleSpecification;

public class IDTriangleSpecification implements TriangleSpecification {
    private long fromID;
    private long toId;

    public IDTriangleSpecification(long fromID, long toID) {
        this.fromID = fromID;
        this.toId = toID;
    }

    @Override
    public boolean specify(Triangle triangle) {
        boolean result = triangle.getTriangleID() >= fromID && triangle.getTriangleID() <= toId;
        return result;
    }
}
