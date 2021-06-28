package com.fairycompany.shape.entity;

public record Point(double x, double y) {

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("x = ").append(x).append("\t")
                .append("y = ").append(y);
        return stringBuilder.toString();
    }
}
