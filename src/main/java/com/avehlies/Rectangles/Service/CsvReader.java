package com.avehlies.Rectangles.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * A simple CSV Reader used to load input CSV files.
 * In a real world application, writing a custom CSV reader is not a great idea.
 * Use of a proper CSV library like OpenCSV or Apache Commons CSV is a way better thing to do.
 */
public class CsvReader {

    /**
     * Reads a CSV file to a {@code List<String[]>}
     * @param filename the filename to read
     * @return a {@link List} of {@code String[]} values
     * @throws FileNotFoundException if the file could not be found
     */
    public List<String[]> read(String filename) throws FileNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException("Unable to read file " + filename);
        }

        List<String[]> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                String line = scanner.next();
                String[] trimmedResults = Arrays.stream(line.split(","))
                        .map(String::trim)
                        .toArray(String[]::new);
                lines.add(trimmedResults);
            }
        }

        return lines;
    }
}
