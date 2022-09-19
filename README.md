# Rectangles
Andrew Vehlies

## Overview
The application should accept a pair of rectangles (or list of pair of rectangles)
and describe the collisions between the two.

## Dependencies

### Runtime
+ Java 1.8

### Tests
+ JUnit 5
+ JUnit Parameterized Tests
+ Mockito

### Building

`./gradlew jar` to build the `Rectangles-1.0.jar` file into the `build/libs` directory

### Usage

`java -jar build/libs/Rectangles-1.0.jar` to run with two randomly generated rectangles in a 10x10 space

`java -jar build/libs/Rectangles-1.0.jar input.csv` to run with the provided input CSV file

### Tests

`./gradlew test` to run JUnit tests.

JUnit output will be in `build/reports/tests/test/index.html`

JaCoCo output will be in `build/reports/jacoco/test/html/index.html`

### Design Decisions

#### Spring
I decided not to include Spring as a dependency for the project. While it could be useful for
autowiring services, it's entirely overkill for a small command-line application. I believe
that my use of the `CollisionDetectionService` that gets initialized with a `List` accurately
mimics how a Spring service would get autowired.

#### Utility functions
`static` utility functions can often be frowned upon because they're not mockable. I believe the
`static` functions I used are brief, and are not things that should be mocked as they're simply
commonly used calculations not easily offered with `BigDecimal`.

### Assumptions
1. Two rectangles that have the same coordinates are not considered to wholly contain the other.
2. When determining intersection points, I am not considering "adjacent"/"overlapping" line segments as "points" (or
   a series of points).
3. We have no intention of dealing with rotation in rectangles. If we were to do that, I couldn't use
   left, top, bottom, and right as the description of the rectangle. If that was desired, rectangles would need to be
   described probably as a vector of distance from an origin and then a vector to the opposing corner.

## Possible expansions on the problem
1. A `DetectionStrategy` could be turned into a templated class that uses two `Shape`
   classes. We could then have `CollisionDetectionStrategy<Rectangle, Circle>` to detect
   collisions between a `Rectangle` and a (future) `Circle` class.
2. We could define a `Rectangle` one of (at least) four ways:
    + `left`, `top`, `right`, `bottom` as we did
    + `Point` for `upperLeft` and `Point` for `upperRight`
    + `Point` for distance from origin, and then a `Point` (really a vector) to describe the direction to the opposite
      corner. A rotatable rectangle would best be described this way.
    + A `Range` for x values and a `Range` for y values.