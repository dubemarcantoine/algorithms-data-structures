package com.github.dubemarcantoine.comp352.assignment02.strategy;

import com.github.dubemarcantoine.comp352.assignment02.MagneticCaveGameBoard;
import com.github.dubemarcantoine.comp352.assignment02.MoveDirection;

import java.util.HashSet;
import java.util.Set;

/**
 * Recursive strategy
 */
public class RecursionStrategy implements GameStrategy {

    private MagneticCaveGameBoard magneticCaveGameBoard;

    @Override
    public boolean solve(MagneticCaveGameBoard magneticCaveGameBoard) {
        System.out.println("-------- Recursion Strategy -----------");
        this.magneticCaveGameBoard = magneticCaveGameBoard;
        System.out.println(this.magneticCaveGameBoard.toString());

        Set<Integer> markers = new HashSet<>();
        int marker = magneticCaveGameBoard.getMarkerStart();
        // Check just in case the board is [0], the game is won!
        if (this.magneticCaveGameBoard.isGameSolved(marker)) {
            return true;
        }
        markers.add(marker);
        Integer left = magneticCaveGameBoard.getValueAtMarkerAfterSteps(MoveDirection.LEFT, marker);
        Integer right = magneticCaveGameBoard.getValueAtMarkerAfterSteps(MoveDirection.RIGHT, marker);
        return this.solve(marker, left, right, markers);
    }

    private boolean isMarkerNavigated(Set<Integer> markers, int marker) {
        return markers.contains(marker);
    }

    private boolean solve(int marker, Integer leftValue, Integer rightValue, Set<Integer> markers) {
        System.out.println("marker="+marker+"  leftValue="+leftValue+"  rightValue="+ rightValue);
        if (rightValue != null) {
            System.out.println("Going right");
            Integer newMarker = this.magneticCaveGameBoard.getMarkerAfterMove(MoveDirection.RIGHT, marker);
            if (this.magneticCaveGameBoard.isGameSolved(newMarker)) {
                return true;
            }

            if (this.isMarkerNavigated(markers, newMarker)) {
                markers.remove(newMarker);
                return false;
            }
            markers.add(newMarker);

            Integer left = this.magneticCaveGameBoard.getValueAtMarkerAfterSteps(MoveDirection.LEFT, newMarker);
            Integer right = this.magneticCaveGameBoard.getValueAtMarkerAfterSteps(MoveDirection.RIGHT, newMarker);
            if (this.solve(newMarker, left ,right, markers)) {
                return true;
            }
            markers.remove(newMarker);
        }
        if (leftValue != null) {
            System.out.println("Going left");
            Integer newMarker = this.magneticCaveGameBoard.getMarkerAfterMove(MoveDirection.LEFT, marker);
            if (this.magneticCaveGameBoard.isGameSolved(newMarker)) {
                return true;
            }

            if (this.isMarkerNavigated(markers, newMarker)) {
                markers.remove(newMarker);
                return false;
            }
            markers.add(newMarker);

            Integer left = this.magneticCaveGameBoard.getValueAtMarkerAfterSteps(MoveDirection.LEFT, newMarker);
            Integer right = this.magneticCaveGameBoard.getValueAtMarkerAfterSteps(MoveDirection.RIGHT, newMarker);
            if (this.solve(newMarker, left ,right, markers)) {
                return true;
            }
            markers.remove(newMarker);
        }
        return false;
    }
}
