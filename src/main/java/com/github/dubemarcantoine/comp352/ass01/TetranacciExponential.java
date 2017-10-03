package com.github.dubemarcantoine.comp352.ass01;

import java.math.BigInteger;

/**
 * Implementation of the exponential Tetranacci algorithm
 *
 * Be aware that it is extremely slow and should not be used to compute big Tetranacci numbers
 * unless you want to see the Universe burn :-)
 */
public class TetranacciExponential implements Tetranacci {

    @Override
    public void warmUp() {
        this.exec(10);
    }

    @Override
    public final String getName() {
        return "exponential";
    }

    /**
     * Tetranacci exec calculator
     * Extremely slow!
     * Complexity: O(4^n)
     *
     * @param n
     * @return
     */
    @Override
    public BigInteger exec(int n) {
        if (n <= 3) {
            return BigInteger.ZERO;
        } else if (n <= 4) {
            return BigInteger.ONE;
        }

        return this.execRec(n);
    }

    private BigInteger execRec(int n) {
        if (n <= 2) {
            return BigInteger.ZERO;
        } else if (n <= 4) {
            return BigInteger.ONE;
        }

        return this.execRec(n - 1)
                .add(this.execRec(n - 2))
                .add(this.execRec(n - 3))
                .add(this.execRec(n - 4));
    }
}
