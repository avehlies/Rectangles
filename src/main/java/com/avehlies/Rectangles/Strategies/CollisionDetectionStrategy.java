package com.avehlies.Rectangles.Strategies;

import com.avehlies.Rectangles.Entities.Rectangle;

/**
 * Interface for strategies for detection collisions of {@link Rectangle} objects
 */
public interface CollisionDetectionStrategy {

    /**
     * Returns the {@link CollisionType} handled by this strategy
     * @return a {@code RectangleCollisionType}
     */
    CollisionType getType();

    /**
     * Detects whether the {@link Rectangle} objects collide for the {@code RectangleCollisionType}
     * @param a the first {@code Rectangle}
     * @param b the second {@code Rectangle}
     * @return a {@link BaseDetectionResult} or derivative class
     */
    BaseDetectionResult detect(Rectangle a, Rectangle b);
}
