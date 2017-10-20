package com.github.dubemarcantoine.comp352.assignment02;

public class MagneticCaveGameBoard {

    public static final int END_WINNING_VALUE = 0;
    public static final int DEFAULT_BOARD_SIZE = 10;

    /**
     * The current marker on the board
     */
    private int marker;

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
        this.marker = marker;
        this.markerStart = marker;
        this.board = board;
        this.size = this.board.length;
    }

    /**
     * Moves the marker left if negative and right if positive
     * @param steps
     * @throws IndexOutOfBoundsException
     */
    public void moveMarker(int steps) throws IndexOutOfBoundsException {
        if (this.marker + steps >= this.board.length) {
            throw new IndexOutOfBoundsException("Cannot move to this position");
        }
        this.marker += steps;
    }

    /**
     * Checks if the marker is on the last index which means the game is solved
     * @return
     */
    public boolean isGameSolved() {
        return this.board[this.marker - 1] == END_WINNING_VALUE;
    }

    public int getMarker() {
        return this.marker;
    }

    public int getMarkerStart() {
        return this.markerStart;
    }

    public Integer[] getBoard() {
        return this.board;
    }

    public int getSize() {
        return this.size;
    }
}
