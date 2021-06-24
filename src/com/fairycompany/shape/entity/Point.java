package com.fairycompany.shape.entity;

public record Point(double X, double Y) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("X = ").append(X).append("\t")
                .append("Y = ").append(Y);
        return sb.toString();
    }
}
