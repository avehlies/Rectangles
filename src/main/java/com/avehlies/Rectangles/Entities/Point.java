package com.avehlies.Rectangles.Entities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A class that represents a single {@code Point} as a cartesian coordinate
 */
public class Point {
    private final BigDecimal x;
    private final BigDecimal y;

    public Point(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    /**
     * It's debatable whether a new {@code equalTo} is appropriate to add, or if {@code equals}
     * should be repurposed to do this. I've opted to use {@code equalTo} because the {@link BigDecimal} class
     * does explicitly have a difference between {@link BigDecimal#compareTo} and {@link BigDecimal#equals}.
     * @param point the {@link Point} to compare
     * @return true if the range's values are the same
     */
    public boolean equalTo(Point point) {
        boolean minEquals = (x == null && point.x == null)
                || (x != null && point.x != null && x.compareTo(point.x) == 0);
        boolean maxEquals = (y == null && point.y == null)
                || (y != null && point.y != null && y.compareTo(point.y) == 0);
        return minEquals && maxEquals;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(x, point.x) && Objects.equals(y, point.y);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Generated
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
