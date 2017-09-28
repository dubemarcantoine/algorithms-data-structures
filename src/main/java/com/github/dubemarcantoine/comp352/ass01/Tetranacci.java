package com.github.dubemarcantoine.comp352.ass01;

import java.math.BigInteger;

public class Tetranacci {

    public static void main(String[] args) {
        Tetranacci tetranacci = new Tetranacci();
        // Warm up
        // The first result is consistently longer if no warmup is performed
        System.out.println(tetranacci.binary(10));
        System.out.println(tetranacci.linear(10));
        System.out.println(tetranacci.tailRecursive(10));


    }

    /**
     * Tetranacci binary calculator
     * Extremely slow!
     * Complexity: O(4^n)
     *
     * @param n
     * @return
     */
    public BigInteger binary(int n) {
        if (n <= 2) {
            return BigInteger.ZERO;
        } else if (n <= 4) {
            return BigInteger.ONE;
        }
        return binary(n - 1).add(binary(n - 2)).add(binary(n - 3)).add(binary(n - 4));
    }

    /**
     *
     * @param n
     * @return
     */
    public BigInteger linear(int n) {
        if (n <= 2) {
            return BigInteger.ZERO;
        } else if (n <= 4) {
            return BigInteger.ONE;
        }
        /**
         *
         def fib(int n) {
         if (n <= 2) {
            return [0, 0, 0 ,0]
         }
         if (n <= 4) {
            return [1, 1, 0 ,0]
         }
         long[] arr = fib(n-1)
         return [arr[0] + arr[1] + arr[2] + arr[3], arr[0], arr[1], arr[2]]
         }​
         */
        return linear(n - 1);
    }

    /**
     * Tetranacci tail recursive calculator
     *
     * Complexity O(n)
     * @param n
     * @return
     */
    public BigInteger tailRecursive(int n) {
        return tailRecursive(BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE, n);
    }

    private BigInteger tailRecursive(BigInteger a, BigInteger b, BigInteger c, BigInteger d, int count) {
        if(count <= 3) {
            return d;
        }
        BigInteger newMax = a.add(b).add(c).add(d);
        return tailRecursive(b, c, d, newMax, count - 1);
    }
}
