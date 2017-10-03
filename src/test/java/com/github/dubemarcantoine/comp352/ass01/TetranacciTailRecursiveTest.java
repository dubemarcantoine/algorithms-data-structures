package com.github.dubemarcantoine.comp352.ass01;

import org.junit.*;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class TetranacciTailRecursiveTest {

    @Test
    public void baseCase0() {
        assertEquals(BigInteger.ZERO, new TetranacciTailRecursive().exec(0));
    }

    @Test
    public void baseCase1() {
        assertEquals(BigInteger.ZERO, new TetranacciTailRecursive().exec(1));
    }

    @Test
    public void baseCase2() {
        assertEquals(BigInteger.ZERO, new TetranacciTailRecursive().exec(2));
    }

    @Test
    public void baseCase3() {
        assertEquals(BigInteger.ZERO, new TetranacciTailRecursive().exec(3));
    }

    @Test
    public void baseCase4() {
        assertEquals(BigInteger.ONE, new TetranacciTailRecursive().exec(4));
    }

    @Test
    public void exec10() {
        assertEquals(BigInteger.valueOf(56), new TetranacciTailRecursive().exec(10));
    }
}