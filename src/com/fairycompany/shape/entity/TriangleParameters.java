package com.fairycompany.shape.entity;

import java.util.OptionalDouble;

public record TriangleParameters(double perimeter, double area) {

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("Perimeter: ").append(perimeter)
                .append("Area: ").append(area);
        return stringBuilder.toString();
    }
}
