package test.fairycompany.shape.repository;

import com.fairycompany.shape.action.impl.TriangleCalculation;
import com.fairycompany.shape.comparator.TriangleComparator;
import com.fairycompany.shape.entity.Point;
import com.fairycompany.shape.entity.Triangle;
import com.fairycompany.shape.entity.TriangleParameters;
import com.fairycompany.shape.entity.TriangleWarehouse;
import com.fairycompany.shape.exception.TriangleException;
import com.fairycompany.shape.repository.TriangleRepository;
import com.fairycompany.shape.repository.impl.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TriangleRepositoryTest {
    TriangleRepository triangleQueryRepository;
    TriangleRepository triangleSortRepository;
    List<Triangle> queryTriangles;
    List<Triangle> sortTriangles;
    TriangleCalculation triangleCalculation;
    TriangleWarehouse warehouse;

    @BeforeClass
    public void setUp() throws TriangleException {
        triangleCalculation = new TriangleCalculation();
        warehouse = TriangleWarehouse.getInstance();
        triangleQueryRepository = new TriangleRepository();

        queryTriangles = List.of(
                new Triangle(new Point(2.5, 3.4), new Point(2.5, 6.4), new Point (6.5, 3.4)),
                new Triangle(new Point(-3.8, 1.2), new Point(-2.3, 3.2), new Point (-0.8, 1.2)),
                new Triangle(new Point(0.0, 0.0), new Point(2.5, 4.33), new Point (5.0, 0.0)),
                new Triangle(new Point(-10, -12), new Point(-5, -7.5), new Point (-2.0, -12)),
                new Triangle(new Point(8.3, -15), new Point(10.5, -11.1), new Point (17.6, -16.2)),
                new Triangle(new Point(-3.0, -3), new Point(-3, 3.2), new Point (1.8, -3)),
                new Triangle(new Point(1.0, 53), new Point(89.0, 74.0), new Point (12.7, 45)),
                new Triangle(new Point(1.0, 8.0), new Point(45.3, 7.0), new Point (15.7, 78.3)),
                new Triangle(new Point(9.0, 45.3), new Point(0.8, 15.0), new Point (13.2, 7.0)),
                new Triangle(new Point(3.2, 2.0), new Point(0.0, 0.0), new Point (7.0, 5.0)),
                new Triangle(new Point(-3.0, 7.0), new Point(-1.2, 13.76), new Point (0.6, 7.0)),
                new Triangle(new Point(3.5, -20), new Point(11.0, -7.01), new Point (18.5, -20.0)),
                new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8)),
                new Triangle(new Point(-15.83, 10.2), new Point(-11.0, 11.0), new Point (-7.83, 10.2)),
                new Triangle(new Point(-3.2, -4.8), new Point(-3.2, 6.2), new Point (4.8, -4.8)),
                new Triangle(new Point(-5.0, -10.0), new Point(-4.2, -3.0), new Point (-2.1, -7.8))
        );
        triangleQueryRepository.addAll(queryTriangles);
        triangleQueryRepository.add(null);

        triangleSortRepository = new TriangleRepository();
        sortTriangles = new ArrayList<>();
        Triangle triangle = new Triangle(new Point(8.3, 17.5), new Point(12.0, 16.0), new Point (18.0, 7.3));
        sortTriangles.add(new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8)));
        sortTriangles.add(new Triangle(new Point(-10, -12), new Point(-5, -7.5), new Point (-2.0, -12)));
        sortTriangles.add(new Triangle(new Point(-10, -7), new Point(-3.2, 6.2), new Point (4.8, -4.8)));
        sortTriangles.add(new Triangle(new Point(2.5, 3.4), new Point(-5, 0.0), new Point (6.5, 3.4)));
        sortTriangles.add(new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8)));
        sortTriangles.add(triangle);

        for (Triangle triangles : sortTriangles) {
            warehouse.putParameters(
                    triangles.getTriangleId(), new TriangleParameters(triangleCalculation.calculatePerimeter(triangles).getAsDouble(),
                            triangleCalculation.calculateArea(triangles).getAsDouble()));
        }

        sortTriangles.add(null);
        triangleSortRepository.addAll(sortTriangles);
    }

    @Test
    public void testQueryId() throws TriangleException {
        int from = 5;
        int to = 8;

        List<Triangle> actual = triangleQueryRepository.query(new IDTriangleSpecification(from, to));
        List<Triangle> expected = List.of(
                new Triangle(new Point(8.3, -15), new Point(10.5, -11.1), new Point (17.6, -16.2)),
                new Triangle(new Point(-3.0, -3), new Point(-3, 3.2), new Point (1.8, -3)),
                new Triangle(new Point(1.0, 53), new Point(89.0, 74.0), new Point (12.7, 45)),
                new Triangle(new Point(1.0, 8.0), new Point(45.3, 7.0), new Point (15.7, 78.3))
        );

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testQueryArea() throws TriangleException {
        double from = 50.0;
        double to = 250;

        List<Triangle> actual = triangleQueryRepository.query(new AreaTriangleSpecification(from, to));
        List<Triangle> expected = List.of(
                new Triangle(new Point(9.0, 45.3), new Point(0.8, 15.0), new Point (13.2, 7.0)),
                new Triangle(new Point(3.5, -20), new Point(11.0, -7.01), new Point (18.5, -20.0)),
                new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8))
        );

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testQueryPerimeter() throws TriangleException {
        double from = 20.135;
        double to = 33.0;

        List<Triangle> actual = triangleQueryRepository.query(new PerimeterTriangleSpecification(from, to));
        List<Triangle> expected = List.of(
                new Triangle(new Point(-10, -12), new Point(-5, -7.5), new Point (-2.0, -12)),
                new Triangle(new Point(8.3, -15), new Point(10.5, -11.1), new Point (17.6, -16.2)),
                new Triangle(new Point(-3.2, -4.8), new Point(-3.2, 6.2), new Point (4.8, -4.8))
        );

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testFirstQuadrantQuerySpecification() throws TriangleException {
        List<Triangle> actual = triangleQueryRepository.query(new FirstQuadrantTriangleSpecification());
        List<Triangle> expected = List.of(
                new Triangle(new Point(2.5, 3.4), new Point(2.5, 6.4), new Point (6.5, 3.4)),
                new Triangle(new Point(0.0, 0.0), new Point(2.5, 4.33), new Point (5.0, 0.0)),
                new Triangle(new Point(1.0, 53), new Point(89.0, 74.0), new Point (12.7, 45)),
                new Triangle(new Point(1.0, 8.0), new Point(45.3, 7.0), new Point (15.7, 78.3)),
                new Triangle(new Point(9.0, 45.3), new Point(0.8, 15.0), new Point (13.2, 7.0)),
                new Triangle(new Point(3.2, 2.0), new Point(0.0, 0.0), new Point (7.0, 5.0)),
                new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8))
        );

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testSecondQuadrantQuerySpecification() throws TriangleException {
        List<Triangle> actual = triangleQueryRepository.query(new SecondQuadrantTriangleSpecification());
        List<Triangle> expected = List.of(
                new Triangle(new Point(-3.8, 1.2), new Point(-2.3, 3.2), new Point (-0.8, 1.2)),
                new Triangle(new Point(-15.83, 10.2), new Point(-11.0, 11.0), new Point (-7.83, 10.2))
        );

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testThirdQuadrantQuerySpecification() throws TriangleException {
        List<Triangle> actual = triangleQueryRepository.query(new ThirdQuadrantTriangleSpecification());
        List<Triangle> expected = List.of(
                new Triangle(new Point(-10, -12), new Point(-5, -7.5), new Point (-2.0, -12)),
                new Triangle(new Point(-5.0, -10.0), new Point(-4.2, -3.0), new Point (-2.1, -7.8))
        );

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testForthQuadrantQuerySpecification() throws TriangleException {
        List<Triangle> actual = triangleQueryRepository.query(new ForthQuadrantTriangleSpecification());
        List<Triangle> expected = List.of(
                new Triangle(new Point(8.3, -15), new Point(10.5, -11.1), new Point (17.6, -16.2)),
                new Triangle(new Point(3.5, -20), new Point(11.0, -7.01), new Point (18.5, -20.0))
        );

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testAcuteQuerySpecification() throws TriangleException {
        List<Triangle> actual = triangleQueryRepository.query(new AcuteTriangleSpecification());
        List<Triangle> expected = List.of(
                new Triangle(new Point(-3.8, 1.2), new Point(-2.3, 3.2), new Point (-0.8, 1.2)),
                new Triangle(new Point(0.0, 0.0), new Point(2.5, 4.33), new Point (5.0, 0.0)),
                new Triangle(new Point(-10, -12), new Point(-5, -7.5), new Point (-2.0, -12)),
                new Triangle(new Point(8.3, -15), new Point(10.5, -11.1), new Point (17.6, -16.2)),
                new Triangle(new Point(1.0, 8.0), new Point(45.3, 7.0), new Point (15.7, 78.3)),
                new Triangle(new Point(-3.0, 7.0), new Point(-1.2, 13.76), new Point (0.6, 7.0)),
                new Triangle(new Point(3.5, -20), new Point(11.0, -7.01), new Point (18.5, -20.0)),
                new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8))
        );

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testObtuseQuerySpecification() throws TriangleException {
        List<Triangle> actual = triangleQueryRepository.query(new ObtuseTriangleSpecification());
        List<Triangle> expected = List.of(
                new Triangle(new Point(1.0, 53), new Point(89.0, 74.0), new Point (12.7, 45)),
                new Triangle(new Point(9.0, 45.3), new Point(0.8, 15.0), new Point (13.2, 7.0)),
                new Triangle(new Point(3.2, 2.0), new Point(0.0, 0.0), new Point (7.0, 5.0)),
                new Triangle(new Point(-15.83, 10.2), new Point(-11.0, 11.0), new Point (-7.83, 10.2)),
                new Triangle(new Point(-5.0, -10.0), new Point(-4.2, -3.0), new Point (-2.1, -7.8))
        );

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testRightQuerySpecification() throws TriangleException {
        List<Triangle> actual = triangleQueryRepository.query(new RightTriangleSpecification());
        List<Triangle> expected = List.of(
                new Triangle(new Point(2.5, 3.4), new Point(2.5, 6.4), new Point (6.5, 3.4)),
                new Triangle(new Point(-3.0, -3), new Point(-3, 3.2), new Point (1.8, -3)),
                new Triangle(new Point(-3.2, -4.8), new Point(-3.2, 6.2), new Point (4.8, -4.8))
        );

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testIsoscelesQuerySpecification() throws TriangleException {
        List<Triangle> actual = triangleQueryRepository.query(new IsoscelesTriangleSpecification());
        List<Triangle> expected = List.of(
                new Triangle(new Point(-3.8, 1.2), new Point(-2.3, 3.2), new Point (-0.8, 1.2)),
                new Triangle(new Point(0.0, 0.0), new Point(2.5, 4.33), new Point (5.0, 0.0)),
                new Triangle(new Point(-3.0, 7.0), new Point(-1.2, 13.76), new Point (0.6, 7.0)),
                new Triangle(new Point(3.5, -20), new Point(11.0, -7.01), new Point (18.5, -20.0))
        );

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testEquilateralQuerySpecification() throws TriangleException {
        List<Triangle> actual = triangleQueryRepository.query(new EquilateralTriangleSpecification());
        List<Triangle> expected = List.of(
                new Triangle(new Point(0.0, 0.0), new Point(2.5, 4.33), new Point (5.0, 0.0)),
                new Triangle(new Point(3.5, -20), new Point(11.0, -7.01), new Point (18.5, -20.0))
        );

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testSortId() throws TriangleException {
        List<Triangle> actual = triangleSortRepository.sort(TriangleComparator.ID.getComparator());

        List<Triangle> expected = new ArrayList<>();
        expected.add(new Triangle(new Point(8.3, 17.5), new Point(12.0, 16.0), new Point (18.0, 7.3)));
        expected.add(new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8)));
        expected.add(new Triangle(new Point(-10, -12), new Point(-5, -7.5), new Point (-2.0, -12)));
        expected.add(new Triangle(new Point(-10, -7), new Point(-3.2, 6.2), new Point (4.8, -4.8)));
        expected.add(new Triangle(new Point(2.5, 3.4), new Point(-5, 0.0), new Point (6.5, 3.4)));
        expected.add(new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8)));
        expected.add(null);

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testSortPointA() throws TriangleException {
        List<Triangle> actual = triangleSortRepository.sort(TriangleComparator.POINT_A.getComparator());

        List<Triangle> expected = new ArrayList<>();
        expected.add(new Triangle(new Point(-10, -12), new Point(-5, -7.5), new Point (-2.0, -12)));
        expected.add(new Triangle(new Point(-10, -7), new Point(-3.2, 6.2), new Point (4.8, -4.8)));
        expected.add(new Triangle(new Point(2.5, 3.4), new Point(-5, 0.0), new Point (6.5, 3.4)));
        expected.add(new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8)));
        expected.add(new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8)));
        expected.add(new Triangle(new Point(8.3, 17.5), new Point(12.0, 16.0), new Point (18.0, 7.3)));
        expected.add(null);

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testSortPointB() throws TriangleException {
        List<Triangle> actual = triangleSortRepository.sort(TriangleComparator.POINT_B.getComparator());

        List<Triangle> expected = new ArrayList<>();
        expected.add(new Triangle(new Point(-10, -12), new Point(-5, -7.5), new Point (-2.0, -12)));
        expected.add(new Triangle(new Point(2.5, 3.4), new Point(-5, 0.0), new Point (6.5, 3.4)));
        expected.add(new Triangle(new Point(-10, -7), new Point(-3.2, 6.2), new Point (4.8, -4.8)));
        expected.add(new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8)));
        expected.add(new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8)));
        expected.add(new Triangle(new Point(8.3, 17.5), new Point(12.0, 16.0), new Point (18.0, 7.3)));
        expected.add(null);

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testSortPointC() throws TriangleException {
        List<Triangle> actual = triangleSortRepository.sort(TriangleComparator.POINT_C.getComparator());

        List<Triangle> expected = new ArrayList<>();
        expected.add(new Triangle(new Point(-10, -12), new Point(-5, -7.5), new Point (-2.0, -12)));
        expected.add(new Triangle(new Point(-10, -7), new Point(-3.2, 6.2), new Point (4.8, -4.8)));
        expected.add(new Triangle(new Point(2.5, 3.4), new Point(-5, 0.0), new Point (6.5, 3.4)));
        expected.add(new Triangle(new Point(8.3, 17.5), new Point(12.0, 16.0), new Point (18.0, 7.3)));
        expected.add(new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8)));
        expected.add(new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8)));
        expected.add(null);

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testSortArea() throws TriangleException {
        List<Triangle> actual = triangleSortRepository.sort(TriangleComparator.AREA.getComparator());

        List<Triangle> expected = new ArrayList<>();
        expected.add(new Triangle(new Point(2.5, 3.4), new Point(-5, 0.0), new Point (6.5, 3.4)));
        expected.add(new Triangle(new Point(8.3, 17.5), new Point(12.0, 16.0), new Point (18.0, 7.3)));
        expected.add(new Triangle(new Point(-10, -12), new Point(-5, -7.5), new Point (-2.0, -12)));
        expected.add(new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8)));
        expected.add(new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8)));
        expected.add(new Triangle(new Point(-10, -7), new Point(-3.2, 6.2), new Point (4.8, -4.8)));

        for (Triangle tri : expected) {
            warehouse.putParameters(
                    tri.getTriangleId(), new TriangleParameters(triangleCalculation.calculatePerimeter(tri).getAsDouble(),
                            triangleCalculation.calculateArea(tri).getAsDouble()));
        }

        expected.add(null);

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    public void testSortPerimeter() throws TriangleException {
        List<Triangle> actual = triangleSortRepository.sort(TriangleComparator.PERIMETER.getComparator());

        List<Triangle> expected = new ArrayList<>();
        expected.add(new Triangle(new Point(-10, -12), new Point(-5, -7.5), new Point (-2.0, -12)));
        expected.add(new Triangle(new Point(2.5, 3.4), new Point(-5, 0.0), new Point (6.5, 3.4)));
        expected.add(new Triangle(new Point(8.3, 17.5), new Point(12.0, 16.0), new Point (18.0, 7.3)));
        expected.add(new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8)));
        expected.add(new Triangle(new Point(3.0, 8.0), new Point(8.8, 16.0), new Point (18.0, 8.8)));
        expected.add(new Triangle(new Point(-10, -7), new Point(-3.2, 6.2), new Point (4.8, -4.8)));

        for (Triangle tri : expected) {
            warehouse.putParameters(
                    tri.getTriangleId(), new TriangleParameters(triangleCalculation.calculatePerimeter(tri).getAsDouble(),
                            triangleCalculation.calculateArea(tri).getAsDouble()));
        }

        expected.add(null);

        assertThat(actual).containsExactlyElementsOf(expected);
    }
}