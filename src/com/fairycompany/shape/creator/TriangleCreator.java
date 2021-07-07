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
import java.util.Objects;
import java.util.stream.Collectors;

public class TriangleCreator {
    private static Logger logger = LogManager.getLogger();
    private static final int VALID_AMOUNT_OF_COORDINATE = 6;

    public List<Triangle> createTriangleList(List<double[]> doubleArraysList) throws TriangleException {
        List<Triangle> triangleArrayList = new ArrayList<>();

        if (doubleArraysList == null || doubleArraysList.isEmpty()) {
            throw new TriangleException("Given list is null or hasn't any arrays");
        }

        for (double[] coordinateArray : doubleArraysList) {
            Triangle triangle = createTriangle(coordinateArray);
            triangleArrayList.add(triangle);
        }

        logger.log(Level.INFO, "Creating Triangle list is successful");

        return triangleArrayList;
    }

    public Triangle createTriangle(double[] coordinateArray) throws TriangleException {
        if (coordinateArray == null || coordinateArray.length != VALID_AMOUNT_OF_COORDINATE) {
            throw new TriangleException(String.format("Parameters %s aren't correct", Arrays.toString(coordinateArray)));
        }

        var triangle = new Triangle(new Point(coordinateArray[0], coordinateArray[1]),
                new Point(coordinateArray[2], coordinateArray[3]),
                new Point(coordinateArray[4], coordinateArray[5]));

        logger.log(Level.INFO, "Creating Triangle is successful");

        return triangle;
    }
}
