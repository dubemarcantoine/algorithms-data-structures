package com.github.dubemarcantoine.comp352.ass01;

public class Tetranacci {

    public static void main(String[] args) {
        System.out.println(new Tetranacci().binary(10));
    }

    public int binary(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 1) {
            return n;
        } else {
            return binary(n - 1) + binary(n - 2) + binary(n - 3) + binary(n - 4);
        }
    }
}
