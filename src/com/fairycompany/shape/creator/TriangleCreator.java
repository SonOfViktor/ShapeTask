package com.fairycompany.shape.creator;

import com.fairycompany.shape.entity.Point;
import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.exception.TriangleException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TriangleCreator {
    private static Logger logger = LogManager.getLogger();

    public List<Triangle> createTriangleList(List<double[]> doubleArraysList) throws TriangleException {
        ArrayList<Triangle> TriangleArrayList = new ArrayList<>();

        if (doubleArraysList == null || doubleArraysList.isEmpty()) {
            throw new TriangleException("Given list is null or hasn't any arrays");
        }

        for (double[] coordinateArray : doubleArraysList) {
            Triangle triangle = createTriangle(coordinateArray);
            TriangleArrayList.add(triangle);
        }

        logger.log(Level.INFO, "Creating Triangle list is successful");

        return TriangleArrayList;
    }

    public Triangle createTriangle(double[] coordinateArray) throws TriangleException {
        if (coordinateArray.length != 6) {
            throw new TriangleException(String.format("Parameters %s aren't correct", Arrays.toString(coordinateArray)));
        } // todo or separate String variable?

        Triangle triangle = new Triangle(new Point(coordinateArray[0], coordinateArray[1]),
                new Point(coordinateArray[2], coordinateArray[3]),
                new Point(coordinateArray[4], coordinateArray[5]));

        logger.log(Level.INFO, "Creating Triangle is successful");

        return triangle;
    }
}
