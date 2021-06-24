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

public class TriangleReader {
    private static Logger logger = LogManager.getLogger();

    public List<String> readFile(String path) throws TriangleException {
        if (path == null || path.isBlank()) {
            throw new TriangleException(String.format("File name %s is null or empty", path));
        }

        List<String> doubleStringList;
        Path dataFile = Paths.get(path);

        try {
            if (!Files.isReadable(dataFile) || Files.size(dataFile) == 0) {
                throw new TriangleException(String.format("File %s does not exist or is empty", dataFile.getFileName()));
            }

            doubleStringList = Files.lines(dataFile)
                    .collect(Collectors.toList());

            logger.log(Level.INFO, "Read file {} is successful", dataFile.getFileName());

        } catch (IOException e) {
            throw new TriangleException(String.format("Input error during reading file %s", dataFile.getFileName()));
        }

        return doubleStringList;
    }
}
