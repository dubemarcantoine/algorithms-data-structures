package com.github.dubemarcantoine.comp352.assignment02.strategy;

import com.github.dubemarcantoine.comp352.assignment02.MagneticCaveGameBoard;
import com.github.dubemarcantoine.comp352.assignment02.MoveDirection;

import java.util.HashSet;
import java.util.Set;

public class RecursionStrategy implements GameStrategy {

    private MagneticCaveGameBoard magneticCaveGameBoard;

    @Override
    public boolean solve(MagneticCaveGameBoard magneticCaveGameBoard) {
        this.magneticCaveGameBoard = magneticCaveGameBoard;
        int[] tree = new int[1000];
        Set<Integer> markers = new HashSet<>();
        int marker = magneticCaveGameBoard.getMarkerStart();
        markers.add(marker);
        Integer left = magneticCaveGameBoard.getValueAtMarker(MoveDirection.LEFT, marker);
        Integer right = magneticCaveGameBoard.getValueAtMarker(MoveDirection.RIGHT, marker);
        return this.solve(marker, left, right, markers);
    }

    @Override
    public boolean isMarkerNavigated(Set<Integer> markers, int marker) {
        return markers.contains(marker);
    }

    private boolean solve(int marker, Integer leftValue, Integer rightValue, Set<Integer> markers) {
        boolean isSolved = false;
        System.out.println(marker);
        if (rightValue != null) {
            Integer newMarker = this.magneticCaveGameBoard.getMarkerAfterMove(MoveDirection.RIGHT, marker);
            if (this.isMarkerNavigated(markers, newMarker)) {
                return false;
            }
            markers.add(newMarker);
            if (this.magneticCaveGameBoard.isGameSolved(newMarker)) {
                return true;
            }
            Integer left = this.magneticCaveGameBoard.getValueAtMarker(MoveDirection.LEFT, newMarker);
            Integer right = this.magneticCaveGameBoard.getValueAtMarker(MoveDirection.RIGHT, newMarker);
            isSolved = this.solve(newMarker, left ,right, markers);
            markers.remove(newMarker);
        }
        if (leftValue != null && !isSolved) {
            Integer newMarker = this.magneticCaveGameBoard.getMarkerAfterMove(MoveDirection.LEFT, marker);
            if (this.isMarkerNavigated(markers, newMarker)) {
                return false;
            }
            markers.add(newMarker);
            if (this.magneticCaveGameBoard.isGameSolved(newMarker)) {
                return true;
            }
            Integer left = this.magneticCaveGameBoard.getValueAtMarker(MoveDirection.LEFT, newMarker);
            Integer right = this.magneticCaveGameBoard.getValueAtMarker(MoveDirection.RIGHT, newMarker);
            isSolved = this.solve(newMarker, left ,right, markers);
            markers.remove(newMarker);
        }
        return isSolved;
    }
}
