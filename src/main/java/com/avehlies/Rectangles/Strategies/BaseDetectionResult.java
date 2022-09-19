package com.avehlies.Rectangles.Strategies;

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
