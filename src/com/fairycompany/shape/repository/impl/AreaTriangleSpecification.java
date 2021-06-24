package com.fairycompany.shape.repository.impl;

import com.fairycompany.shape.action.impl.TriangleCalculation;
import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.exception.TriangleException;
import com.fairycompany.shape.repository.TriangleSpecification;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AreaTriangleSpecification implements TriangleSpecification {
    private static Logger logger = LogManager.getLogger();
    private double fromArea;
    private double toArea;

    AreaTriangleSpecification(double fromArea, double toArea) {
        this.fromArea = fromArea;
        this.toArea = toArea;
    }

    @Override
    public boolean specify(Triangle triangle) {
        boolean areaTriangle = false;
        TriangleCalculation triangleCalculation = new TriangleCalculation();

        try {                                                                       // todo what to do
            areaTriangle = (triangleCalculation.calculateArea(triangle) >= fromArea &&
                    triangleCalculation.calculateArea(triangle) <= toArea);
        } catch (TriangleException e) {
            logger.log(Level.ERROR, "Given triangle is null");
        }

        return areaTriangle;
    }
}
