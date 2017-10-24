package com.github.dubemarcantoine.comp352.assignment02;

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
        // TODO: Generate random game
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

    public int getSteps(int value) {
        return value % 2 == 0
                ? value / 2
                : value / 2 + 1;
    }

    public Integer getValueAtMarker(MoveDirection moveDirection, int marker) {
        Integer value = null;
        int stepCount = this.getSteps(this.getMarkerValue(marker));
        switch (moveDirection) {
            case LEFT:
                if (marker - stepCount > 0) {
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
        return marker == this.getBoardSize() -1 &&
                this.board[marker] == END_WINNING_VALUE;
    }

    public int getMarkerStart() {
        return this.markerStart;
    }

    public Integer[] getBoard() {
        return this.board;
    }

    public int getBoardSize() {
        return this.size;
    }
}
