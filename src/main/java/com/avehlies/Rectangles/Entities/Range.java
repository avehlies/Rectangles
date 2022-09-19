package com.avehlies.Rectangles.Entities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A class used to hold a minimum and maximum value representing a range
 */
public class Range {
    private final BigDecimal min;
    private final BigDecimal max;

    /**
     * Constructs a {@code Range} which could be null/null (invalid) or have a numeric min and max
     * @param min the minimum value on the range ("start")
     * @param max the maximum value on the range ("end")
     * @throws RuntimeException when the min input &gt; max input
     */
    public Range(BigDecimal min, BigDecimal max) throws RuntimeException {
        // A little more complex of a constructor than I'd normally like
        // another option would be:
        // Range::invalid() static function
        // Range::create(BigDecimal, BigDecimal) static function that validates that max >= min
        if (min == null && max == null) {
            this.min = min;
            this.max = max;
        } else if (min == null || max == null) {
            throw new RuntimeException("min and max must both be null or both be non-null");
        } else {
            if (min.compareTo(max) > 0) {
                throw new RuntimeException("Unable to create a Range with min > max");
            }
            this.min = min;
            this.max = max;
        }
    }

    public BigDecimal getMin() {
        return min;
    }

    public BigDecimal getMax() {
        return max;
    }

    /**
     * An "invalid" range is one that has no min/max. This could be because there's no intersection.
     * @return true if there is no valid min/max.
     */
    public boolean isInvalid() {
        return min == null && max == null;
    }

    /**
     * A "Zero Length" range implies that the min == max. Visually, this would represent sides of rectangles
     * simply touching, but not overlapping.
     * @return true if the min and max are the same
     */
    public boolean isZeroLength() {
        if (isInvalid()) {
            return false;
        }
        return min.compareTo(max) == 0;
    }

    /**
     * It's debatable whether a new {@code equalTo} is appropriate to add, or if {@code equals}
     * should be repurposed to do this. I've opted to use {@code equalTo} because the {@link BigDecimal} class
     * does explicitly have a difference between {@link BigDecimal#compareTo} and {@link BigDecimal#equals}.
     * @param range the {@link Range} to compare
     * @return true if the range's values are the same
     */
    public boolean equalTo(Range range) {
        boolean minEquals = (min == null && range.min == null)
                || (min != null && range.min != null && min.compareTo(range.min) == 0);
        boolean maxEquals = (max == null && range.max == null)
                || (max != null && range.max != null && max.compareTo(range.max) == 0);
        return minEquals && maxEquals;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return Objects.equals(min, range.min) && Objects.equals(max, range.max);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }

    @Generated
    @Override
    public String toString() {
        return "Range{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
