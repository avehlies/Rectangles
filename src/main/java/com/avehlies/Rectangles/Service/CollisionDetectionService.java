package com.avehlies.Rectangles.Service;

import com.avehlies.Rectangles.Strategies.BaseDetectionResult;
import com.avehlies.Rectangles.Strategies.CollisionType;
import com.avehlies.Rectangles.Strategies.CollisionDetectionStrategy;
import com.avehlies.Rectangles.Entities.Rectangle;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service for handling Rectangle collision detections.
 */
public class CollisionDetectionService {

    private final Map<CollisionType, CollisionDetectionStrategy> detectionStrategies;

    public CollisionDetectionService(
        List<CollisionDetectionStrategy> collisionDetectionStrategyList
    ) {
        // In a full-fledged Spring application, this is likely
        // how we would want to inject our strategies so as we add more,
        // they're autowired without any more intervention
        detectionStrategies = collisionDetectionStrategyList.stream()
                .collect(Collectors.toMap(CollisionDetectionStrategy::getType, Function.identity()));
    }

    /**
     * Iterates over all strategies and finds which collision type the two {@link Rectangle}s meet
     * @param a the first {@code Rectangle}
     * @param b the second {@code Rectangle}
     * @return a {@link BaseDetectionResult}
     */
    public BaseDetectionResult detect(Rectangle a, Rectangle b) {
        List<BaseDetectionResult> result = detectionStrategies.keySet().stream()
                .map(type -> detect(type, a, b))
                .filter(BaseDetectionResult::isDetected)
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            return new BaseDetectionResult(
                    false,
                    "There were no collisions detected between the rectangles."
            );
        }

        // We should expect only 1 result to be detected, but we'll just make sure we can handle a situation where
        // there were more than 1. Perhaps an error in our code elsewhere could cause this?
        if (result.size() > 1) {
            return new BaseDetectionResult(
                    true,
                    result.stream()
                            .map(BaseDetectionResult::getMessage)
                            .collect(Collectors.joining(" "))
            );
        }

        return result.get(0);
    }

    /**
     * Detects if two {@link Rectangle}s collide based on a specific {@link CollisionType}. Maybe we're
     * only interested if it's one situation instead of just "Give me any collision that there is"
     *
     * @param type the {@code CollisionType} to check
     * @param a the first {@code Rectangle}
     * @param b the second {@code Rectangle}
     * @return a {@link BaseDetectionResult}
     */
    public BaseDetectionResult detect(CollisionType type, Rectangle a, Rectangle b) {
        return detectionStrategies.get(type).detect(a, b);
    }
}
