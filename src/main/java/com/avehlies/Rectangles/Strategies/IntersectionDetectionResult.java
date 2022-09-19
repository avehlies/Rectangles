package com.avehlies.Rectangles.Strategies;

import com.avehlies.Rectangles.Entities.Generated;
import com.avehlies.Rectangles.Entities.Point;

import java.util.List;
import java.util.Objects;

public class IntersectionDetectionResult extends BaseDetectionResult {
    private final List<Point> intersections;

    public IntersectionDetectionResult(boolean detected, String message, List<Point> intersections) {
        super(detected, message);
        this.intersections = intersections;
    }

    @Override
    public String getMessage() {
        return this.message +
                ((getIntersections().isEmpty()) ? "" : " Intersections: " + getIntersections().toString());
    }

    public List<Point> getIntersections() {
        return intersections;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntersectionDetectionResult that = (IntersectionDetectionResult) o;
        return intersections.equals(that.intersections);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(intersections);
    }

    @Generated
    @Override
    public String toString() {
        return "IntersectionDetectionResult{" +
                "intersections=" + intersections +
                '}';
    }
}
