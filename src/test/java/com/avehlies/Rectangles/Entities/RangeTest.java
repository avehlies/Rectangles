package com.avehlies.Rectangles.Entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.avehlies.Rectangles.TestUtilities.TestUtilities.assertBigDecimalEquals;
import static com.avehlies.Rectangles.TestUtilities.TestUtilities.range;
import static org.junit.jupiter.api.Assertions.*;

public class RangeTest {

    @Test
    public void testConstructor() {
        Range range = new Range(BigDecimal.ONE, BigDecimal.TEN);
        assertBigDecimalEquals(range.getMin(), BigDecimal.valueOf(1));
        assertBigDecimalEquals(range.getMax(), BigDecimal.valueOf(10));
    }

    @Test
    public void testIsInvalid() {
        Range invalid = new Range(null, null);
        assertTrue(invalid.isInvalid());

        Range valid = range(1, 5);
        assertFalse(valid.isInvalid());
    }

    @Test
    public void testEqualTo() {
        Range invalid = new Range(null, null);
        Range invalid2 = new Range(null, null);
        assertTrue(invalid.equalTo(invalid2));

        Range valid = range(1, 5);
        assertFalse(valid.equalTo(invalid));

        Range valid2 = range(1, 4);
        Range valid3 = range(2, 5);
        assertFalse(valid.equalTo(valid2));
        assertFalse(valid.equalTo(valid3));
    }
}
