package com.avehlies.Rectangles.Strategies;

import com.avehlies.Rectangles.Entities.Rectangle;

/**
 * Detects whether two {@link Rectangle}s are in the same position
 */
public class SamePositionDetectionStrategy implements CollisionDetectionStrategy {

    @Override
    public CollisionType getType() {
        return CollisionType.SAME_POSITION;
    }

    @Override
    public BaseDetectionResult detect(Rectangle a, Rectangle b) {
        if (a.equalTo(b)) {
            return new BaseDetectionResult(
                    true,
                    "The rectangles are in the same position."
            );
        }

        return new BaseDetectionResult(
                false,
                "The rectangles are not in the same position."
        );
    }
}
