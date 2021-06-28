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
            case ID -> Comparator.comparingLong(Triangle::getTriangleId);

            case POINT_A -> Comparator.comparingDouble((ToDoubleFunction<Triangle>) tr -> tr.getPointA().x())
                    .thenComparing(tr -> tr.getPointA().y());

            case POINT_B -> Comparator.comparingDouble((ToDoubleFunction<Triangle>) tr -> tr.getPointB().x())
                    .thenComparing(tr -> tr.getPointB().y());

            case POINT_C -> Comparator.comparingDouble((ToDoubleFunction<Triangle>) tr -> tr.getPointC().x())
                    .thenComparing(tr -> tr.getPointC().y());

            case AREA -> Comparator.comparingDouble((ToDoubleFunction<Triangle>) tr ->
                    TriangleWarehouse.getInstance()
                            .getParameters(tr.getTriangleId())
                            .area());

            case PERIMETER -> Comparator.comparingDouble((ToDoubleFunction<Triangle>) tr ->
                    TriangleWarehouse.getInstance()
                            .getParameters(tr.getTriangleId())
                            .perimeter());

            //todo default
        };
    }
}
