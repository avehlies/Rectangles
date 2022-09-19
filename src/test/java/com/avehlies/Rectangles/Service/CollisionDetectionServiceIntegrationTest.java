package com.avehlies.Rectangles.Service;

import com.avehlies.Rectangles.Entities.Rectangle;
import com.avehlies.Rectangles.Strategies.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.avehlies.Rectangles.TestUtilities.TestUtilities.rect;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This isn't a true integration test, but I would like to test
 * the CollisionDetectionService using the actual strategies
 * and not solely rely on the mocking.
 */
public class CollisionDetectionServiceIntegrationTest {

    private final CollisionDetectionService service = new CollisionDetectionService(Arrays.asList(
            new SamePositionDetectionStrategy(),
            new ContainmentDetectionStrategy(),
            new AdjacentDetectionStrategy(),
            new IntersectionDetectionStrategy()
    ));

    // we'll test against this rectangle
    private static final Rectangle BASE_RECT = rect(0, 5, 5, 0);

    public static Stream<Arguments> detectProvider() {
        return Stream.of(
                // same, no collisions, touching corners
                Arguments.of(rect(0, 5, 5, 0), true, "The rectangles are in the same position."),
                Arguments.of(rect(6, 10, 6, 10), false, "There were no collisions detected between the rectangles."),
                Arguments.of(rect(5, 10, 10, 5), false, "There were no collisions detected between the rectangles."),
                // adjacent
                Arguments.of(rect(0, 10, 5, 5), true, "The rectangles are adjacent with a type of Proper."),
                Arguments.of(rect(5, 10, 10, 0), true, "The rectangles are adjacent with a type of Sub-line."),
                Arguments.of(rect(5, 5, 10, 0), true, "The rectangles are adjacent with a type of Proper."),
                Arguments.of(rect(2, 10, 7, 5), true, "The rectangles are adjacent with a type of Partial."),
                // containment
                Arguments.of(rect(-5, 10, 10, -5), true, "The second rectangle contains the first rectangle."),
                Arguments.of(rect(1, 4, 4, 1), true, "The first rectangle contains the second rectangle."),
                // intersection
                Arguments.of(rect(0, 3, 10, 0), true, "There is 1 intersection. Intersections: [Point{x=5, y=3}]"),
                Arguments.of(rect(1, 6, 6, -1), true, "There are 2 intersections. Intersections: [Point{x=1, y=0}, Point{x=1, y=5}]"),
                Arguments.of(rect(2, 7, 4, -2), true, "There are 4 intersections. Intersections: [Point{x=2, y=0}, Point{x=2, y=5}, Point{x=4, y=0}, Point{x=4, y=5}]")
        );
    }

    @ParameterizedTest
    @MethodSource("detectProvider")
    public void testDetect(
            Rectangle testRectangle,
            boolean expectedDetected, String expectedMessage
    ) {
        BaseDetectionResult result = service.detect(BASE_RECT, testRectangle);
        assertEquals(expectedDetected, result.isDetected());
        assertEquals(expectedMessage, result.getMessage());
    }
}
