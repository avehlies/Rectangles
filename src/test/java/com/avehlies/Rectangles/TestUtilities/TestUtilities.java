package com.avehlies.Rectangles.TestUtilities;

import com.avehlies.Rectangles.Entities.Point;
import com.avehlies.Rectangles.Entities.Range;
import com.avehlies.Rectangles.Entities.Rectangle;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public final class TestUtilities {

    public static void assertBigDecimalEquals(BigDecimal expected, BigDecimal actual) {
        if ((expected == null && actual == null)) {
            return;
        }

        if (expected == null || actual == null) {
            fail("The BigDecimal values are not equal.");
        }

        assertEquals(0, expected.compareTo(actual), "The BigDecimal values are not equal.");
    }

    public static Range range(Integer min, Integer max) {
        return new Range(
                (min == null) ? null : new BigDecimal(min),
                (max == null) ? null : new BigDecimal(max)
        );
    }

    public static Rectangle rect(int left, int top, int right, int bottom) {
        return new Rectangle(
                new BigDecimal(left),
                new BigDecimal(top),
                new BigDecimal(right),
                new BigDecimal(bottom)
        );
    }

    public static Point point(int x, int y) {
        return new Point(
                new BigDecimal(x),
                new BigDecimal(y)
        );
    }
}
