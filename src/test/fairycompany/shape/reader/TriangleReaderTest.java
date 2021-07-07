package test.fairycompany.shape.reader;

import com.fairycompany.shape.exception.TriangleException;
import com.fairycompany.shape.reader.TriangleReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class TriangleReaderTest {
    TriangleReader reader;

    @BeforeClass
    public void setUp() {
        reader = new TriangleReader();
    }

    @Test
    public void testReadFile() throws TriangleException {
        List<String> expected = List.of(
                "2.5 3.4 2.5 6.4 6.5 3.4",
                "-3.8 1.2 -2.3 3.2 -0.8 1.2",
                "2.25 2.25 4.1",
                "",
                "75 53 89 74 12.7 45 18"
        );

        String path = "resources\\test_data\\testdata.txt";

        List<String> actual = reader.readFile(path);

        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = TriangleException.class)
    public void testReadNullFileException() throws TriangleException {
        reader.readFile(null);
    }

    @Test(expectedExceptions = TriangleException.class)
    public void testReadEmptyFileNameException() throws TriangleException {
        String path = " ";
        reader.readFile(path);
    }

    @Test(expectedExceptions = TriangleException.class)
    public void testReadWrongFileException() throws TriangleException {
        String path = "resources\\test_data\\data.txt";
        reader.readFile(path);
    }
}