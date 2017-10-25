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
        System.out.println("-------- List Strategy -----------");
        this.gameBoard = magneticCaveGameBoard;
        System.out.println(this.gameBoard.toString());

        int marker = this.gameBoard.getMarkerStart();
        if (this.gameBoard.isGameSolved(marker)) {
            return true;
        }
        this.tree = new ArrayList<>(Collections.nCopies(20, null));

        // Set up initial node
        int treeIndex = 0;
        this.tree.set(0, marker);

        boolean isSolved = solve(treeIndex, marker);
        System.out.println(this.toString());
        return isSolved;
    }

    /**
     * Checks if this marker has been visited by the parents
     * It is the equivalent of bubbling up in a binary tree, so the complexity is O(log(n))
     * @param parentNode
     * @param marker
     * @return
     */
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

    /**
     * Recursively solves the magnetic cave
     * @param treeIndex
     * @param marker
     * @return
     */
    private boolean solve(int treeIndex, int marker) {
        System.out.println("index=" + treeIndex + "   marker="+marker);
        // Get markers at left and right of current marker
        Integer markerRight = this.gameBoard.getMarkerAfterMove(MoveDirection.RIGHT, marker);
        if (this.gameBoard.isGameSolved(markerRight)) {
            return true;
        }
        Integer markerLeft = this.gameBoard.getMarkerAfterMove(MoveDirection.LEFT, marker);
        if (this.gameBoard.isGameSolved(markerLeft)) {
            return true;
        }

        // Insert the right marker at the right of the current index
        this.insertRight(treeIndex, markerRight);
        // Insert the left marker at the left of the current index
        this.insertLeft(treeIndex, markerLeft);

        // Try solving the right hand side first
        Integer rightMarkerValue = this.gameBoard.getMarkerValue(markerRight);
        // Make sure that the marker has never been navigated to
        if (rightMarkerValue != null && !this.isMarkerNavigated(treeIndex, markerRight)) {
            System.out.println("Going right with marker value="+rightMarkerValue);
            // Solve with new marker if right value at marker was in bound
            if (this.solve(this.indexAtRight(treeIndex), markerRight)) {
                return true;
            }
        }

        // Try solving by the left hand side
        Integer leftMarkerValue = this.gameBoard.getMarkerValue(markerLeft);
        // Make sure that the marker has never been navigated to
        if (leftMarkerValue != null && !this.isMarkerNavigated(treeIndex, markerLeft)) {
            System.out.println("Going left with marker value="+leftMarkerValue);
            // Solve with new marker if left value at marker was in bound
            if (this.solve(this.indexAtLeft(treeIndex), markerLeft)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a value at an index
     * @param treeIndex
     * @return
     */
    private Integer valueAt(int treeIndex) {
        return this.tree.get(treeIndex);
    }

    /**
     * Inserts a value to the left of the current node at index
     * @param treeIndex
     * @param value
     */
    private void insertLeft(int treeIndex, Integer value) {
        this.insert(this.indexAtLeft(treeIndex), value);
    }

    /**
     * Inserts a value to the right of the current node at index
     * @param treeIndex
     * @param value
     */
    private void insertRight(int treeIndex, Integer value) {
        this.insert(this.indexAtRight(treeIndex), value);
    }

    private void insert(int index, Integer value) {
        if (index >= this.tree.size()) {
            // Double the list
            this.tree.addAll(new ArrayList<>(Collections.nCopies(this.tree.size() * 2, null)));
        }
        this.tree.set(index, value);
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

    @Override
    public String toString() {
        return "ListStrategy{" +
                "tree=" + tree +
                '}';
    }
}
