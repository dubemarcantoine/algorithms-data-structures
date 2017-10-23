package com.github.dubemarcantoine.comp352.assignment02.strategy;

import com.github.dubemarcantoine.comp352.assignment02.MagneticCaveGameBoard;
import com.github.dubemarcantoine.comp352.assignment02.MoveDirection;

public class RecursionStrategy implements GameStrategy {

    private MagneticCaveGameBoard magneticCaveGameBoard;

    @Override
    public boolean solve(MagneticCaveGameBoard magneticCaveGameBoard) {
        this.magneticCaveGameBoard = magneticCaveGameBoard;
        int[] tree = new int[1000];
        int marker = magneticCaveGameBoard.getMarkerStart();
        Integer left = magneticCaveGameBoard.getValueAtMarker(MoveDirection.LEFT, marker);
        Integer right = magneticCaveGameBoard.getValueAtMarker(MoveDirection.RIGHT, marker);
        return this.solve(marker, left, right);
    }

    private boolean solve(int marker, Integer leftValue, Integer rightValue) {
        if (rightValue != null) {
            return this.solve(marker, leftValue, rightValue, MoveDirection.RIGHT);
        }
        if (leftValue != null) {
            return this.solve(marker, leftValue, rightValue, MoveDirection.LEFT);
        }
        return false;
    }

    private boolean solve(int marker, Integer leftValue, Integer rightValue, MoveDirection moveDirection) {
        Integer newMarker = this.magneticCaveGameBoard.getMarkerAfterMove(moveDirection, marker);
        if (this.magneticCaveGameBoard.isGameSolved(newMarker)) {
            System.out.println("solved");
            return true;
        }
        Integer left = this.magneticCaveGameBoard.getValueAtMarker(MoveDirection.LEFT, newMarker);
        Integer right = this.magneticCaveGameBoard.getValueAtMarker(MoveDirection.RIGHT, newMarker);
        this.solve(newMarker, left ,right);
        return false;
    }
}
