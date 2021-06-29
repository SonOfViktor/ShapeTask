package com.fairycompany.shape.observer;

import com.fairycompany.shape.exception.TriangleException;

public interface Observer {
    void parametersChange(TriangleEvent event) throws TriangleException;
}
