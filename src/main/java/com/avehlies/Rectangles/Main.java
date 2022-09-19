package com.avehlies.Rectangles;

import com.avehlies.Rectangles.Entities.Pair;
import com.avehlies.Rectangles.Entities.Rectangle;
import com.avehlies.Rectangles.Service.ApplicationService;
import com.avehlies.Rectangles.Service.CollisionDetectionService;
import com.avehlies.Rectangles.Service.CsvReader;
import com.avehlies.Rectangles.Service.DataLoaderService;
import com.avehlies.Rectangles.Strategies.*;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    /**
     * These would normally be auto-wired in a Spring application, but since I've elected to not use Spring,
     * we'll create them as static instances and initialize them manually by creating a list of Strategies to pass
     * into the CollisionDetectionService constructor, and creating a CsvReader to pass into
     * DataLoaderService's constructor.
     */
    private static ApplicationService applicationService;

    public static void main(String[] args) {
        // Initializing our strategies and services here is mimicking Spring's autowiring
        // I've elected nto to use Spring since it's such a big dependency, but this will at least
        // allow us to mock all the services in tests.
        List<CollisionDetectionStrategy> collisionDetectionStrategies = Arrays.asList(
                new SamePositionDetectionStrategy(),
                new ContainmentDetectionStrategy(),
                new AdjacentDetectionStrategy(),
                new IntersectionDetectionStrategy()
        );

        CsvReader csvReader = new CsvReader();

        CollisionDetectionService collisionDetectionService =
                new CollisionDetectionService(collisionDetectionStrategies);
        DataLoaderService dataLoaderService = new DataLoaderService(csvReader);

        applicationService = new ApplicationService(
                collisionDetectionService,
                dataLoaderService
        );

        // our equivalent of SpringApplication.run(Application.class, args);
        applicationService.run(args);
    }
}