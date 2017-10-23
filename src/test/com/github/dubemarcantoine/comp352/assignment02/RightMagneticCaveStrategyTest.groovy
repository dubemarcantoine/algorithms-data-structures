package com.github.dubemarcantoine.comp352.assignment02

import com.github.dubemarcantoine.comp352.assignment02.strategy.RecursionStrategy
import spock.lang.Specification
import spock.lang.Unroll

class RightMagneticCaveStrategyTest extends Specification {

    @Unroll
    def "Marker starting at #markerStart of #board and is solvable=#isSolvable"(int markerStart, boolean isSolvable, Integer[] board) {
        expect:
        new RecursionStrategy().solve(new MagneticCaveGameBoard(markerStart, board)) == isSolvable

        where:
        markerStart | isSolvable | board
        0           | true       | [8, 15, 1, 1, 6, 1, 1, 6, 1, 0]
        0           | true       | [8, 16, 10, 4, 6, 10, 2, 12, 8, 0]
        0           | true       | [1, 1, 1, 1, 1, 1, 0]
        0           | true       | [0]
        0           | true       | [12, 0, 0, 0, 0, 0, 0]
        0           | false      | [1, 0, 0]
        0           | false      | [10, 16, 4, 6, 2, 10, 0]
        0           | false      | [1000, 0]
        0           | false      | [4, 10, 1, 1, 1, 1, 10, 0]
        0           | false      | [0, 0, 0, 0, 0]
    }
}