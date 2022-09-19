package com.avehlies.Rectangles.Entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.avehlies.Rectangles.TestUtilities.TestUtilities.assertBigDecimalEquals;

public class RectangleTest {

    private static final Rectangle TEST_RECTANGLE = new Rectangle(
            BigDecimal.valueOf(5),
            BigDecimal.valueOf(10),
            BigDecimal.valueOf(15),
            BigDecimal.valueOf(2)
    );

    @Test
    public void testConstructor() {
        Rectangle rectangle = new Rectangle(
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(15),
                BigDecimal.valueOf(2)
        );

        assertBigDecimalEquals(BigDecimal.valueOf(5), rectangle.getLeft());
        assertBigDecimalEquals(BigDecimal.valueOf(10), rectangle.getTop());
        assertBigDecimalEquals(BigDecimal.valueOf(15), rectangle.getRight());
        assertBigDecimalEquals(BigDecimal.valueOf(2), rectangle.getBottom());
    }

    @Test
    public void testHeightAndWidth() {
        assertBigDecimalEquals(BigDecimal.valueOf(10), TEST_RECTANGLE.getWidth());
        assertBigDecimalEquals(BigDecimal.valueOf(8), TEST_RECTANGLE.getHeight());
    }
}
