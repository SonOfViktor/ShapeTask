package com.fairycompany.shape.comparator;

import com.fairycompany.shape.action.impl.TriangleCalculation;
import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.entity.TriangleParameters;
import com.fairycompany.shape.entity.TriangleWarehouse;
import com.fairycompany.shape.exception.TriangleException;
import java.util.Comparator;
import java.util.function.ToDoubleFunction;

import static java.util.Comparator.comparingDouble;
import static java.util.Comparator.nullsLast;


public enum TriangleComparator {
    ID,
    POINT_A,
    POINT_B,
    POINT_C,
    AREA,
    PERIMETER;

    public Comparator<Triangle> getComparator() throws TriangleException {
        return switch (this) {
            case ID -> nullsLast(Comparator.comparingLong(Triangle::getTriangleId));

            case POINT_A -> nullsLast(comparingDouble((ToDoubleFunction<Triangle>) tr -> tr.getPointA().x())
                    .thenComparing(tr -> tr.getPointA().y()));

            case POINT_B -> nullsLast(comparingDouble((ToDoubleFunction<Triangle>) tr -> tr.getPointB().x())
                    .thenComparing(tr -> tr.getPointB().y()));

            case POINT_C -> nullsLast(comparingDouble((ToDoubleFunction<Triangle>) tr -> tr.getPointC().x())
                    .thenComparing(tr -> tr.getPointC().y()));

            case AREA -> nullsLast(comparingDouble((ToDoubleFunction<Triangle>) triangle ->
                    TriangleWarehouse.getInstance()
                            .getParameters(triangle.getTriangleId())
                            .orElseThrow(() ->
                                    new RuntimeException("Warehouse has no this triangle" + triangle.getTriangleId()))
                            .area()));

            case PERIMETER -> nullsLast(comparingDouble((ToDoubleFunction<Triangle>) triangle ->
                            TriangleWarehouse.getInstance()
                            .getParameters(triangle.getTriangleId())
                            .orElseThrow(() ->
                                    new RuntimeException("Warehouse has no this triangle" + triangle.getTriangleId()))
                            .perimeter()));

//            case PERIMETER -> nullsLast(comparingDouble((ToDoubleFunction<? super Triangle>) triangle -> {
//                        var triangleCalculation = new TriangleCalculation();
//                        return triangleCalculation
//                                .calculatePerimeter(triangle)
//                                .orElseThrow(() -> new RuntimeException("It will never be called"));
//                    }
//            ));

//            case PERIMETER -> nullsLast(Comparator.comparingDouble((ToDoubleFunction<Triangle>) tr ->
//                    TriangleWarehouse.getInstance()
//                            .getParameters(tr.getTriangleId())
//                            .orElseGet(() -> {
//                                var triangleCalculation = new TriangleCalculation();
//                                var triangleParameters =
//                                        new TriangleParameters(triangleCalculation.calculatePerimeter(tr).getAsDouble(),
//                                                triangleCalculation.calculateArea(tr).getAsDouble());
//                                TriangleWarehouse.getInstance().putParameters(tr.getTriangleId(), triangleParameters);
//                                return triangleParameters;
//                            })
//                            .perimeter()));

            default -> throw new TriangleException(String.format("%s comparator is absent", this.name()));
        };
    }
}