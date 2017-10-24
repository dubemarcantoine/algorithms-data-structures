# Right Magnetic Cave Algorithm
## a) Briefly explain the time and space complexity for both versions of your game. You can write your answer in a separate file and submit it together with the other submissions.

## b) For the first version of your solution describe the type of recursion used in yourimplementation. Does the particular type of recursion have an impact on the time and space complexity? Justify your answer.

## c) For the second part of your solution, justify why you choose that particular data structure (e.g. why you choose a stack and not a queue, etc.)

## d) Provide test logs for at least 20 different game configurations, sufficiently complete to show that your solution works for various row sizes and square values.

## e) If possible, explain how one can detect unsolvable array configurations and whether there exists a way to speed up the execution time. Answering this question is optional and you can earn bonus marks by submitting a good solution.
We should ensure

## Pseudo Code
### Recursive Strategy
```
Algorithm solve(gameBoard)
    Input: A gameBoard containing the board and utility functions to navigate that board
    Output: Boolean if the board is solvable or not
    
    markers ← [] {A set of Integers}
    marker ← gameBoard.markerStart
    IF gameBoard.isGameSolved(marker) THEN
        return true
    markers.add(marker)
    left ← gameBoard.getValueAtMarkerAfterSteps(LEFT, marker)
    right ← gameBoard.getValueAtMarkerAfterSteps(RIGHT, marker)
    
    return this.solve(marker, left, right, markers)
    
Algorithm solve(marker, leftValue, rightValue, markers)
    Input:
        marker: The current position of the marker on the game board
        leftValue: The Integer value to the left of the marker
        rightValue: The Integer value to the right of the marker
        markers: A set containing all the previous markers navigated to
    Output:
        If the board has been solved
    
    IF rightValue THEN
        newMarker ← gameBoard.getMarkerAfterMove(RIGHT, marker)
        IF gameBoard.isGameSolved(newMarker) THEN
            return true
        
        IF markers.contains(newMarker) THEN
            markers.remove(newMarker)
            return false
        markers.add(newMarker)
        
        left ← gameBoard.getValueAtMarkerAfterSteps(LEFT, newMarker)
        right ← gameBoard.getValueAtMarkerAfterSteps(RIGHT, newMarker)
        IF solve(newMarker, left, right, markers) THEN
            return true
        
        markers.remove(newMarker)
    IF leftValue THEN
        newMarker ← gameBoard.getMarkerAfterMove(LEFT, marker)
        IF gameBoard.isGameSolved(newMarker) THEN
            return true
        
        IF markers.contains(newMarker) THEN
            markers.remove(newMarker)
            return false
        markers.add(newMarker)
        
        left ← gameBoard.getValueAtMarkerAfterSteps(LEFT, newMarker)
        right ← gameBoard.getValueAtMarkerAfterSteps(RIGHT, newMarker)
        IF solve(newMarker, left, right, markers) THEN
            return true
        
        markers.remove(newMarker)
```

### List Strategy
```
Algorithm solve(gameBoard)
    Input: A gameBoard containing the board and utility functions to navigate that board
    Output: Boolean if the board is solvable or not
    
    tree ← [20]
    treeIndex ← 0
    marker ← gameBoard.markerStart
    tree.set(0, marker)

    return solve(treeIndex, marker)
    
Algorithm solve(treeIndex, marker)
    Input:
        treeIndex: The current index pointing to where we are in the tree
        marker: The current marker on the game board
    Output:
        Boolean if the board is solved
        
    markerRight ← gameBoard.getMarkerAfterMove(RIGHT, marker)
    IF (gameBoard.isGameSolved(markerRight) THEN
        return true
    markerLeft ← gameBoard.getMarkerAfterMove(LEFT, marker)
        IF (gameBoard.isGameSolved(markerLeft) THEN
            return true
            
    insertRight(treeIndex, markerRight)
    insertLeft(treeIndex, markerLeft)
    
    rightMarkerValue ← gameBoard.getMarkerValue(markerRight)
    IF rightMarkerValue AND isMarkerNavigated(treeIndex, markerRight) = false THEN
        IF solve(indexAtRight(treeIndex), markerRight)) THEN
            return true
            
    leftMarkerValue ← gameBoard.getMarkerValue(markerLeft)
        IF leftMarkerValue AND isMarkerNavigated(treeIndex, markerLeft) = false THEN
            IF solve(indexAtRight(treeIndex), markerLeft)) THEN
                return true
                
    return false
    
Algorithm isMarkerNavigated(parentNode, marker)
    Input:
        parentNode: The parent node in the tree
        marker: The current marker value
    Output: Boolean if this marker has already been navigated to
    
    parentNodeMarker ← valueAt(parentNode)
    IF parentNodeMarker = marker THEN
        return true
    IF parentNode = 0
        return false
    parentNode ← (parentNode - 1) / 2
    return isMarkerNavigated(parentNode, marker)
```