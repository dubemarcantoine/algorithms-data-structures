package com.github.dubemarcantoine.comp352.ass01;

import java.math.BigInteger;

/**
 * Implementation of the linear Tetranacci tail recursive algorithm
 */
public class TetranacciTailRecursive implements Tetranacci {

    @Override
    public void warmUp() {
        this.exec(10);
    }

    @Override
    public final String getName() {
        return "linear-tail-recursive";
    }

    /**
     * Tetranacci tail recursive calculator
     *
     * Complexity O(n)
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
        return execRec(BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE, n);
    }

    /**
     * Tail recursively calls itself
     * @param a The smallest Tetranacci number in the last 4 computed
     * @param b
     * @param c
     * @param d The biggest Tetranacci number in the last 4 computed
     * @param count
     * @return
     */
    private BigInteger execRec(BigInteger a, BigInteger b, BigInteger c, BigInteger d, int count) {
        if(count <= 3) {
            return d;
        }
        BigInteger newMax = a.add(b).add(c).add(d);
        return this.execRec(b, c, d, newMax, count - 1);
    }
}
