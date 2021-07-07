package test.fairycompany.shape.creator;

import com.fairycompany.shape.creator.TriangleCreator;
import com.fairycompany.shape.entity.Point;
import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.exception.TriangleException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

public class TriangleCreatorTest {
    TriangleCreator triangleCreator;

    @BeforeClass
    public void setUp() {
        triangleCreator = new TriangleCreator();
    }

    @Test
    public void testCreateTriangleList() throws TriangleException {
        List<double[]> doubleArraysList = List.of(
                new double[]{2.5, 3.4, 2.5, 6.4, 6.5, 3.4},
                new double[]{-3.8, 1.2, -2.3, 3.2, -0.8, 1.2},
                new double[]{75, 53, 89, 74, 12.7, 45});

        List<Triangle> actual = triangleCreator.createTriangleList(doubleArraysList);

        List<Triangle> expected = List.of(
                new Triangle(new Point(2.5, 3.4), new Point(2.5, 6.4), new Point (6.5, 3.4)),
                new Triangle(new Point(-3.8, 1.2), new Point(-2.3, 3.2), new Point (-0.8, 1.2)),
                new Triangle(new Point(75, 53), new Point(89, 74), new Point (12.7, 45))
        );

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test(expectedExceptions = TriangleException.class)
    public void testCreateTriangleListNullDoubleList() throws TriangleException {
        List<Triangle> triangles = triangleCreator.createTriangleList(null);
    }

    @Test(expectedExceptions = TriangleException.class)
    public void testCreateTriangleListEmptyDoubleList() throws TriangleException {
        List<double[]> empty = new ArrayList<>();
        List<Triangle> triangles = triangleCreator.createTriangleList(empty);
    }

    @Test
    public void testCreateTriangle() throws TriangleException {
        double[] array = {1.5, 1, 2.5, 2, 3.5, 1};
        Triangle actual = triangleCreator.createTriangle(array);
        Triangle expected = new Triangle(new Point(1.5, 1), new Point(2.5, 2), new Point(3.5, 1));
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = TriangleException.class)
    public void testCreateTriangleNullArray() throws TriangleException {
        Triangle triangle = triangleCreator.createTriangle(null);
    }

    @Test(expectedExceptions = TriangleException.class)
    public void testCreateTriangleLessSixElementArray() throws TriangleException {
        Triangle triangle = triangleCreator.createTriangle(new double[] {2, 3, 4.3});
    }

    @Test(expectedExceptions = TriangleException.class)
    public void testCreateTriangleMoreSixElementArray() throws TriangleException {
        Triangle triangle = triangleCreator.createTriangle(new double[] {2, 3, 4.3, 4.5, 2, 9.3, 10});
    }
}