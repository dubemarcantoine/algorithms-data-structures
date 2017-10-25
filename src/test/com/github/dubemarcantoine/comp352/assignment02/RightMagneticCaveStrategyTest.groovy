package com.github.dubemarcantoine.comp352.assignment02

import com.github.dubemarcantoine.comp352.assignment02.strategy.ListStrategy
import com.github.dubemarcantoine.comp352.assignment02.strategy.RecursionStrategy
import spock.lang.Specification
import spock.lang.Unroll

class RightMagneticCaveStrategyTest extends Specification {

    @Unroll
    def "Marker starting at #markerStart of #board and is solvable=#isSolvable"(int markerStart, boolean isSolvable, Integer[] board) {
        expect:
        new ListStrategy().solve(new MagneticCaveGameBoard(markerStart, board)) == isSolvable

        and:
        new RecursionStrategy().solve(new MagneticCaveGameBoard(markerStart, board)) == isSolvable

        where:
        markerStart | isSolvable | board
//        0           | true       | [8, 15, 1, 1, 6, 1, 1, 6, 1, 0]
//        0           | true       | [8, 16, 10, 4, 6, 10, 2, 12, 8, 0]
//        8           | true       | [17, 1, 1, 1, 1, 1, 1, 0, 8, 0]
//        22          | true       | [46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 5, 0]
//        11          | true       | [46, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0]
//        0           | true       | [7, 15, 9, 3, 5, 9, 1, 11, 7, 0]
//        0           | true       | [1, 1, 1, 1, 1, 1, 0]
//        3           | true       | [1, 1, 1, 1, 1, 1, 0]
//        1           | true       | [1, 0]
//        0           | true       | [0]
//        0           | true       | [-1, 0]
//        0           | true       | [12, 0, 0, 0, 0, 0, 0]
        0           | true       | [5, 0, 0, 6, 0, 0, 0]
        0           | false      | [5, 0, 0, 6, 0, 0, 0, 0]
        0           | false      | [12, 0, 0, 0, 0, 0, 1]
        0           | false      | [1, 0, 0]
        0           | false      | [10, 16, 4, 6, 2, 10, 0]
        0           | false      | [1000, 0]
        0           | false      | [4, 10, 1, 1, 1, 1, 10, 0]
        0           | false      | [0, 0, 0, 0, 0]
        3           | false      | [0, 0, 0, 0, 0]
    }
}
