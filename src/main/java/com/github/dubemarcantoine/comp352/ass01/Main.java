package com.github.dubemarcantoine.comp352.ass01;

public class Main {

    public static void main(String[] args) {
        TetranacciBinary tetranacciBinary = new TetranacciBinary();
        TetranacciLinear tetranacciLinear = new TetranacciLinear();
        TetranacciTailRecursive tetranacciTailRecursive = new TetranacciTailRecursive();

        tetranacciBinary.warmUp();
        tetranacciLinear.warmUp();
        tetranacciTailRecursive.warmUp();
    }
}
