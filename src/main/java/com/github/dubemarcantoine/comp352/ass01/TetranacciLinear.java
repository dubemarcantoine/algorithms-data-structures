package com.github.dubemarcantoine.comp352.ass01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TetranacciLinear implements Tetranacci {

    @Override
    public void warmUp() {
        this.exec(10);
    }

    /**
     * Calculates Tetranacci in linear time
     *
     * @param n
     * @return
     */
    @Override
    public BigInteger exec(int n) {
        return this.execRec(n).get(0);
    }

    /**
     * Recursively calls itself and runs in linear time
     *
     * @param n
     * @return
     */
    private List<BigInteger> execRec(int n) {
        if (n <= 2) {
            return Arrays.asList(BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO);
        } else if (n <= 4) {
            return Arrays.asList(BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO, BigInteger.ZERO);
        }

        List<BigInteger> list = this.execRec(n - 1);
        BigInteger newMax = list.get(0)
                .add(list.get(1))
                .add(list.get(2))
                .add(list.get(3));
        return Arrays.asList(newMax, list.get(0), list.get(1), list.get(2));
    }
}
