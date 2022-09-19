package com.avehlies.Rectangles.Strategies;

import com.avehlies.Rectangles.Entities.Rectangle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static com.avehlies.Rectangles.TestUtilities.TestUtilities.rect;

public class SamePositionDetectionStrategyTest {

    private static final String MSG_NOT_SAME_POSITION = "The rectangles are not in the same position.";
    private static final String MSG_SAME_POSITION = "The rectangles are in the same position.";

    private final SamePositionDetectionStrategy strategy = new SamePositionDetectionStrategy();

    @Test
    public void testGetType() {
        assertEquals(CollisionType.SAME_POSITION, strategy.getType());
    }

    public static Stream<Arguments> detectProvider() {
        return Stream.of(
                Arguments.of(rect(0, 10, 20, 0), rect(0, 10, 20, 0), true, MSG_SAME_POSITION),
                Arguments.of(rect(0, 10, 20, 0), rect(-1, 10, 20, 0), false, MSG_NOT_SAME_POSITION),
                Arguments.of(rect(0, 10, 20, 0), rect(0, 11, 20, 0), false, MSG_NOT_SAME_POSITION),
                Arguments.of(rect(0, 10, 20, 0), rect(0, 10, 21, 0), false, MSG_NOT_SAME_POSITION),
                Arguments.of(rect(0, 10, 20, 0), rect(0, 10, 20, 1), false, MSG_NOT_SAME_POSITION)
        );
    }

    @ParameterizedTest
    @MethodSource("detectProvider")
    public void testDetect(
            Rectangle a,
            Rectangle b,
            boolean expectedDetected, String expectedMessage
    ) {
        BaseDetectionResult result = strategy.detect(a, b);
        assertEquals(expectedDetected, result.isDetected());
        assertEquals(expectedMessage, result.getMessage());
    }
}
