package com.github.dubemarcantoine.comp352.ass01;

import java.math.BigInteger;
import java.util.ArrayList;
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
            List<BigInteger> list = new ArrayList<>();
            list.add(BigInteger.ZERO);
            list.add(BigInteger.ZERO);
            list.add(BigInteger.ZERO);
            list.add(BigInteger.ZERO);
            return list;
        } else if (n <= 4) {
            List<BigInteger> list = new ArrayList<>();
            list.add(BigInteger.ONE);
            list.add(BigInteger.ONE);
            list.add(BigInteger.ZERO);
            list.add(BigInteger.ZERO);
            return list;
        }

        List<BigInteger> list = this.execRec(n - 1);
        BigInteger newMax = list.get(0)
                .add(list.get(1))
                .add(list.get(2))
                .add(list.get(3));
        List<BigInteger> newList = new ArrayList<>();
        newList.add(newMax);
        newList.add(list.get(0));
        newList.add(list.get(1));
        newList.add(list.get(2));
        return newList;
    }
}
