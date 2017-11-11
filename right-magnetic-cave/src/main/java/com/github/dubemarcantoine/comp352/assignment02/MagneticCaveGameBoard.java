package com.github.dubemarcantoine.comp352.assignment02;

import java.util.Arrays;

/**
 * Holds the board and has utility functions
 */
public class MagneticCaveGameBoard {

    public static final int END_WINNING_VALUE = 0;
    public static final int DEFAULT_BOARD_SIZE = 10;

    /**
     * The origin marker
     */
    private int markerStart;

    /**
     * The board of the game with the integers
     */
    private Integer[] board;

    private int size;

    /**
     * Generates a random game and places the marker at 0
     */
    MagneticCaveGameBoard() {
        this.markerStart = 0;
        this.board = new Integer[DEFAULT_BOARD_SIZE];
        for (int i=0; i<DEFAULT_BOARD_SIZE - 1; i++) {
            this.board[i] = (int)Math.floor(Math.random() * DEFAULT_BOARD_SIZE) + 1;
        }
        // Set last value as 0
        this.board[DEFAULT_BOARD_SIZE - 1] = 0;
        this.size = this.board.length;
    }

    /**
     * Predefined marker and game
     * @param marker
     * @param board
     */
    MagneticCaveGameBoard(int marker, Integer[] board) {
        this.markerStart = marker;
        this.board = board;
        this.size = this.board.length;
    }

    /**
     * Returns the number of steps for a value
     * @param value
     * @return
     */
    public int getSteps(int value) {
        return value % 2 == 0
                ? value / 2
                : value / 2 + 1;
    }

    /**
     * Returns the value at a marker after a move in a direction
     * @param moveDirection
     * @param marker
     * @return
     */
    public Integer getValueAtMarkerAfterSteps(MoveDirection moveDirection, int marker) {
        Integer value = null;
        int stepCount = this.getSteps(this.getMarkerValue(marker));
        switch (moveDirection) {
            case LEFT:
                if (marker - stepCount >= 0) {
                    value = this.board[marker - stepCount];
                }
                break;
            case RIGHT:
                if (marker + stepCount < this.getBoardSize()) {
                    value = this.board[marker + stepCount];
                }
                break;
        }
        return value;
    }

    /**
     * Returns the marker after a move
     * @param moveDirection
     * @param marker
     * @return
     */
    public int getMarkerAfterMove(MoveDirection moveDirection, int marker) {
        int stepCount = this.getSteps(this.getMarkerValue(marker));
        switch (moveDirection) {
            case LEFT:
                return marker - stepCount;
            case RIGHT:
                return marker + stepCount;
        }
        return marker;
    }

    /**
     * Returns the current marker value
     * @param marker
     * @return
     */
    public Integer getMarkerValue(int marker) {
        if (marker < 0 || marker >= this.getBoardSize()) {
            return null;
        }
        return this.board[marker];
    }

    /**
     * Checks if the marker is on the last index which means the game is solved
     * @return
     */
    public boolean isGameSolved(int marker) {
        if (marker < 0 || marker >= this.getBoardSize()) {
            return false;
        }
        // Check that the marker is at last position and check that last position is the winning value
        return marker == this.getBoardSize() -1
                && this.board[marker] == END_WINNING_VALUE;
    }

    public boolean isSolvable() {
        return false;
    }

    /**
     * Returns the marker for the start
     * @return
     */
    public int getMarkerStart() {
        return this.markerStart;
    }

    public Integer[] getBoard() {
        return this.board;
    }

    public int getBoardSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return "MagneticCaveGameBoard{" +
                "markerStart=" + markerStart +
                ", board=" + Arrays.toString(board) +
                '}';
    }
}
