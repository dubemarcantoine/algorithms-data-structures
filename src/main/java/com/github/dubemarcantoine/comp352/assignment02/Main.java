package com.github.dubemarcantoine.comp352.assignment02;

import com.github.dubemarcantoine.comp352.assignment02.strategy.RecursionStrategy;

/**
 * The main class for the start of the program
 */
public class Main {

    public static void main(String[] args) {
//        Integer[] values = {8, 16, 10, 4, 6, 10, 2, 12, 8, 0};
        Integer[] values = {8, 20, 1, 1, 6, 1, 1, 6, 1, 0};
        MagneticCaveGameBoard gameBoard = new MagneticCaveGameBoard(0, values);
        System.out.println(new RecursionStrategy().solve(gameBoard));
    }
}
