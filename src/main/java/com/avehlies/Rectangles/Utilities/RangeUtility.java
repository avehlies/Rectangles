package com.avehlies.Rectangles.Utilities;

import com.avehlies.Rectangles.Entities.Range;

import java.math.BigDecimal;

/**
 * Range-related functions that can be used between all detection strategies
 */
public final class RangeUtility {

    /**
     * Calculates the value of an intersected {@code Range}
     * @param a the first range
     * @param b the second range
     * @return a {@code Range}
     */
    public static Range getIntersectedRange(Range a, Range b) {
        BigDecimal min = MathUtility.max(a.getMin(), b.getMin());
        BigDecimal max = MathUtility.min(a.getMax(), b.getMax());
        // if the min is greater than the max, then there is *NO* intersection, and we have an Invalid Range
        if (MathUtility.gt(min, max)) {
            return new Range(null, null);
        }

        return new Range(min, max);
    }

}
