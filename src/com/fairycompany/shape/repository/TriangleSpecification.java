package com.fairycompany.shape.repository;

import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.exception.TriangleException;

public interface TriangleSpecification {
    boolean specify(Triangle triangle);
}
