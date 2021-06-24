package com.fairycompany.shape.entity;

public record TriangleParameters(double perimeter, double area) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Perimeter: ").append(perimeter)
                .append("Area: ").append(area);
        return sb.toString();
    }
}
