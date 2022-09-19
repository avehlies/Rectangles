package com.avehlies.Rectangles.Strategies;

import com.avehlies.Rectangles.Entities.Rectangle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.avehlies.Rectangles.TestUtilities.TestUtilities.rect;
import static org.junit.jupiter.api.Assertions.*;

public class ContainmentDetectionStrategyTest {

    private static final String MSG_SAME_RECTANGLES =
            "The rectangles are the same, which for this example is 'not wholly contained'.";
    private static final String MSG_FIRST_CONTAINS_SECOND = "The first rectangle contains the second rectangle.";
    private static final String MSG_SECOND_CONTAINS_FIRST = "The second rectangle contains the first rectangle.";
    private static final String MSG_NEITHER_CONTAINS = "Neither rectangle fully contains the other rectangle.";

    private final ContainmentDetectionStrategy strategy = new ContainmentDetectionStrategy();

    @Test
    public void testGetType() {
        assertEquals(CollisionType.CONTAINMENT, strategy.getType());
    }

    public static Stream<Arguments> detectProvider() {
        return Stream.of(
                Arguments.of(rect(0, 10, 20, 0), rect(0, 10, 20, 0), false, MSG_SAME_RECTANGLES),
                Arguments.of(rect(-1, 11, 21, -1), rect(0, 10, 20, 0), true, MSG_FIRST_CONTAINS_SECOND),
                Arguments.of(rect(0, 10, 20, -1), rect(0, 10, 20, 0), true, MSG_FIRST_CONTAINS_SECOND),
                Arguments.of(rect(0, 10, 20, 0), rect(-1, 10, 20, 0), true, MSG_SECOND_CONTAINS_FIRST),
                Arguments.of(rect(0, 10, 20, 0), rect(0, 11, 20, 0), true, MSG_SECOND_CONTAINS_FIRST),
                Arguments.of(rect(0, 10, 20, 0), rect(0, 10, 21, 0), true, MSG_SECOND_CONTAINS_FIRST),
                Arguments.of(rect(0, 10, 20, 0), rect(0, 10, 20, -1), true, MSG_SECOND_CONTAINS_FIRST),

                Arguments.of(rect(0, 2, 2, 0), rect(2, 4, 4, 2), false, MSG_NEITHER_CONTAINS),
                Arguments.of(rect(0, 4, 4, 0), rect(4, 3, 6, 2), false, MSG_NEITHER_CONTAINS),
                Arguments.of(rect(0, 4, 4, 0), rect(4, 3, 6, 0), false, MSG_NEITHER_CONTAINS),
                Arguments.of(rect(0, 2, 2, 0), rect(2, 3, 4, 1), false, MSG_NEITHER_CONTAINS),
                Arguments.of(rect(0, 2, 2, 0), rect(2, 2, 4, 0), false, MSG_NEITHER_CONTAINS)
        );
    }

    @ParameterizedTest
    @MethodSource("detectProvider")
    public void testDetect(
            Rectangle a, Rectangle b,
            boolean expectedDetected, String expectedMessage
    ) {
        BaseDetectionResult result = strategy.detect(a, b);
        assertEquals(expectedDetected, result.isDetected());
        assertEquals(expectedMessage, result.getMessage());
    }
}
