package com.fairycompany.shape.observer.impl;

import com.fairycompany.shape.action.impl.TriangleCalculation;
import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.entity.TriangleParameters;
import com.fairycompany.shape.entity.TriangleWarehouse;
import com.fairycompany.shape.exception.TriangleException;
import com.fairycompany.shape.observer.Observer;
import com.fairycompany.shape.observer.TriangleEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TriangleObserver implements Observer {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void parametersChange(TriangleEvent event) throws TriangleException {
        var triangleWarehouse = TriangleWarehouse.getInstance();
        var triangleCalculation = new TriangleCalculation();
        Triangle triangle = event.getSource();

        double perimeter = triangleCalculation.calculatePerimeter(triangle)
                .orElseThrow(() -> new TriangleException("This triangle is null"));
        double area = triangleCalculation.calculateArea(triangle)
                .orElseThrow(() -> new TriangleException("This triangle is null"));
        TriangleParameters triangleParameters = new TriangleParameters(perimeter, area);

        triangleWarehouse.putParameters(triangle.getTriangleId(), triangleParameters);

        logger.log(Level.INFO, "Parameters of the triangle #{} were updated", triangle.getTriangleId());
    }
}
