package com.avehlies.Rectangles.TestUtilities;

import com.avehlies.Rectangles.Entities.Point;
import com.avehlies.Rectangles.Entities.Range;
import com.avehlies.Rectangles.Entities.Rectangle;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Some small utilities for our tests
 */
public final class TestUtilities {

    /**
     * Implement a very basic assert for comparing two {@link BigDecimal} values for equality
     * @param expected the expected value
     * @param actual the actual value
     */
    public static void assertBigDecimalEquals(BigDecimal expected, BigDecimal actual) {
        if ((expected == null && actual == null)) {
            return;
        }

        if (expected == null || actual == null) {
            fail("The BigDecimal values are not equal.");
        }

        assertEquals(0, expected.compareTo(actual), "The BigDecimal values are not equal.");
    }

    /**
     * Helper to create {@link Range} from {@code Integer} values
     * @param min the min value
     * @param max the max value
     * @return a populated {@code Range}
     */
    public static Range range(Integer min, Integer max) {
        return new Range(
                (min == null) ? null : new BigDecimal(min),
                (max == null) ? null : new BigDecimal(max)
        );
    }

    /**
     * Helper to create {@link Rectangle} from {@code Integer} values
     * @param left the left value
     * @param top the top value
     * @param right the right value
     * @param bottom the bottom value
     * @return a populated {@code Rectangle}
     */
    public static Rectangle rect(int left, int top, int right, int bottom) {
        return new Rectangle(
                new BigDecimal(left),
                new BigDecimal(top),
                new BigDecimal(right),
                new BigDecimal(bottom)
        );
    }

    /**
     * Helper to create {@link Point} from {@code Integer} values
     * @param x the x value
     * @param y the y value
     * @return a populated {@code Point}
     */
    public static Point point(int x, int y) {
        return new Point(
                new BigDecimal(x),
                new BigDecimal(y)
        );
    }
}
