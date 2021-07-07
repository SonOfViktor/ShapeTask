package com.fairycompany.shape.parser;

import com.fairycompany.shape.exception.TriangleException;
import com.fairycompany.shape.validator.TriangleValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TriangleParser {
    private static Logger logger = LogManager.getLogger();
    private static final String SPACE_DELIMITER_REGEX = "\\s+";

    public List<double[]> parseStringListToArray(List<String> doubleStringList) throws TriangleException {
        if (doubleStringList == null || doubleStringList.isEmpty()) {
            throw new TriangleException("List is null or hasn't any strings");
        }

        List<double[]> doubleArraysList;

        doubleArraysList = doubleStringList.stream()
                .map(String::trim)
                .filter(TriangleValidator::isTriangleData)
                .peek(line -> logger.log(Level.DEBUG, "{} added", line))
                .map(line -> line.split(SPACE_DELIMITER_REGEX))
                .map(array -> Stream.of(array)
                        .mapToDouble(Double::parseDouble)
                        .toArray())
                .collect(Collectors.toList());

        logger.log(Level.INFO, "Parsing is successful");

        return doubleArraysList;
    }
}
