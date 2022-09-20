package com.avehlies.Rectangles.Strategies;

/**
 * Result from a {@link CollisionDetectionStrategy}
 */
public class BaseDetectionResult {

    private final boolean detected;
    protected String message;

    public BaseDetectionResult(boolean detected, String message) {
        this.detected = detected;
        this.message = message;
    }

    public boolean isDetected() {
        return detected;
    }

    public String getMessage() {
        return message;
    }
}
