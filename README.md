# comp-352-ass01 Tetranacci numbers
## a) Analysis of execution time of the different algorithms
From the results in the results folder, the linear implementation is exponentially 
faster than the binary implementation. This is because the binary executes in O(4^n) time vs 
the linear which executes in O(n) time. The second iteration of both functions consistently show a difference in speed
of about 4 to 7 times, the linear being the faster one.

Here is a comparision in nanoseconds of `Tetranacci(10)`

| Executions | Binary | Linear | Ratio L/B |
|------------|--------|--------|-----------|
| 1          | 84010  | 13402  | 0.16      |
| 2          | 117158 | 23006  | 0.2       |
| 3          | 81651  | 14153  | 0.17      |

The difference is not ***that*** big considering the execution times of each algorithm.
However, if we compare the execution time of `Tetranacci(30)`, we quickly see the exponential difference of the 
two implementations.

Given these observations, we would not be able to see the end of the Universe if we try to compute much bigger numbers.

### Pseudocode Binary
```pseudo
Algorithm Binary(n)
    Input: The number of Tetranacci to compute
    Output: The computed number of Tetranacci
    
    if n <= 2 then
        return 0
    else if n <= 4
        return 1
    
    return Binary(n - 1) + Binary(n - 2) + Binary(n - 3) + Binary(n - 4)
```
### Pseudocode Linear
```pseudo
Algorithm Linear(n)
    Input: The number of Tetranacci to compute
    Output: The computed number of Tetranacci
    
    return LinearRec(n)[3]

Algorithm LinearRec(n)
    Input: The number of Tetranacci to compute
    Output: Array containing previously computed values of Tetranacci from smallest to biggest
    
    if n <= 2 then
        return [0, 0, 0, 0]
    else if n <= 4
        return [0, 0, 1, 1]
    
    A = LinearRec(n - 1)
    newMax = A[0] + A[1] + A[2] + A[3]
    return [A[1], A[2], A[3], newMax]
```

## b) Briefly explain why the first algorithm is of exponential complexity and the second one is linear (more specifically, how the second algorithm resolves some specific bottleneck(s) of the first algorithm).
The difference between the 2 algorithms is that the binary one does not re-use the previous results to compute the next
result. Instead, it adds up 4 Tetranacci numbers and calculates them recursively.

To solve this bottleneck, the linear algorithm keeps a list of the 4 previous Tetranacci numbers to add them
together to get the next number. This means that it's done only once for each number.

## c) Do the previous algorithms use tail recursion?
Both the linear and binary versions of Tetranacci previously designed and implemented do **not** use tail recursion.
This is because the last call in each of the functions is not the function itself which means that the
stackframes will keep piling up for each of the recursions potentially causing a stack overflow.

However, a linear and tail recursive implementation can be done. Below is the pseudocode and it's implementation
can be found in `com.github.dubemarcantoine.comp352.ass01TetranacciTailRecursive`.

```pseudo
Algorithm TailRecursive(n)
    Input: The number of Tetranacci to compute
    Output: The computed number of Tetranacci
    
    return TailRecursive(0, 0, 0, 1, n)
    
Algorithm TailRecursive(a, b, c, d, n)
    Input:
        a, b, c, d The previously computed Tetranacci numbers from smallest (a) to biggest (d)
        n The number of Tetranacci to compute
    Output: The computed number of Tetranacci
    
    if n <= 3 then
        return d
    
    newMax ← a + b + c + d
    return TailRecursive(b, c, d, newMax, n - 1)
```

## Project
### Gradle / Gradlew
Execute the Gradle wrapper to have a local project version
- Windows: `gradlew`
- Linux/MacOS: `./gradlew`

### Run
- Windows: `gradlew run`
- Linux/MacOS: `./gradlew run`

### Build
- Windows: `gradlew build`
- Linux/MacOS: `./gradlew build`

### Jar
- Windows: `gradlew fatJar`
- Linux/MacOS: `./gradlew fatJar`

The jar is generated at `./build/libs/ass01-1.0.jar`