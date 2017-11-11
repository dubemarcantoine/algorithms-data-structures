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
        System.out.println(new ListStrategy().solve(new MagneticCaveGameBoard()));
        System.out.println(new RecursionStrategy().solve(new MagneticCaveGameBoard()));
    }
}
