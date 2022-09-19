package com.avehlies.Rectangles.Entities;


import java.math.BigDecimal;
import java.util.Objects;

/**
 * A class to represent a Rectangle in 2d-space
 */
public class Rectangle {
    private final BigDecimal left;
    private final BigDecimal top;
    private final BigDecimal right;
    private final BigDecimal bottom;

    public Rectangle(BigDecimal left, BigDecimal top, BigDecimal right, BigDecimal bottom) {
        // Someone could give a left > right value, or bottom > top, so we could take that into consideration
        // and correct the values on input
        // or we could throw a RuntimeException, but we'll be nice here
        this.left = (left.compareTo(right) <= 0) ? left : right;
        this.top = (bottom.compareTo(top) <= 0) ? top : bottom;
        this.right = (left.compareTo(right) <= 0) ? right : left;
        this.bottom = (bottom.compareTo(top) <= 0) ? bottom : top;
    }

    public BigDecimal getLeft() {
        return left;
    }

    public BigDecimal getTop() {
        return top;
    }

    public BigDecimal getRight() {
        return right;
    }

    public BigDecimal getBottom() {
        return bottom;
    }

    public BigDecimal getHeight() {
        return top.subtract(bottom);
    }

    public BigDecimal getWidth() {
        return right.subtract(left);
    }

    public Range getXRange() {
        return new Range(left, right);
    }

    public Range getYRange() {
        return new Range(bottom, top);
    }

    /**
     * It's debatable whether a new {@code equalTo} is appropriate to add, or if {@code equals}
     * should be repurposed to do this. I've opted to use {@code equalTo} because the {@link BigDecimal} class
     * does explicitly have a difference between {@link BigDecimal#compareTo} and {@link BigDecimal#equals}.
     * @param rect the {@link Rectangle} to compare
     * @return true if the range's values are the same
     */
    public boolean equalTo(Rectangle rect) {
        return
                left.compareTo(rect.left) == 0
                && top.compareTo(rect.top) == 0
                && right.compareTo(rect.right) == 0
                && bottom.compareTo(rect.bottom) == 0;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(left, rectangle.left)
                && Objects.equals(top, rectangle.top)
                && Objects.equals(right, rectangle.right)
                && Objects.equals(bottom, rectangle.bottom);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(left, top, right, bottom);
    }

    @Generated
    @Override
    public String toString() {
        return "Rectangle{" +
                "left=" + left +
                ", top=" + top +
                ", right=" + right +
                ", bottom=" + bottom +
                '}';
    }
}
