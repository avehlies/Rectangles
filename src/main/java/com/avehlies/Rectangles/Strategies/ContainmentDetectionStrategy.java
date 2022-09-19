package com.avehlies.Rectangles.Strategies;

import com.avehlies.Rectangles.Utilities.MathUtility;
import com.avehlies.Rectangles.Entities.Rectangle;

/**
 * Detects if either {@link Rectangle} wholly contains the other {@link Rectangle}
 */
public class ContainmentDetectionStrategy implements CollisionDetectionStrategy {

    @Override
    public CollisionType getType() {
        return CollisionType.CONTAINMENT;
    }

    @Override
    public BaseDetectionResult detect(Rectangle a, Rectangle b) {
        // Making a decision here that equal rectangles are not considered to be "wholly contained".
        if (a.equalTo(b)) {
            return new BaseDetectionResult(
                    false,
                    "The rectangles are the same, which for this example is 'not wholly contained'."
            );
        }

        // We might as well give a different message for each, so detect them separately
        boolean aContainsB = MathUtility.lte(a.getLeft(), b.getLeft())
                && MathUtility.gte(a.getTop(), b.getTop())
                && MathUtility.gte(a.getRight(), b.getRight())
                && MathUtility.lte(a.getBottom(), b.getBottom());
        boolean bContainsA = MathUtility.lte(b.getLeft(), a.getLeft())
                && MathUtility.gte(b.getTop(), a.getTop())
                && MathUtility.gte(b.getRight(), a.getRight())
                && MathUtility.lte(b.getBottom(), a.getBottom());

        if (aContainsB) {
            return new BaseDetectionResult(
                    true,
                    "The first rectangle contains the second rectangle."
            );
        }

        if (bContainsA) {
            return new BaseDetectionResult(
                    true,
                    "The second rectangle contains the first rectangle."
            );
        }

        return new BaseDetectionResult(
                false,
                "Neither rectangle fully contains the other rectangle."
        );
    }
}
