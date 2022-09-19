package com.avehlies.Rectangles.Strategies;

import com.avehlies.Rectangles.Entities.Range;
import com.avehlies.Rectangles.Entities.Rectangle;
import com.avehlies.Rectangles.Utilities.RangeUtility;


/**
 * {@link CollisionDetectionStrategy} for Adjacent rectangles
 */
public class AdjacentDetectionStrategy implements CollisionDetectionStrategy {

    @Override
    public CollisionType getType() {
        return CollisionType.ADJACENT;
    }

    @Override
    public BaseDetectionResult detect(Rectangle a, Rectangle b) {
        if (a.equalTo(b)) {
            return new BaseDetectionResult(
                    false,
                    "The rectangles are in the same position."
            );
        }

        Range xIntersectionRange = RangeUtility.getIntersectedRange(a.getXRange(), b.getXRange());
        Range yIntersectionRange = RangeUtility.getIntersectedRange(a.getYRange(), b.getYRange());

        if (
                (xIntersectionRange.isInvalid() && yIntersectionRange.isInvalid())
                || (!xIntersectionRange.isZeroLength() && !yIntersectionRange.isZeroLength())
        ) {
            return new BaseDetectionResult(
                    false,
                    "There is no adjacency between the rectangles."
            );
        }

        if (xIntersectionRange.isZeroLength() && yIntersectionRange.isZeroLength()) {
            return new BaseDetectionResult(
                    false,
                    "The rectangles are touching on a corner."
            );
        }

        String adjacencyType = null;
        // it's a vertical line that's adjacent
        if (xIntersectionRange.isZeroLength()) {
            adjacencyType = calculateAdjacencyType(a.getYRange(), b.getYRange());
        } else if (yIntersectionRange.isZeroLength()) {
            adjacencyType = calculateAdjacencyType(a.getXRange(), b.getXRange());
        }

        return new BaseDetectionResult(
                true,
                "The rectangles are adjacent with a type of " + adjacencyType + "."
        );
    }

    /**
     * In order to calculate the type of adjacency, we need to know both {@link Range}s,
     * so we can't just pass yIntersectionRange even though we already calculated and will calculate it again
     * @param a the first {@code Range}
     * @param b the second {@code Range}
     * @return the type of adjacency
     */
    private String calculateAdjacencyType(Range a, Range b) {
        if (a.equalTo(b)) {
            return "Proper";
        }

        Range intersection = RangeUtility.getIntersectedRange(a, b);

        if (intersection.equalTo(a) || intersection.equalTo(b)) {
            return "Sub-line";
        }

        // For completeness, we should assume someone may accidentally call this even when there is no valid
        // adjacency, so give an out if it's invalid.
        if (intersection.isInvalid()) {
            return "No Adjacency";
        }

        // if we're not Proper, Sub-line, or No Adjacency, then we're Partial
        return "Partial";
    }
}
