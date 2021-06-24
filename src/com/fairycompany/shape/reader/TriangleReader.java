package com.fairycompany.shape.reader;

import com.fairycompany.shape.exception.TriangleException;
import com.fairycompany.shape.validator.TriangleValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TriangleReader {
    private static Logger logger = LogManager.getLogger();

    public List<String> readFile(String path) throws TriangleException {
        if (path == null) {
            logger.log(Level.ERROR, "Name of file is null");
            throw new TriangleException();
        }

        List<String> doubleStringList = new ArrayList<>();
        Path dataFile = Paths.get(path);

        try {
            if (!Files.isReadable(dataFile) || Files.size(dataFile) == 0) {
                logger.log(Level.ERROR, "File {} does not exist or is empty", dataFile.getFileName());
                throw new TriangleException();
            }

            doubleStringList = Files.lines(dataFile)
                    .collect(Collectors.toList());

            logger.log(Level.INFO, "Read file {} is successful", dataFile.getFileName());

        } catch (IOException e) {
            logger.log(Level.ERROR, "Input error during reading file {}", path);
        }

        return doubleStringList;
    }
}
