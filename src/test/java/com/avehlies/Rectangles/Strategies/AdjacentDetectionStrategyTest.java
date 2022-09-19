package com.avehlies.Rectangles.Strategies;

import com.avehlies.Rectangles.Entities.Rectangle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.avehlies.Rectangles.TestUtilities.TestUtilities.rect;
import static org.junit.jupiter.api.Assertions.*;

public class AdjacentDetectionStrategyTest {

    private static final String MSG_ADJACENT_PARTIAL = "The rectangles are adjacent with a type of Partial.";
    private static final String MSG_ADJACENT_PROPER = "The rectangles are adjacent with a type of Proper.";
    private static final String MSG_ADJACENT_SUBLINE = "The rectangles are adjacent with a type of Sub-line.";
    private static final String MSG_CORNER_TOUCHING = "The rectangles are touching on a corner.";
    private static final String MSG_NOT_ADJACENT = "There is no adjacency between the rectangles.";
    private static final String MSG_SAME_POSITION = "The rectangles are in the same position.";

    private final AdjacentDetectionStrategy strategy = new AdjacentDetectionStrategy();

    @Test
    public void testGetType() {
        assertEquals(CollisionType.ADJACENT, strategy.getType());
    }

    public static Stream<Arguments> detectProvider() {
        return Stream.of(
                Arguments.of(rect(0, 10, 20, 0), rect(0, 10, 20, 0), false, MSG_SAME_POSITION),
                Arguments.of(rect(0, 2, 2, 0), rect(3, 4, 4, 3), false, MSG_NOT_ADJACENT),
                Arguments.of(rect(0, 2, 2, 0), rect (0, 5, 2, 3), false, MSG_NOT_ADJACENT),
                Arguments.of(rect(0, 2, 2, 0), rect(3, 2, 5, 0), false, MSG_NOT_ADJACENT),

                Arguments.of(rect(0, 2, 2, 0), rect(2, 4, 4, 2), false, MSG_CORNER_TOUCHING),
                Arguments.of(rect(0, 4, 4, 0), rect(4, 3, 6, 2), true, MSG_ADJACENT_SUBLINE),
                Arguments.of(rect(0, 4, 4, 0), rect(4, 3, 6, 0), true, MSG_ADJACENT_SUBLINE),
                Arguments.of(rect(0, 2, 2, 0), rect(2, 3, 4, 1), true, MSG_ADJACENT_PARTIAL),
                Arguments.of(rect(0, 2, 2, 0), rect(2, 2, 4, 0), true, MSG_ADJACENT_PROPER),

                Arguments.of(rect(2, 4, 4, 2), rect(0, 2, 2, 0), false, MSG_CORNER_TOUCHING),
                Arguments.of(rect(4, 3, 6, 2), rect(0, 4, 4, 0), true, MSG_ADJACENT_SUBLINE),
                Arguments.of(rect(4, 3, 6, 0), rect(0, 4, 4, 0), true, MSG_ADJACENT_SUBLINE),
                Arguments.of(rect(2, 3, 4, 1), rect(0, 2, 2, 0), true, MSG_ADJACENT_PARTIAL),
                Arguments.of(rect(2, 2, 4, 0), rect(0, 2, 2, 0), true, MSG_ADJACENT_PROPER),

                Arguments.of(rect(0, 4, 4, 0), rect(1, 6, 3, 4), true, MSG_ADJACENT_SUBLINE),
                Arguments.of(rect(0, 4, 4, 0), rect(0, 6, 3, 4), true, MSG_ADJACENT_SUBLINE),
                Arguments.of(rect(0, 2, 2, 0), rect(1, 4, 3, 2), true, MSG_ADJACENT_PARTIAL),
                Arguments.of(rect(0, 2, 2, 0), rect(0, 4, 2, 2), true, MSG_ADJACENT_PROPER),

                Arguments.of(rect(1, 6, 3, 4),rect(0, 4, 4, 0),  true, MSG_ADJACENT_SUBLINE),
                Arguments.of(rect(0, 6, 3, 4),rect(0, 4, 4, 0),  true, MSG_ADJACENT_SUBLINE),
                Arguments.of(rect(1, 4, 3, 2),rect(0, 2, 2, 0),  true, MSG_ADJACENT_PARTIAL),
                Arguments.of(rect(0, 4, 2, 2),rect(0, 2, 2, 0),  true, MSG_ADJACENT_PROPER),

                Arguments.of(rect(0, 10, 20, -1), rect(0, 10, 20, 0), false, MSG_NOT_ADJACENT),
                Arguments.of(rect(0, 10, 20, 0), rect(-1, 10, 20, 0), false, MSG_NOT_ADJACENT),
                Arguments.of(rect(0, 10, 20, 0), rect(0, 11, 20, 0), false, MSG_NOT_ADJACENT),
                Arguments.of(rect(0, 10, 20, 0), rect(0, 10, 21, 0), false, MSG_NOT_ADJACENT),
                Arguments.of(rect(0, 10, 20, 0), rect(0, 10, 20, -1), false, MSG_NOT_ADJACENT)
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
