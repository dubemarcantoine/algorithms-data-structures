package com.github.dubemarcantoine.comp352.assignment02.strategy;

import com.github.dubemarcantoine.comp352.assignment02.MagneticCaveGameBoard;

import java.util.Set;

public class ArrayStrategy implements GameStrategy {

    @Override
    public boolean solve(MagneticCaveGameBoard magneticCaveGameBoard) {
        return false;
    }

    @Override
    public boolean isMarkerNavigated(Set<Integer> indices, int index) {
        return false;
    }
}
