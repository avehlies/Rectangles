package com.avehlies.Rectangles.Strategies;

import com.avehlies.Rectangles.Entities.Point;
import com.avehlies.Rectangles.Entities.Rectangle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.avehlies.Rectangles.TestUtilities.TestUtilities.point;
import static com.avehlies.Rectangles.TestUtilities.TestUtilities.pointList;
import static com.avehlies.Rectangles.TestUtilities.TestUtilities.rect;
import static org.junit.jupiter.api.Assertions.*;

public class IntersectionDetectionStrategyTest {

    public static final String MSG_NO_INTERSECTION = "There is no intersection between the two rectangles.";
    public static final String MSG_NO_INTERSECTION_SHARE_EDGE = "There are no intersections between the rectangles, but they likely share an edge.";
    private static final String MSG_SAME_POSITION = "The rectangles are in the same position.";
    private static final String MSG_CORNER_TOUCHING = "The rectangles are touching on a corner.";

    private final IntersectionDetectionStrategy strategy = new IntersectionDetectionStrategy();

    @Test
    public void testGetType() {
        assertEquals(CollisionType.INTERSECTION, strategy.getType());
    }

    public static Stream<Arguments> detectProvider() {
        return Stream.of(
                // containments and no intersections at all
                Arguments.of(rect(0, 10, 20, 0), rect(0, 10, 20, 0), false, MSG_SAME_POSITION, Collections.emptyList()),
                Arguments.of(rect(0, 2, 2, 0), rect(3, 4, 4, 3), false, MSG_NO_INTERSECTION, Collections.emptyList()),
                Arguments.of(rect(0, 2, 2, 0), rect (0, 5, 2, 3), false, MSG_NO_INTERSECTION, Collections.emptyList()),
                Arguments.of(rect(0, 2, 2, 0), rect(3, 2, 5, 0), false, MSG_NO_INTERSECTION, Collections.emptyList()),
                // adjacencies
                Arguments.of(rect(0, 2, 2, 0), rect(2, 4, 4, 2), false, MSG_CORNER_TOUCHING, Collections.emptyList()),
                Arguments.of(rect(0, 4, 4, 0), rect(4, 3, 6, 2), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(0, 4, 4, 0), rect(4, 3, 6, 0), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(0, 2, 2, 0), rect(2, 3, 4, 1), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(0, 2, 2, 0), rect(2, 2, 4, 0), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                // adjacencies
                Arguments.of(rect(2, 4, 4, 2), rect(0, 2, 2, 0), false, MSG_CORNER_TOUCHING, Collections.emptyList()),
                Arguments.of(rect(4, 3, 6, 2), rect(0, 4, 4, 0), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(4, 3, 6, 0), rect(0, 4, 4, 0), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(2, 3, 4, 1), rect(0, 2, 2, 0), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(2, 2, 4, 0), rect(0, 2, 2, 0), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                // adjacencies
                Arguments.of(rect(0, 4, 4, 0), rect(1, 6, 3, 4), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(0, 4, 4, 0), rect(0, 6, 3, 4), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(0, 2, 2, 0), rect(1, 4, 3, 2), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(0, 2, 2, 0), rect(0, 4, 2, 2), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                // adjacencies
                Arguments.of(rect(1, 6, 3, 4), rect(0, 4, 4, 0),  false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(0, 6, 3, 4), rect(0, 4, 4, 0),  false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(1, 4, 3, 2), rect(0, 2, 2, 0),  false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(0, 4, 2, 2), rect(0, 2, 2, 0),  false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                // containment
                Arguments.of(rect(0, 10, 20, -1), rect(0, 10, 20, 0), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(0, 10, 20, 0), rect(-1, 10, 20, 0), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(0, 10, 20, 0), rect(0, 11, 20, 0), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(0, 10, 20, 0), rect(0, 10, 21, 0), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(0, 10, 20, 0), rect(0, 10, 20, -1), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                // partial situation
                Arguments.of(rect(0, 4, 4, 0), rect(0, 4, 2, 0), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                Arguments.of(rect(0, 4, 4, 0), rect(0, 4, 2, 0), false, MSG_NO_INTERSECTION_SHARE_EDGE, Collections.emptyList()),
                // intersections
                Arguments.of(rect(0, 4, 4, 0), rect(0, 6, 2, 2), true, "There is 1 intersection. Intersections: [Point{x=2, y=4}]", Collections.singletonList(point(2, 4))),
                Arguments.of(rect(0, 4, 4, 0), rect(0, 6, 2, -2), true, "There are 2 intersections. Intersections: [Point{x=2, y=0}, Point{x=2, y=4}]", pointList(point(2, 0), point(2, 4))),
                Arguments.of(rect(0, 4, 4, 0), rect(-2, 6, 2, -2), true, "There are 2 intersections. Intersections: [Point{x=2, y=0}, Point{x=2, y=4}]", pointList(point(2, 0), point(2, 4))),
                Arguments.of(rect(0, 4, 4, 0), rect(1, 6, 3, -2), true, "There are 4 intersections. Intersections: [Point{x=1, y=0}, Point{x=1, y=4}, Point{x=3, y=0}, Point{x=3, y=4}]", pointList(point(1, 0), point(1, 4), point(3, 0), point(3, 4)))
        );
    }

    @ParameterizedTest
    @MethodSource("detectProvider")
    public void testDetect(
            Rectangle a, Rectangle b,
            boolean expectedDetected, String expectedMessage,
            List<Point> expectedIntersections
    ) {
        IntersectionDetectionResult result = strategy.detect(a, b);
        assertEquals(expectedDetected, result.isDetected());
        assertEquals(expectedMessage, result.getMessage());
        assertEquals(expectedIntersections, result.getIntersections());
    }

}
