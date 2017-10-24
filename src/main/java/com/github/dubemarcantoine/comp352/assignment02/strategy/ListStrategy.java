package com.github.dubemarcantoine.comp352.assignment02.strategy;

import com.github.dubemarcantoine.comp352.assignment02.MagneticCaveGameBoard;
import com.github.dubemarcantoine.comp352.assignment02.MoveDirection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * List based strategy
 */
public class ListStrategy implements GameStrategy {

    /**
     * Contains the indices of the markers in the board that
     * come across the execution
     */
    private List<Integer> tree;

    private MagneticCaveGameBoard gameBoard;

    @Override
    public boolean solve(MagneticCaveGameBoard magneticCaveGameBoard) {
        this.gameBoard = magneticCaveGameBoard;

        this.tree = new ArrayList<>(Collections.nCopies(1000, null));
        int treeIndex = 0;
        int marker = this.gameBoard.getMarkerStart();
        this.tree.set(0, marker);

        return solve(treeIndex, marker);
    }

    private boolean isMarkerNavigated(int parentNode, int marker) {
        Integer parentNodeMarker = this.valueAt(parentNode);
        if (parentNodeMarker == marker) {
            return true;
        }
        if (parentNode == 0) {
            return false;
        }
        parentNode = (parentNode - 1) / 2;
        return isMarkerNavigated(parentNode, marker);
    }

    private boolean solve(int treeIndex, int marker) {
        // Get markers at left and right of current marker
        Integer markerRight = this.gameBoard.getMarkerAfterMove(MoveDirection.RIGHT, marker);
        if (this.gameBoard.isGameSolved(markerRight)) {
            return true;
        }
        Integer markerLeft = this.gameBoard.getMarkerAfterMove(MoveDirection.LEFT, marker);
        if (this.gameBoard.isGameSolved(markerLeft)) {
            return true;
        }

        this.insertRight(treeIndex, markerRight);
        this.insertLeft(treeIndex, markerLeft);

        Integer rightMarkerValue = this.gameBoard.getMarkerValue(markerRight);
        if (rightMarkerValue != null && !this.isMarkerNavigated(treeIndex, markerRight)) {
            if (this.solve(this.indexAtRight(treeIndex), markerRight)) {
                return true;
            }
        }
        Integer leftMarkerValue = this.gameBoard.getMarkerValue(markerLeft);
        if (leftMarkerValue != null && !this.isMarkerNavigated(treeIndex, markerLeft)) {
            if (this.solve(this.indexAtLeft(treeIndex), markerLeft)) {
                return true;
            }
        }

        return false;
    }

    private Integer valueAt(int treeIndex) {
        return this.tree.get(treeIndex);
    }

    /**
     * Inserts a value to the left of the current node at index
     * @param treeIndex
     * @param value
     */
    private void insertLeft(int treeIndex, Integer value) {
        this.tree.set(this.indexAtLeft(treeIndex), value);
    }

    /**
     * Inserts a value to the right of the current node at index
     * @param treeIndex
     * @param value
     */
    private void insertRight(int treeIndex, Integer value) {
        this.tree.set(this.indexAtRight(treeIndex), value);
    }

    /**
     * Returns the index of the left node
     * @param treeIndex
     * @return
     */
    private int indexAtLeft(int treeIndex) {
        return 2 * treeIndex + 1;
    }

    /**
     * Returns the index of the right node
     * @param treeIndex
     * @return
     */
    private int indexAtRight(int treeIndex) {
        return 2 * treeIndex + 2;
    }
}
