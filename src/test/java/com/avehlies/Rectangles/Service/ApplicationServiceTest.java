package com.avehlies.Rectangles.Service;

import com.avehlies.Rectangles.Entities.Pair;
import com.avehlies.Rectangles.Entities.Rectangle;
import com.avehlies.Rectangles.Strategies.BaseDetectionResult;
import com.avehlies.Rectangles.Strategies.CollisionType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.avehlies.Rectangles.TestUtilities.TestUtilities.rect;
import static org.mockito.Mockito.*;

public class ApplicationServiceTest {
    private CollisionDetectionService collisionDetectionServiceMock = mock(CollisionDetectionService.class);
    private DataLoaderService dataLoaderServiceMock = mock(DataLoaderService.class);

    private ApplicationService service = new ApplicationService(
            collisionDetectionServiceMock,
            dataLoaderServiceMock
    );

    @Test
    public void testRunWithArgs() {
        String filename = "file.csv";
        when(dataLoaderServiceMock.createRectanglesFromFile(filename))
                .thenReturn(Collections.emptyList());

        service.run(new String[]{filename});

        verify(dataLoaderServiceMock, times(1))
                .createRectanglesFromFile(filename);

        verifyNoMoreInteractions(dataLoaderServiceMock);
        verifyNoInteractions(collisionDetectionServiceMock);
    }

    @Test
    public void testRunWithoutArgs() {
        when(dataLoaderServiceMock.createSampleRectangles())
                .thenReturn(Collections.emptyList());

        service.run(new String[]{});

        verify(dataLoaderServiceMock, times(1))
                .createSampleRectangles();
        verifyNoMoreInteractions(dataLoaderServiceMock);
        verifyNoInteractions(collisionDetectionServiceMock);
    }

    @Test
    public void testDetectCollisionsList() {
        Rectangle a = rect(0, 2, 2, 0);
        Rectangle b = rect(0, 1, 1, 0);
        List<Pair<Rectangle>> list = new ArrayList<>();
        list.add(new Pair<>(a, b));
        list.add(new Pair<>(a, b));

        BaseDetectionResult result1 = new BaseDetectionResult(true, "test1");
        BaseDetectionResult adjacentResult = new BaseDetectionResult(false, "adjacent");
        BaseDetectionResult samePositionResult = new BaseDetectionResult(false, "same");
        BaseDetectionResult containmentResult = new BaseDetectionResult(false, "containment");
        BaseDetectionResult intersectionResult = new BaseDetectionResult(true, "intersection");

        when(collisionDetectionServiceMock.detect(a, b)).thenReturn(result1);
        when(collisionDetectionServiceMock.detect(CollisionType.ADJACENT, a, b)).thenReturn(adjacentResult);
        when(collisionDetectionServiceMock.detect(CollisionType.CONTAINMENT, a, b)).thenReturn(containmentResult);
        when(collisionDetectionServiceMock.detect(CollisionType.INTERSECTION, a, b)).thenReturn(intersectionResult);
        when(collisionDetectionServiceMock.detect(CollisionType.SAME_POSITION, a, b)).thenReturn(samePositionResult);

        service.detectCollisions(list);

        verify(collisionDetectionServiceMock, times(2)).detect(a, b);
        verify(collisionDetectionServiceMock, times(2)).detect(CollisionType.ADJACENT, a, b);
        verify(collisionDetectionServiceMock, times(2)).detect(CollisionType.CONTAINMENT, a, b);
        verify(collisionDetectionServiceMock, times(2)).detect(CollisionType.INTERSECTION, a, b);
        verify(collisionDetectionServiceMock, times(2)).detect(CollisionType.SAME_POSITION, a, b);

        verifyNoMoreInteractions(collisionDetectionServiceMock);
    }

    @Test
    public void testDetectCollisionsSingleItem() {
        Rectangle a = rect(0, 2, 2, 0);
        Rectangle b = rect(0, 1, 1, 0);

        BaseDetectionResult result1 = new BaseDetectionResult(true, "test1");
        BaseDetectionResult adjacentResult = new BaseDetectionResult(false, "adjacent");
        BaseDetectionResult samePositionResult = new BaseDetectionResult(false, "same");
        BaseDetectionResult containmentResult = new BaseDetectionResult(false, "containment");
        BaseDetectionResult intersectionResult = new BaseDetectionResult(true, "intersection");

        when(collisionDetectionServiceMock.detect(a, b)).thenReturn(result1);
        when(collisionDetectionServiceMock.detect(CollisionType.ADJACENT, a, b)).thenReturn(adjacentResult);
        when(collisionDetectionServiceMock.detect(CollisionType.CONTAINMENT, a, b)).thenReturn(containmentResult);
        when(collisionDetectionServiceMock.detect(CollisionType.INTERSECTION, a, b)).thenReturn(intersectionResult);
        when(collisionDetectionServiceMock.detect(CollisionType.SAME_POSITION, a, b)).thenReturn(samePositionResult);

        service.detectCollisions(a, b);

        verify(collisionDetectionServiceMock, times(1)).detect(a, b);
        verify(collisionDetectionServiceMock, times(1)).detect(CollisionType.ADJACENT, a, b);
        verify(collisionDetectionServiceMock, times(1)).detect(CollisionType.CONTAINMENT, a, b);
        verify(collisionDetectionServiceMock, times(1)).detect(CollisionType.INTERSECTION, a, b);
        verify(collisionDetectionServiceMock, times(1)).detect(CollisionType.SAME_POSITION, a, b);

        verifyNoMoreInteractions(collisionDetectionServiceMock);
    }
}
