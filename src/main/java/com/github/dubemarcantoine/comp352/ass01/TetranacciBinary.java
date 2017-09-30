package com.github.dubemarcantoine.comp352.ass01;

import java.math.BigInteger;

public class TetranacciBinary {

    public void warmUp() {
        this.exec(10);
    }

    /**
     * Tetranacci exec calculator
     * Extremely slow!
     * Complexity: O(4^n)
     *
     * @param n
     * @return
     */
    public BigInteger exec(int n) {
        if (n <= 2) {
            return BigInteger.ZERO;
        } else if (n <= 4) {
            return BigInteger.ONE;
        }
        return this.exec(n - 1)
                .add(this.exec(n - 2))
                .add(this.exec(n - 3))
                .add(this.exec(n - 4));
    }
}
