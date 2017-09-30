# comp-352-ass01 Tetranacci numbers
## a)
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

## b)

## c)
Both the linear and binary versions of Tetranacci previously designed and implemented do **not** use tail recursion.
This is because the last call in each of the functions is not the function itself which means that the
stackframes will keep piling up for each of the recursions potentially causing a stackoverflow.

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