package com.fairycompany.shape.comparator;

import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.entity.TriangleWarehouse;

import java.util.Comparator;
import java.util.function.ToDoubleFunction;
import java.util.function.ToLongFunction;

public enum TriangleComparator {
    ID,
    POINT_A,
    POINT_B,
    POINT_C,
    AREA,
    PERIMETER;

    public Comparator<Triangle> getComparator() {
        return switch(this) {

            case POINT_A -> Comparator.comparingDouble((ToDoubleFunction<Triangle>) tr -> tr.getA().getX())
                                        .thenComparing(tr -> tr.getA().getY());

            case POINT_B -> Comparator.comparingDouble((ToDoubleFunction<Triangle>) tr -> tr.getB().getX())
                                        .thenComparing(tr -> tr.getB().getY());

            case POINT_C -> Comparator.comparingDouble((ToDoubleFunction<Triangle>) tr -> tr.getC().getX())
                                        .thenComparing(tr -> tr.getC().getY());

            case AREA -> Comparator.comparingDouble((ToDoubleFunction<Triangle>) tr ->
                        TriangleWarehouse.getInstance()
                                         .getParameters(tr.getTriangleID())
                                         .getArea());

            case PERIMETER -> Comparator.comparingDouble((ToDoubleFunction<Triangle>) tr ->
                        TriangleWarehouse.getInstance()
                                         .getParameters(tr.getTriangleID())
                                         .getPerimeter());

            default -> Comparator.comparingLong(Triangle::getTriangleID); // todo ask how it works it's not static
        };
    }
}
