package com.github.dubemarcantoine.comp352.ass01;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Main class
 */
public class Main {

    public static final int TETTRANACCI_START = 5;
    public static final int TETRANACCI_END = 100;
    public static final int TETRANACCI_INCREMENT = 5;

    public static final String RESULT_FOLDER = "results/";
    public static final String RESULT_FILE_EXT = ".txt";

    public static void main(String[] args) {
        TetranacciBinary tetranacciBinary = new TetranacciBinary();
        TetranacciLinear tetranacciLinear = new TetranacciLinear();
        TetranacciTailRecursive tetranacciTailRecursive = new TetranacciTailRecursive();

        tetranacciBinary.warmUp();
        tetranacciLinear.warmUp();
        tetranacciTailRecursive.warmUp();

        createResultsDirectory();

        evaluateTetraTime(tetranacciBinary, TETTRANACCI_START, 30);
        evaluateTetraTime(tetranacciLinear, TETTRANACCI_START, TETRANACCI_END);
        evaluateTetraTime(tetranacciTailRecursive, TETTRANACCI_START, TETRANACCI_END);
    }

    /**
     * Calculates the time it takes to execute Tetranacci
     * @param tetranacci
     * @param start
     * @param end
     */
    private static void evaluateTetraTime(Tetranacci tetranacci, int start, int end) {
        final String FILE_NAME = RESULT_FOLDER + tetranacci.getName() + "-"
                + System.currentTimeMillis() + RESULT_FILE_EXT;
        List<String> results = new ArrayList<>();
        results.add("number,nano,time");

        IntStream.iterate(start, x -> x + TETRANACCI_INCREMENT)
                .limit(end / TETRANACCI_INCREMENT)
                .forEach(i -> {
                    long startTime = System.nanoTime();
                    BigInteger value = tetranacci.exec(i);
                    long endTime = System.nanoTime() - startTime;
                    results.add(i + "," + endTime + "," + value);
                });

        writeResultsToFile(results, FILE_NAME);
    }

    /**
     * Writes the results to a file
     * @param results
     * @param fileName
     */
    private static void writeResultsToFile(List<String> results, String fileName) {
        try {
            Files.write(Paths.get(fileName), results);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the results directory
     */
    private static void createResultsDirectory() {
        try {
            Path path = Paths.get(RESULT_FOLDER);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
