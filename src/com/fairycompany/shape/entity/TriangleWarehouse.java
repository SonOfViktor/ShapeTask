package com.fairycompany.shape.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;

public class TriangleWarehouse {
    private static Logger logger = LogManager.getLogger();
    private static TriangleWarehouse instance;
    private Map<Long, TriangleParameters> triangleMap = new HashMap<>();

    private TriangleWarehouse() {
    }

    public static TriangleWarehouse getInstance() {
        if (instance == null) {
            instance = new TriangleWarehouse();
        }
        return instance;
    }

    public Map<Long, TriangleParameters> getTriangleMap() {
        return Map.copyOf(triangleMap);
    }

    public void putParameters(long id, TriangleParameters triangleParameters) {
        instance.triangleMap.put(id, triangleParameters);
    }

    public Optional<TriangleParameters> getParameters(long id) {
        Optional<TriangleParameters> parameters = Optional.ofNullable(instance.triangleMap.get(id));

        return parameters;
    }
}
