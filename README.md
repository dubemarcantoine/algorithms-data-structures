# Right Magnetic Cave Algorithm
## a) Briefly explain the time and space complexity for both versions of your game. You can write your answer in a separate file and submit it together with the other submissions.
### Recursion strategy
The recursion strategy acts as a virtual binary tree without having a tree structure. 
At every given move, it gets the value to it's left and to it's right. It then checks
if it is possible to go right as it tries to get the farthest possible to the right.
If it gets stuck in a cycle, the recursion tries to go left and unwinds on all previous values
until it could go back right.
It keeps track of the previous visited markers with a `Set<Integer>` which takes O(1) to lookup into.

It's worst case scenario is O(n²). Because it always tries to go right, if the marker starts at
the last index of the board before 0 and that the index of the board that get's to 0 is the first
index of the board, the algorithm will try to go right, be blocked, then go left, then try to go right,
so for every given marker, it could try all right values before finally trying the left ones.

The base case scenario is O(n) (or less). For example, if we start at 0 and all the steps are 1 up to the last element,
n operations will be performed

See b) for space complexity

### List strategy
The strategy of the list was to implement a binary tree with the list.

The time complexity is O(nlogn). The operations performed are n which is the side of the board and logn is the time
it could take to bubble up from a leaf to the root node. In this case, `n=h` where h is the height of the binary
tree.

The space complexity is more problematic as it is exponential depending on the height of the tree which could be n.
So the big-O space is O(2^n).

## b) For the first version of your solution describe the type of recursion used in your implementation. Does the particular type of recursion have an impact on the time and space complexity? Justify your answer.
The recursion used is binary recursion. This recursion has an impact on the space complexity
as a stack overflow could potentially occur if the recursion is too deep. The space complexity is then O(n²).

## c) For the second part of your solution, justify why you choose that particular data structure (e.g. why you choose a stack and not a queue, etc.)
I decided to use a list as it provided a good base to implement a binary tree.
Due to the nature of the problem, IE deciding on going right or left, the representation of a binary tree felt like a 
good way to tackle this problem. However, the biggest issue has been the space complexity as we need large amounts of
RAM/Heap Space to solve larger boards.

## d) Provide test logs for at least 20 different game configurations, sufficiently complete to show that your solution works for various row sizes and square values.
See logs/*.txt

## e) If possible, explain how one can detect unsolvable array configurations and whether there exists a way to speed up the execution time. Answering this question is optional and you can earn bonus marks by submitting a good solution.
We should ensure that the board is solvable by checking if any of the values can
reach the last element of the array and that this element equals 0.

This check would be done in O(n) time and can potentially save computing time.

```
Algorithm isSolvable(board)
    Input: An array of integers
    Output: Boolean if the board can be solved
    
    FOR i ← 0 to board.size - 2
        marker ← getMarkerAfterMove(RIGHT, i)
        IF isGameSolved(marker) THEN
            return true
    END FOR

```

Another quick check could be to check if the marker is already on the last element
and that element is of 0 value. This would be done in O(1)

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