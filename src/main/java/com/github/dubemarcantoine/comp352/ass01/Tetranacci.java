package com.github.dubemarcantoine.comp352.ass01;

import java.math.BigInteger;

public interface Tetranacci {

    /**
     * Calculates the Tetranacci numbers for n
     *
     * @param n
     * @return
     */
    BigInteger exec(int n);

    /**
     * Because the execution time is always longer the first time a function
     * is executed, this function is used as a warm up before the tests begin
     */
    void warmUp();
}
