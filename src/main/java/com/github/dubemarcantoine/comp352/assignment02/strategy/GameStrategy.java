package com.github.dubemarcantoine.comp352.assignment02.strategy;

import com.github.dubemarcantoine.comp352.assignment02.MagneticCaveGameBoard;

import java.util.Set;

/**
 * Generic strategy to be implemented
 *
 * All strategies start by iterating on all board values to see if one of them
 * can possibly land on the 0, if not, the game is unsolvable. This check is O(n)
 */
public interface GameStrategy {

    boolean solve(MagneticCaveGameBoard magneticCaveGameBoard);

    boolean isMarkerNavigated(Set<Integer> indices, int index);
}
