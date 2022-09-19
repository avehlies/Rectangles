package com.avehlies.Rectangles.Entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.avehlies.Rectangles.TestUtilities.TestUtilities.assertBigDecimalEquals;

public class PointTest {

    @Test
    public void testConstructor() {
        Point point = new Point(BigDecimal.ONE, BigDecimal.TEN);
        assertBigDecimalEquals(point.getX(), BigDecimal.valueOf(1));
        assertBigDecimalEquals(point.getY(), BigDecimal.valueOf(10));
    }
}
