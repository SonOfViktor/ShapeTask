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
    public void parameterChange(TriangleEvent event) {
        TriangleWarehouse triangleWarehouse = TriangleWarehouse.getInstance();
        TriangleCalculation triangleCalculation = new TriangleCalculation();
        Triangle triangle = event.getSource();

        try {
            double perimeter = triangleCalculation.calculatePerimeter(triangle);
            double area = triangleCalculation.calculateArea(triangle);
            TriangleParameters triangleParameters = new TriangleParameters(perimeter, area);

            triangleWarehouse.putParameters(triangle.getTriangleID(), triangleParameters);

            logger.log(Level.INFO, "Parameters of the triangle #{} were updated", triangle.getTriangleID());
        } catch (TriangleException e) {
            logger.log(Level.ERROR, "Parameters of the triangle #{} were not updated", triangle.getTriangleID());
        }
    }
}
