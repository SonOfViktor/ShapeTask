package com.fairycompany.shape.entity;

// import com.fairycompany.shape.exception.TriangleException;

import java.util.HashMap;
import java.util.Map;

public class TriangleWarehouse {
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

    public void putParameters(long id, double perimeter, double area) {
        instance.triangleMap.put(id, new TriangleParameters(perimeter, area));
    }

    public TriangleParameters getParameters(long id) { //throws TriangleException {
        TriangleParameters data = instance.triangleMap.get(id);

//        if (data == null) {
//            throw new TriangleException("Given id is wrong");
//        }

        return data;                                        // todo clone
    }
}
