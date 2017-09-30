package com.github.dubemarcantoine.comp352.ass01;

import java.math.BigInteger;

public class TetranacciTailRecursive implements Tetranacci {

    /**
     * Tetranacci tail recursive calculator
     *
     * Complexity O(n)
     * @param n
     * @return
     */
    @Override
    public BigInteger exec(int n) {
        return execRec(BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE, n);
    }

    /**
     * Tail recursively calls itself
     * @param a
     * @param b
     * @param c
     * @param d
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

    @Override
    public void warmUp() {
        this.exec(10);
    }

    @Override
    public final String getName() {
        return "linear-tail-recursive";
    }
}
