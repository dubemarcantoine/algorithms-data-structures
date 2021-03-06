package com.github.dubemarcantoine.comp352.ass01;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the linear Tetranacci algorithm
 */
public class TetranacciLinear implements Tetranacci {

    @Override
    public void warmUp() {
        this.exec(10);
    }

    @Override
    public final String getName() {
        return "linear";
    }

    /**
     * Calculates Tetranacci in linear time
     *
     * @param n
     * @return
     */
    @Override
    public BigInteger exec(int n) {
        return this.execRec(n).get(3);
    }

    /**
     * Recursively calls itself and runs in linear time
     *
     * @param n
     * @return
     */
    private List<BigInteger> execRec(int n) {
        if (n <= 3) {
            return Arrays.asList(BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO);
        } else if (n <= 4) {
            return Arrays.asList(BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE, BigInteger.ONE);
        }

        List<BigInteger> list = this.execRec(n - 1);
        BigInteger newMax = list.get(0)
                .add(list.get(1))
                .add(list.get(2))
                .add(list.get(3));
        return Arrays.asList(list.get(1), list.get(2), list.get(3), newMax);
    }
}
