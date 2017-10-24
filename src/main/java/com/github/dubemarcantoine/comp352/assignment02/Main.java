package com.github.dubemarcantoine.comp352.assignment02;

import com.github.dubemarcantoine.comp352.assignment02.strategy.ListStrategy;
import com.github.dubemarcantoine.comp352.assignment02.strategy.RecursionStrategy;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The main class for the start of the program
 */
public class Main {

    public static void main(String[] args) {
        Integer[] values = {6, 16, 1, 4, 0, 0, 0, 0, 0, 0};
//        Integer[] values = {1, 1, 1, 1, 0};
        MagneticCaveGameBoard gameBoard = new MagneticCaveGameBoard(0, values);
        System.out.println(new ListStrategy().solve(gameBoard));
    }
}
