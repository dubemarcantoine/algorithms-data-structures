package com.github.dubemarcantoine.comp352.ass01;

import java.util.stream.IntStream;

/**
 * Main class
 */
public class Main {

    public static final int TETTRANACCI_START = 5;
    public static final int TETRANACCI_END = 100;

    public static void main(String[] args) {
        TetranacciBinary tetranacciBinary = new TetranacciBinary();
        TetranacciLinear tetranacciLinear = new TetranacciLinear();
        TetranacciTailRecursive tetranacciTailRecursive = new TetranacciTailRecursive();

        tetranacciBinary.warmUp();
        tetranacciLinear.warmUp();
        tetranacciTailRecursive.warmUp();

        tetraTime(tetranacciTailRecursive);
    }

    private static void tetraTime(Tetranacci tetranacci) {
        IntStream.range(TETTRANACCI_START, TETRANACCI_END + 1)
                .forEach(i -> {
                    long startTime = System.nanoTime();
                    tetranacci.exec(i);
                    long endTime = System.nanoTime() - startTime;
                    System.out.println(endTime);
                });
    }
}
