package com.github.dubemarcantoine.comp352.ass01;

public class Tetranacci {

    public static void main(String[] args) {
        System.out.println(new Tetranacci().binary(10));
        System.out.println(new Tetranacci().tailRecursive(100));
    }

    public long binary(int n) {
        if (n <= 2) {
            return 0;
        } else if (n <= 4) {
            return 1;
        } else {
            return binary(n - 1) + binary(n - 2) + binary(n - 3) + binary(n - 4);
        }
    }

    public long tailRecursive(int n) {
        if (n <= 2) {
            return 0;
        } else if (n <= 4) {
            return 1;
        }
        return tailRecursive(0, 0, 0, 1, n);
    }

    private long tailRecursive(long a, long b, long c, long d, int count) {
        if(count <= 0) {
            return a;
        }
        return tailRecursive(b, c, d, a+b+c+d, count-1);
    }
}
