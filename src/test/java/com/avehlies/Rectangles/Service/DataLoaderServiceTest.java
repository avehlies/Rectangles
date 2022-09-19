package com.avehlies.Rectangles.Service;

import com.avehlies.Rectangles.Entities.Pair;
import com.avehlies.Rectangles.Entities.Rectangle;
import com.avehlies.Rectangles.Utilities.MathUtility;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.avehlies.Rectangles.TestUtilities.TestUtilities.rect;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class DataLoaderServiceTest {

    private final BigDecimal FIVE = new BigDecimal(5);

    private CsvReader csvReaderMock = mock(CsvReader.class);

    private DataLoaderService service = new DataLoaderService(csvReaderMock);

    @Test
    public void testFileNotFound() throws FileNotFoundException {
        when(csvReaderMock.read("file.csv"))
                .thenThrow(FileNotFoundException.class);

        List<Pair<Rectangle>> result = service.createRectanglesFromFile("file.csv");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testCreateRectanglesFromFile() throws FileNotFoundException {
        List<String[]> linesInFile = new ArrayList<>();
        linesInFile.add(new String[]{"a", "b", "c", "d", "e", "f", "g", "h"});
        linesInFile.add(new String[]{"1", "2", "3", "0", "6", "7", "8", "5"});
        linesInFile.add(new String[]{"6", "7", "8", "5", "1", "2", "3", "0"});
        when(csvReaderMock.read("file.csv"))
                .thenReturn(linesInFile);

        List<Pair<Rectangle>> expectedResult = new ArrayList<>();
        expectedResult.add(new Pair<>(rect(1, 2, 3, 0), rect(6, 7, 8, 5)));
        expectedResult.add(new Pair<>(rect(6, 7, 8, 5), rect(1, 2, 3, 0)));

        List<Pair<Rectangle>> actualResult = service.createRectanglesFromFile("file.csv");
        assertEquals(2, actualResult.size());

        for (int i = 0; i < 2; i++) {
            // Use our Rectangle::equalTo method to compare rectangles
            assertTrue(expectedResult.get(i).getLeft().equalTo(actualResult.get(i).getLeft()));
            assertTrue(expectedResult.get(i).getRight().equalTo(actualResult.get(i).getRight()));
        }
    }

    @Test
    public void testCreateSampleRectangles() {
        List<Pair<Rectangle>> rectangles = service.createSampleRectangles();

        for (Pair<Rectangle> pair : rectangles) {
            for (Rectangle rect : Arrays.asList(pair.getLeft(), pair.getRight())) {
                assertTrue(MathUtility.gte(rect.getLeft(), BigDecimal.ZERO));
                assertTrue(MathUtility.lte(rect.getLeft(), FIVE));
                assertTrue(MathUtility.gte(rect.getTop(), FIVE));
                assertTrue(MathUtility.lte(rect.getTop(), BigDecimal.TEN));
                assertTrue(MathUtility.gte(rect.getBottom(), BigDecimal.ZERO));
                assertTrue(MathUtility.lte(rect.getBottom(), FIVE));
                assertTrue(MathUtility.gte(rect.getRight(), FIVE));
                assertTrue(MathUtility.lte(rect.getRight(), BigDecimal.TEN));
            }
        }
    }
}
