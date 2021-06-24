package com.fairycompany.shape.comparator;

import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.entity.TriangleWarehouse;

import java.util.Comparator;
import java.util.function.ToDoubleFunction;

public enum TriangleComparator {
    ID,
    POINT_A,
    POINT_B,
    POINT_C,
    AREA,
    PERIMETER;

    public Comparator<Triangle> getComparator() {
        return switch (this) {
            case ID -> Comparator.comparingLong(Triangle::getTriangleID);

            case POINT_A -> Comparator.comparingDouble((ToDoubleFunction<Triangle>) tr -> tr.getA().X())
                    .thenComparing(tr -> tr.getA().Y());

            case POINT_B -> Comparator.comparingDouble((ToDoubleFunction<Triangle>) tr -> tr.getB().X())
                    .thenComparing(tr -> tr.getB().Y());

            case POINT_C -> Comparator.comparingDouble((ToDoubleFunction<Triangle>) tr -> tr.getC().X())
                    .thenComparing(tr -> tr.getC().Y());

            case AREA -> Comparator.comparingDouble((ToDoubleFunction<Triangle>) tr ->
                    TriangleWarehouse.getInstance()
                            .getParameters(tr.getTriangleID())
                            .area());

            case PERIMETER -> Comparator.comparingDouble((ToDoubleFunction<Triangle>) tr ->
                    TriangleWarehouse.getInstance()
                            .getParameters(tr.getTriangleID())
                            .perimeter());
        };
    }
}
