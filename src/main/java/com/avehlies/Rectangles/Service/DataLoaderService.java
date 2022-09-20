package com.avehlies.Rectangles.Service;

import com.avehlies.Rectangles.Entities.Pair;
import com.avehlies.Rectangles.Entities.Rectangle;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

/**
 * A helper service to load data into the application. Either to create data from a file,
 * or to generate sample rectangles (if there's no input).
 */
public class DataLoaderService {

    private final CsvReader reader;

    public DataLoaderService(final CsvReader reader) {
        this.reader = reader;
    }

    /**
     * 1. Loads a CSV file in the format:
     *        aLeft, aTop, aRight, aBottom, bLeft, bTop, bRight, bBottom, comments (ignored)
     * 2. Removes the header
     * 3. Turns the values into {@link BigDecimal}s
     * 4. Creates two {@link Rectangle}s from the values
     * @param filename the filename to read
     *
     * @return a {@link List} of {@link Pair}s of {@code Rectangle} objects
     */
    public List<Pair<Rectangle>> createRectanglesFromFile(String filename) {
        List<String[]> lines;
        try {
            lines = reader.read(filename);
        } catch (FileNotFoundException ex) {
            return Collections.emptyList();
        }

        // take off the header line
        lines.remove(0);

        List<Pair<Rectangle>> rectangles = new ArrayList<>();
        for (String[] line : lines) {
            Rectangle a = new Rectangle(
                    new BigDecimal(line[0]),
                    new BigDecimal(line[1]),
                    new BigDecimal(line[2]),
                    new BigDecimal(line[3])
            );
            Rectangle b = new Rectangle(
                    new BigDecimal(line[4]),
                    new BigDecimal(line[5]),
                    new BigDecimal(line[6]),
                    new BigDecimal(line[7])
            );
            rectangles.add(new Pair<>(a, b));
        }
        return rectangles;
    }

    /**
     * A helper method to create two rectangles in a 10x10 space. This is only used when there is no CSV input.
     *
     * @return a {@link Pair} of {@link Rectangle} objects
     */
    public List<Pair<Rectangle>> createSampleRectangles() {
        Rectangle a = new Rectangle(
                new BigDecimal(randomInt(0, 5)),
                new BigDecimal(randomInt(5, 10)),
                new BigDecimal(randomInt(5, 10)),
                new BigDecimal(randomInt(0, 5))
        );
        Rectangle b = new Rectangle(
                new BigDecimal(randomInt(0, 5)),
                new BigDecimal(randomInt(5, 10)),
                new BigDecimal(randomInt(5, 10)),
                new BigDecimal(randomInt(0, 5))
        );
        return Collections.singletonList(new Pair<>(a, b));
    }

    /**
     * Creates a random number between two values
     * @param min the minimum number (inclusive)
     * @param max the maximum number (inclusive)
     * @return an integer between the two
     */
    private int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
