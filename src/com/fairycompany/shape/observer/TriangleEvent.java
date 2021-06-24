package com.fairycompany.shape.observer;

import com.fairycompany.shape.entity.Triangle;

import java.util.EventObject;

public class TriangleEvent extends EventObject {
    public TriangleEvent (Triangle source) {
        super(source);
    }

    @Override
    public Triangle getSource() {
        return (Triangle)super.getSource();
    }
}
