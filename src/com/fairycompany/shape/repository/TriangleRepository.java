package com.fairycompany.shape.repository;

import com.fairycompany.shape.entity.Triangle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TriangleRepository {

    private List<Triangle> triangles = new ArrayList<>();

    public boolean add(Triangle triangle) {
        return triangles.add(triangle);
    }

    public boolean addAll(List<Triangle> triangleList) {
        return triangles.addAll(triangleList);
    }

    public void clear() {
        triangles.clear();
    }

    public boolean contains(Triangle triangle) {
        return triangles.contains(triangle);
    }

    public Triangle get(int index) {
        return triangles.get(index);
    }

    public int indexOf(Triangle triangle) {
        return triangles.indexOf(triangle);
    }

    public Triangle remove(int index) {
        return triangles.remove(index);
    }

    public boolean remove(Triangle triangle) {
        return triangles.remove(triangle);
    }

    public Triangle set(int index, Triangle triangle) {
        return triangles.set(index, triangle);
    }

    public int size() {
        return triangles.size();
    }

    public List<Triangle> query(TriangleSpecification triangleSpecification) {
        return triangles.stream().filter(triangleSpecification::specify).collect(Collectors.toList());
    }

    public List<Triangle> sort(Comparator<? super Triangle> comparator) {
        List<Triangle> trianglesClone = new ArrayList<>(triangles);
        trianglesClone.sort(comparator);
        return trianglesClone;
    }

    public boolean equals(Object o) {
        return triangles.equals(o);
    }

    public int hashCode() {
        return triangles.hashCode();
    }
}
