package com.fairycompany.shape.reader;

import com.fairycompany.shape.exception.TriangleException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TriangleReader {
    private static Logger logger = LogManager.getLogger();

    public List<String> readFile(String path) throws TriangleException {
        if (path == null || path.isBlank()) {
            throw new TriangleException(String.format("File name %s is null or empty", path));
        }

        List<String> doubleStringList;
        Path dataFile = Paths.get(path);

        try (Stream<String> dataStream = Files.lines(dataFile)){

            doubleStringList = dataStream
                    .collect(Collectors.toList());

            logger.log(Level.INFO, "Read file {} is successful", dataFile.getFileName());

        } catch (IOException e) {
            throw new TriangleException(String.format("Input error during reading file %s", dataFile.getFileName()), e);
        }

        return doubleStringList;
    }
}
