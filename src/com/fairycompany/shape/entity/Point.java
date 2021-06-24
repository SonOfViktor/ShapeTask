package com.fairycompany.shape.entity;

public record Point(double X, double Y) {

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("X = ").append(X).append("\t")
                .append("Y = ").append(Y);
        return stringBuilder.toString();
    }
}
