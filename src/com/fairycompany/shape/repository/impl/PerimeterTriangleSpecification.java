package com.fairycompany.shape.repository.impl;

import com.fairycompany.shape.action.impl.TriangleCalculation;
import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.exception.TriangleException;
import com.fairycompany.shape.repository.TriangleSpecification;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PerimeterTriangleSpecification implements TriangleSpecification {
    private static Logger logger = LogManager.getLogger();
    private double fromPerimeter;
    private double toPerimeter;

    public PerimeterTriangleSpecification(double fromPerimeter, double toPerimeter) {
        this.fromPerimeter = fromPerimeter;
        this.toPerimeter = toPerimeter;
    }

    @Override
    public boolean specify(Triangle triangle) {
        boolean perimeter = false;
        TriangleCalculation triangleCalculation = new TriangleCalculation();

        try {                                                                       // todo what to do
            perimeter = (triangleCalculation.calculatePerimeter(triangle) >= fromPerimeter &&
                            triangleCalculation.calculatePerimeter(triangle) <= toPerimeter);
        } catch (TriangleException e) {
            logger.log(Level.ERROR, "Given triangle is null");
        }

        return perimeter;
    }
}
