package test.fairycompany.shape.action.impl;

import com.fairycompany.shape.action.impl.TriangleCalculation;
import com.fairycompany.shape.entity.Point;
import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.exception.TriangleException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TriangleCalculationTest {
    TriangleCalculation triangleCalculation;
    Triangle triangle;

    @BeforeClass
    public void setUp() throws TriangleException {
        triangleCalculation = new TriangleCalculation();
        triangle = new Triangle(new Point(1.5, 3.5), new Point(3.0, 4.0), new Point(4.8, 3.5));
    }

    @Test
    public void testCalculatePerimeter() {
        double actual = triangleCalculation.calculatePerimeter(triangle).getAsDouble();
        double expected = 6.749;
        assertEquals(actual, expected);
    }

    @Test
    public void testCalculatePerimeterNullTriangle() {
        boolean actual = triangleCalculation.calculatePerimeter(null).isEmpty();
        assertTrue(actual);
    }

    @Test
    public void testCalculateArea() {
        double actual = triangleCalculation.calculateArea(triangle).getAsDouble();
        double expected = 0.825;
        assertEquals(actual, expected);
    }

    @Test
    public void testIsRightTriangle() throws TriangleException {
        Triangle triangle = new Triangle(new Point(0,0), new Point (0, 3), new Point(4, 0));
        boolean actual = triangleCalculation.isRightTriangle(triangle);
        assertTrue(actual);
    }

    @Test
    public void testIsNotRightTriangle() {
        boolean actual = triangleCalculation.isRightTriangle(triangle);
        assertFalse(actual);
    }

    @Test
    public void testIsRightNullTriangle() {
        boolean actual = triangleCalculation.isRightTriangle(null);
        assertFalse(actual);
    }

    @Test
    public void testIsAcuteTriangle() throws TriangleException {
        Triangle triangle = new Triangle(new Point(1,1), new Point (1.5, 2.8), new Point(2.2, 1.2));
        boolean actual = triangleCalculation.isAcuteTriangle(triangle);
        assertTrue(actual);
    }

    @Test
    public void testIsNotAcuteTriangle() {
        boolean actual = triangleCalculation.isAcuteTriangle(triangle);
        assertFalse(actual);
    }

    @Test
    public void testIsAcuteNullTriangle() {
        boolean actual = triangleCalculation.isAcuteTriangle(null);
        assertFalse(actual);
    }

    @Test
    public void testIsObtuseTriangle() {
        boolean actual = triangleCalculation.isObtuseTriangle(triangle);
        assertTrue(actual);
    }

    @Test
    public void testIsNotObtuseTriangle() throws TriangleException {
        Triangle triangle = new Triangle(new Point(1,1), new Point (1.5, 2.8), new Point(2.2, 1.2));
        boolean actual = triangleCalculation.isObtuseTriangle(triangle);
        assertFalse(actual);
    }

    @Test
    public void testIsObtuseNullTriangle() {
        boolean actual = triangleCalculation.isObtuseTriangle(null);
        assertFalse(actual);
    }

    @Test
    public void testIsIsoscelesTriangle() throws TriangleException {
        Triangle triangle = new Triangle(new Point(2, 2), new Point(3.1, 3.8), new Point(4.2, 2));
        boolean actual = triangleCalculation.isIsoscelesTriangle(triangle);
        assertTrue(actual);
    }

    @Test
    public void testIsNotIsoscelesTriangle() {
        boolean actual = triangleCalculation.isIsoscelesTriangle(triangle);
        assertFalse(actual);
    }

    @Test
    public void testIsIsoscelesNullTriangle() {
        boolean actual = triangleCalculation.isIsoscelesTriangle(null);
        assertFalse(actual);
    }

    @Test
    public void testIsEquilateralTriangle() throws TriangleException {
        Triangle triangle = new Triangle(new Point(2.2, 3.2), new Point(2.7, 4.066) ,new Point(3.2, 3.2));
        boolean actual = triangleCalculation.isEquilateralTriangle(triangle);
        assertTrue(actual);
    }

    @Test
    public void testIsNotEquilateralTriangle() {
        boolean actual = triangleCalculation.isEquilateralTriangle(triangle);
        assertFalse(actual);
    }

    @Test
    public void testIsEquilateralNullTriangle() {
        boolean actual = triangleCalculation.isEquilateralTriangle(null);
        assertFalse(actual);
    }
}