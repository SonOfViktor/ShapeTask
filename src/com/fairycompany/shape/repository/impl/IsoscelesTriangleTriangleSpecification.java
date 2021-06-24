package com.fairycompany.shape.repository.impl;

import com.fairycompany.shape.action.impl.TriangleCalculation;
import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.exception.TriangleException;
import com.fairycompany.shape.repository.TriangleSpecification;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IsoscelesTriangleTriangleSpecification implements TriangleSpecification {
    public static Logger logger = LogManager.getLogger();
    @Override
    public boolean specify(Triangle triangle){
        boolean isoscelesTriangle = false;

        try {                                                                  //todo what to do
            isoscelesTriangle = new TriangleCalculation().isIsoscelesTriangle(triangle);
        } catch (TriangleException e) {
            logger.log(Level.ERROR, "Given triangle is null");
        }

        return isoscelesTriangle;
    }
}
