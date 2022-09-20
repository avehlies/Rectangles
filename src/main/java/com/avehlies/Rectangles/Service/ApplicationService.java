package com.avehlies.Rectangles.Service;

import com.avehlies.Rectangles.Entities.Pair;
import com.avehlies.Rectangles.Entities.Rectangle;
import com.avehlies.Rectangles.Strategies.BaseDetectionResult;
import com.avehlies.Rectangles.Strategies.CollisionType;

import java.util.ArrayList;
import java.util.List;

/**
 * Do all the actual logic for the application here so it is testable, instead of in the Main class
 * which isn't testable (by unit tests).
 */
public class ApplicationService {

    private final CollisionDetectionService collisionDetectionService;
    private final DataLoaderService dataLoaderService;

    public ApplicationService(
            final CollisionDetectionService collisionDetectionService,
            final DataLoaderService dataLoaderService
    ) {
        this.collisionDetectionService = collisionDetectionService;
        this.dataLoaderService = dataLoaderService;
    }

    /**
     * The main application method. This is what SpringApplication.run() would call
     * @param args the application arguments
     */
    public void run(String[] args) {
        List<Pair<Rectangle>> rectangles = new ArrayList<>();
        // do we have a filename? if so, read the CSV line by line and generate pairs of rectangles
        if (args.length == 1) {
            rectangles = dataLoaderService.createRectanglesFromFile(args[0]);
        } else {
            // we don't have a filename, just get a sample set of rectangles, so we have some output:
            rectangles = dataLoaderService.createSampleRectangles();
        }

        detectCollisions(rectangles);
    }

    /**
     * Detect collisions for {@link List}s of {@link Pair}s of {@link Rectangle}s
     * and display the output
     * @param rectangleList {@code List} of {@code Pair}s of {@code Rectangle}s
     */
    public void detectCollisions(List<Pair<Rectangle>> rectangleList) {
        for (Pair<Rectangle> rPair : rectangleList) {
            detectCollisions(rPair.getLeft(), rPair.getRight());
        }
    }

    /**
     * Detect collisions between two {@link Rectangle}s and display the output
     * @param a the first {@code Rectangle}
     * @param b the second {@code Rectangle}
     */
    public void detectCollisions(Rectangle a, Rectangle b) {
        System.out.println("Comparing: " + a + " and " + b);

        // Display the calculated collision
        BaseDetectionResult result = collisionDetectionService.detect(a, b);
        System.out.println(result.getMessage());

        // Display each CollisionType's result
        for (CollisionType type : CollisionType.values()) {
            System.out.println(type.name() + ": " + collisionDetectionService.detect(type, a, b).getMessage());
        }
        System.out.println("\n");
    }

}
