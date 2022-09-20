package com.avehlies.Rectangles.Service;

import com.avehlies.Rectangles.Entities.Point;
import com.avehlies.Rectangles.Entities.Rectangle;
import com.avehlies.Rectangles.Strategies.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.avehlies.Rectangles.TestUtilities.TestUtilities.rect;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CollisionDetectionServiceTest {
    private final SamePositionDetectionStrategy samePositionStrategy = mock(SamePositionDetectionStrategy.class);
    private final ContainmentDetectionStrategy containmentStrategy = mock(ContainmentDetectionStrategy.class);
    private final AdjacentDetectionStrategy adjacentStrategy = mock(AdjacentDetectionStrategy.class);
    private final IntersectionDetectionStrategy intersectionStrategy = mock(IntersectionDetectionStrategy.class);

    private CollisionDetectionService service;

    @BeforeEach
    public void beforeEach() {
        when(samePositionStrategy.getType()).thenReturn(CollisionType.SAME_POSITION);
        when(containmentStrategy.getType()).thenReturn(CollisionType.CONTAINMENT);
        when(adjacentStrategy.getType()).thenReturn(CollisionType.ADJACENT);
        when(intersectionStrategy.getType()).thenReturn(CollisionType.INTERSECTION);

        service = new CollisionDetectionService(Arrays.asList(
                samePositionStrategy,
                containmentStrategy,
                adjacentStrategy,
                intersectionStrategy
        ));
    }

    @Test
    public void testDetectSingleTrueResult() {
        Rectangle a = rect(0, 2, 2, 0);
        Rectangle b = rect(1, 3, 3, 3);
        List<Point> intersections = Arrays.asList(
                new Point(new BigDecimal(1), new BigDecimal(2)),
                new Point(new BigDecimal(2), new BigDecimal(1))
        );
        when(intersectionStrategy.detect(a, b)).thenReturn(new IntersectionDetectionResult(true, "intersection.", intersections));
        when(samePositionStrategy.detect(a, b)).thenReturn(new BaseDetectionResult(false, "same position."));
        when(containmentStrategy.detect(a, b)).thenReturn(new BaseDetectionResult(false, "containment."));
        when(adjacentStrategy.detect(a, b)).thenReturn(new BaseDetectionResult(false, "adjacent."));

        BaseDetectionResult result = service.detect(a, b);
        assertTrue(result.isDetected());
        assertEquals("intersection. Intersections: [Point{x=1, y=2}, Point{x=2, y=1}]", result.getMessage());
    }

    @Test
    public void testDetectNoTrueResult() {
        Rectangle a = rect(0, 2, 2, 0);
        Rectangle b = rect(1, 3, 3, 3);
        when(intersectionStrategy.detect(a, b)).thenReturn(new IntersectionDetectionResult(false, "intersection.", Collections.emptyList()));
        when(samePositionStrategy.detect(a, b)).thenReturn(new BaseDetectionResult(false, "same position."));
        when(containmentStrategy.detect(a, b)).thenReturn(new BaseDetectionResult(false, "containment."));
        when(adjacentStrategy.detect(a, b)).thenReturn(new BaseDetectionResult(false, "adjacent."));

        BaseDetectionResult result = service.detect(a, b);
        assertFalse(result.isDetected());
        assertEquals("There were no collisions detected between the rectangles.", result.getMessage());
    }

    @Test
    public void testDetectMultipleTrueResults() {
        Rectangle a = rect(0, 2, 2, 0);
        Rectangle b = rect(1, 3, 3, 3);
        when(intersectionStrategy.detect(a, b)).thenReturn(new IntersectionDetectionResult(false, "intersection.", Collections.emptyList()));
        when(samePositionStrategy.detect(a, b)).thenReturn(new BaseDetectionResult(true, "same position."));
        when(containmentStrategy.detect(a, b)).thenReturn(new BaseDetectionResult(false, "containment."));
        when(adjacentStrategy.detect(a, b)).thenReturn(new BaseDetectionResult(true, "adjacent."));

        BaseDetectionResult result = service.detect(a, b);
        assertTrue(result.isDetected());
        assertTrue(result.getMessage().contains("adjacent."));
        assertTrue(result.getMessage().contains("same position."));
    }
}
