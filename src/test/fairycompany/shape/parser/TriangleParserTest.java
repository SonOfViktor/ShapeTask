package test.fairycompany.shape.parser;

import com.fairycompany.shape.exception.TriangleException;
import com.fairycompany.shape.parser.TriangleParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TriangleParserTest {
    TriangleParser triangleParser;

    @BeforeClass
    public void setUp() {
        triangleParser = new TriangleParser();
    }

    @Test
    public void testParseStringListToArray() throws TriangleException {
        List<String> data = List.of(
                "2.5 3.4 2.5 6.4 6.5 3.4",
                "  2.5 3.4 2.5   6.4 6.5   3.4  ",
                "-3.8 1.2 -2.3 3.2 -0.8 1.2",
                "-3. 8 1.2 -2.3 3.2 -0.8 1.2",
                "2.25 2.25 4.1",
                "",
                "2.34 3.58 4.8 3 3.4 58.2  1.2",
                "75 53 89 74 12.7 45"
        );

        List<double[]> actual = triangleParser.parseStringListToArray(data);

        List<double[]> expected = List.of(
                new double[]{2.5, 3.4, 2.5, 6.4, 6.5, 3.4},
                new double[]{2.5, 3.4, 2.5, 6.4, 6.5, 3.4},
                new double[]{-3.8, 1.2, -2.3, 3.2, -0.8, 1.2},
                new double[]{75, 53, 89, 74, 12.7, 45}
        );

        assertThat(actual).containsExactlyElementsOf(expected);
    }


    @Test(expectedExceptions = TriangleException.class)
    public void testParseNullStringListToArray() throws TriangleException {
        List<double[]> doubleArraysList = triangleParser.parseStringListToArray(null);
    }

    @Test(expectedExceptions = TriangleException.class)
    public void testParseEmptyStringListToArray() throws TriangleException {
        List<String> data = new ArrayList<>();
        List<double[]> doubleArraysList = triangleParser.parseStringListToArray(data);
    }
}