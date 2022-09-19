package com.avehlies.Rectangles.Strategies;

import com.avehlies.Rectangles.Entities.Point;
import com.avehlies.Rectangles.Entities.Range;
import com.avehlies.Rectangles.Entities.Rectangle;
import com.avehlies.Rectangles.Utilities.RangeUtility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Detects if two {@link Rectangle}s intersect with each other
 */
public class IntersectionDetectionStrategy implements CollisionDetectionStrategy {

    @Override
    public CollisionType getType() {
        return CollisionType.INTERSECTION;
    }

    @Override
    public IntersectionDetectionResult detect(Rectangle a, Rectangle b) {
        if (a.equalTo(b)) {
            return new IntersectionDetectionResult(
                    false,
                    "The rectangles are in the same position.",
                    Collections.emptyList()
            );
        }

        // treat this as 2 1-d problems
        Range xIntersectionRange = RangeUtility.getIntersectedRange(a.getXRange(), b.getXRange());
        Range yIntersectionRange = RangeUtility.getIntersectedRange(a.getYRange(), b.getYRange());

        // if either range is "invalid" that means that there's no intersection between x and y coordinates
        // so there is no intersection at all
        if (xIntersectionRange.isInvalid() || yIntersectionRange.isInvalid()) {
            return new IntersectionDetectionResult(
                    false,
                    "There is no intersection between the two rectangles.",
                    Collections.emptyList()
            );
        }

        // Each range being "zero length" means that the rectangles are touching on a corner
        // I'm assuming here that this isn't considered an "intersection"
        if (xIntersectionRange.isZeroLength() && yIntersectionRange.isZeroLength()) {
            return new IntersectionDetectionResult(
                    false,
                    "The rectangles are touching on a corner.",
                    Collections.emptyList()
            );
        }

        // Now we have the intersected area, so get the four corners of the intersected area
        List<Point> intersectedAreaCoords = new ArrayList<>();
        intersectedAreaCoords.add(new Point(xIntersectionRange.getMin(), yIntersectionRange.getMin()));
        intersectedAreaCoords.add(new Point(xIntersectionRange.getMin(), yIntersectionRange.getMax()));
        intersectedAreaCoords.add(new Point(xIntersectionRange.getMax(), yIntersectionRange.getMin()));
        intersectedAreaCoords.add(new Point(xIntersectionRange.getMax(), yIntersectionRange.getMax()));

        // *ASSUMING* that we only want "Points" that are standalone and not full-line intersections
        // we should just need to get the boundingBoxCoords that AREN'T corners of the original rectangles
        List<Point> intersections = intersectedAreaCoords.stream()
                .filter(coord -> notRectangleCoordinate(coord, a))
                .filter(coord -> notRectangleCoordinate(coord, b))
                .collect(Collectors.toList());

        // If we have no points on the intersected area that aren't existing corners of our Rectangles,
        // then we have a situation where there is at least one edge shared between the two.
        if (intersections.isEmpty()) {
            return new IntersectionDetectionResult(
                    false,
                    "There are no intersections between the rectangles, but they likely share an edge.",
                    Collections.emptyList()
            );
        }

        int intersectionCount = intersections.size();
        String message = "";
        if (intersectionCount == 1) {
            message = "There is 1 intersection.";
        } else {
            message = "There are " + intersectionCount + " intersections.";
        }
        return new IntersectionDetectionResult(
                true,
                message,
                intersections
        );
    }

    /**
     * A {@link java.util.function.Predicate} to detect if a {@link Point} is one of the four coordinates
     * of a {@link Rectangle}
     *
     * @param point a {@code Point}
     * @param rectangle a {@link Rectangle} to check the point against
     * @return true if {@code point} is the same as one of the corners of {@code rectangle}
     */
    private boolean notRectangleCoordinate(Point point, Rectangle rectangle) {
        List<Point> rectanglePoints = new ArrayList<>();
        rectanglePoints.add(new Point(rectangle.getLeft(), rectangle.getTop()));
        rectanglePoints.add(new Point(rectangle.getLeft(), rectangle.getBottom()));
        rectanglePoints.add(new Point(rectangle.getRight(), rectangle.getTop()));
        rectanglePoints.add(new Point(rectangle.getRight(), rectangle.getBottom()));

        return rectanglePoints.stream()
                .noneMatch(point::equalTo);
    }
}
