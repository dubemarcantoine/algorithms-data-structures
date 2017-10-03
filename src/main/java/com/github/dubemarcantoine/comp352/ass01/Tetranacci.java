package com.github.dubemarcantoine.comp352.ass01;

import java.math.BigInteger;

/**
 * Definitions for the Tetranacci implementations
 */
public interface Tetranacci {

    /**
     * Because the execution time is always longer the first time a function
     * is executed, this function is used as a warm up before the tests begin
     */
    void warmUp();

    String getName();

    /**
     * Calculates the Tetranacci numbers for n
     *
     * @param n
     * @return
     */
    BigInteger exec(int n);
}
