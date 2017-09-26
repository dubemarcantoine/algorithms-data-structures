package com.github.dubemarcantoine.comp352.ass01;

import java.math.BigInteger;

public class Tetranacci {

    public static void main(String[] args) {
        System.out.println(new Tetranacci().binary(10));
        System.out.println(new Tetranacci().tailRecursive(5000));
    }

    public BigInteger binary(int n) {
        if (n <= 2) {
            return BigInteger.ZERO;
        } else if (n <= 4) {
            return BigInteger.ONE;
        }
        return binary(n - 1).add(binary(n - 2)).add(binary(n - 3)).add(binary(n - 4));
    }

    public BigInteger linear(int n) {

        return BigInteger.ONE;
    }

    public BigInteger tailRecursive(int n) {
        if (n <= 2) {
            return BigInteger.ZERO;
        } else if (n <= 4) {
            return BigInteger.ONE;
        }
        return tailRecursive(BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE, n);
    }

    private BigInteger tailRecursive(BigInteger a, BigInteger b, BigInteger c, BigInteger d, int count) {
        if(count <= 0) {
            return a;
        }
        return tailRecursive(b, c, d, a.add(b).add(c).add(d), count - 1);
    }
}
