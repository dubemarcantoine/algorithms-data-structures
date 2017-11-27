# COMP 352 Assignment 04
## Smart AR
Smart AR is a datastructure that changes it's internal datastructures depending on how much data it contains.
2 internal datastructures were created:

### SequenceSmartARDatastructure
This datastructure is using a combination of ArrayLists containing keys and sublists of the whole key and it's value.
It is the slowest of the 2 implementations, but uses less memory.

#### Pseudocode
##### allKeys()
```
Algorithm allKeys()
    Output: The full keys sorted in alphabetical order
    keys <- []
    FOR subKeyList IN values
        FOR key IN subKeyList
            keys.add(key)
            
    return keys
```
##### add(subKey, key, data)
```
Algorithm add(subKey, key, data)
    Input:
        subKey the subkey that was from the key
        key the full key
        data the data to be inserted
    Output: boolean if the key is new or not
    
    subList <- getSubKeyList(subKey)
    IF !subList THEN
        dataList <- [data]
        sameKeyData <- Data(fullKey, dataList)
        sameKeyList <- [sameKeyData]
        sameSubKeyData <- Data(subKey, sameKeyList)
        values.add(sameSubKeyData)
        return false
    subDataList <- getKeyList(key, subList)
    IF !subDataList THEN
        dataList <- [data]
        sameKeyData <- Data(fullKey, dataList)
        subList.add(sameKeyData)
        return false
    overwrite = setLastAsDeleted(subDataList)
    subDataList.add(data)
    return overwrite
```

##### remove(subKey, fullKey)
```
Algorithm remove(subKey, fullKey)
    Input:
        subKey the subkey that was from the key
        key the full key
    Output:
        boolean if the data was removed or not
    
    subList <- getSubKeyList(subKey)
    IF !subList THEN
        return false
    subDataList <- getKeyList(key, subList)
    IF !subDataList THEN
        return false
    return setLastAsDeleted(subDataList)
```

##### getValues(subKey, fullKey)
```
Algorithm getValues(subKey, fullKey)
    Input:
        subKey the subkey that was from the key
        key the full key
    Output:
        The values
    
    return getValues(subKey, fullKey, false)
```

##### prevKey(subKey, fullKey)
```
Algorithm prevKey(subKey, fullKey)
    Input:
        subKey the subkey that was from the key
        key the full key
    Output:
        The previous key
    
    keys <- allKeys()
    keyIndex <- keys.indexOf(fullKey)
    IF keyIndex > 0 THEN
        return keys[keyIndex -1]
    return null
```

##### nextKey(subKey, fullKey)
```
Algorithm nextKey(subKey, fullKey)
    Input:
        subKey the subkey that was from the key
        key the full key
    Output:
        The next key
    
    keys <- allKeys()
    keyIndex <- keys.indexOf(fullKey)
    IF keyIndex < keys.length - 1 THEN
        return keys[keyIndex + 1]
    return null
```

##### previousValues(subKey, fullKey)
```
Algorithm previousValues(subKey, fullKey)
    Input:
        subKey the subkey that was from the key
        key the full key
    Output:
        The values
    
    values <- getValues(subKey, fullKey, true)
    reverse(values) // From order of insertion to counter order of insertion
    return values
```

##### getValues(subKey, fullKey, getOnlyDeleted)
```
Algorithm getValues(subKey, fullKey, getOnlyDeleted)
    Input:
        subKey the subkey that was from the key
        key the full key
        getOnlyDeleted boolean to only get deleted values
    Output:
        The values
    
    subList <- getSubKeyList(subKey)
    IF !subList THEN
        return null
    subDataList <- getKeyList(key, subList)
    IF !subDataList THEN
        return null
    values <- []
    FOR data IN subDataList
        IF !getOnlyDeleted AND data.isDeleted THEN
            values.add(data)
    return values
```

### TreeMapSmartARDatastructure
This datastructure uses a combination of 2 TreeMaps to hold the truncated and the full key and an ArrayList to hold
the data in order of insertion.

#### Pseudocode
##### allKeys()
```
Algorithm allKeys()
    Output: The full keys sorted in alphabetical order
    keys <- []
    FOR subKeyList IN treeMap
        FOR key IN subKeyList
            keys.add(key)
            
    return keys
```
##### add(subKey, key, data)
```
Algorithm add(subKey, key, data)
    Input:
        subKey the subkey that was from the key
        key the full key
        data the data to be inserted
    Output: boolean if the key is new or not
    
    subTreeMap <- treeMap.get(subKey)
    IF !subTreeMap THEN
        subTreeMap <- [:]
        values <- [data]
        subTreeMap.put(key, values)
        treeMap.put(subKey, subTreeMap)
        return false        
    values <- subTreeMap.get(key)
    IF !values THEN
        subTreeMap <- [:]
        values <- [data]
        subTreeMap.put(key, values)
        return false
    overwrite = setLastAsDeleted(values)
    values.add(data)
    return overwrite
```

##### remove(subKey, fullKey)
```
Algorithm remove(subKey, fullKey)
    Input:
        subKey the subkey that was from the key
        key the full key
    Output:
        boolean if the data was removed or not
    
    subTreeMap <- treeMap.get(subKey)
    IF !subTreeMap THEN
        return false
    values <- subTreeMap.get(key)
    IF !values THEN
        return false
    return setLastAsDeleted(values)
```

##### getValues(subKey, fullKey)
```
Algorithm getValues(subKey, fullKey)
    Input:
        subKey the subkey that was from the key
        key the full key
    Output:
        The values
    
    return getValues(subKey, fullKey, false)
```

##### prevKey(subKey, fullKey)
```
Algorithm prevKey(subKey, fullKey)
    Input:
        subKey the subkey that was from the key
        key the full key
    Output:
        The previous key
    
    subTreeMap <- treeMap.get(subKey)
    IF !subTreeMap THEN
        return null
    previous <- subTreeMap.lowerKey(fullKey)
    IF previous THEN
        return previous
    previousSubTreeMapKey <- treeMap.lowerKey(subKey)
    IF !previousSubTreeMapKey THEN
        return null
    previousSubTree <- treeMap.get(previousSubTreeMapKey)
    return previousSubTree.lastKey()
```

##### nextKey(subKey, fullKey)
```
Algorithm nextKey(subKey, fullKey)
    Input:
        subKey the subkey that was from the key
        key the full key
    Output:
        The next key
    
    subTreeMap <- treeMap.get(subKey)
    IF !subTreeMap THEN
        return null
    next <- subTreeMap.higherKey(fullKey)
    IF previous THEN
        return previous
    nextSubTreeMapKey <- treeMap.higherKey(subKey)
    IF !nextSubTreeMapKey THEN
        return null
    nextSubTree <- treeMap.get(nextSubTreeMapKey)
    return nextSubTree.firstKey()
```

##### previousValues(subKey, fullKey)
```
Algorithm previousValues(subKey, fullKey)
    Input:
        subKey the subkey that was from the key
        key the full key
    Output:
        The values
        
    values <- getValues(subKey, fullKey, true)
    IF !values THEN
        return null
    reverse(values)
    return values
```

##### getValues(subKey, fullKey, getOnlyDeleted)
```
Algorithm getValues(subKey, fullKey, getOnlyDeleted)
    Input:
        subKey the subkey that was from the key
        key the full key
        getOnlyDeleted boolean to only get deleted values
    Output:
        The values
    
    subTreeMap <- treeMap.get(subKey)
    IF !subTreeMap THEN
        return null
    values <- subTreeMap.get(key)
    IF !values THEN
        return null
    FOR data IN values
    IF !getOnlyDeleted AND data.isDeleted THEN
        values.add(data)
    return values
```


### Discuss how both the time and space complexity change for each of the methods above if the underlying structure of your SmartAR is an array or a linked list?
In the case of `SequenceSmartARDatastructure` which uses a sequence, the space complexity would not change as it would still be O(n).

The time complexity would change for the insert. Before insertion, we have to find the key which takes O(n) time. The insertion in an array is O(1)
and O(n) in a linkedlist. An insert thus takes O(n) with the array, but would take O(n^2) with the LinkedList.

For remove, nothing would change as the remove function of the underlying datastructures is not used. SmartAR uses soft deletes which just toggles a boolean in O(1).

### Run with data files output
```
Parsing file: ar_test_file0.txt
Finished parsing file in 23ms
Finished adding 1000 data in 78ms
Parsing file: ar_test_file1.txt
Finished parsing file in 5ms
Finished adding 10000 data in 21ms
Parsing file: ar_test_file2.txt
Finished parsing file in 21ms
Finished adding 100000 data in 253ms
Parsing file: ar_test_file3.txt
Finished parsing file in 210ms
Finished adding 1000000 data in 1772ms
Parsing file: ar_test_file4.txt
Finished parsing file in 1028ms
Finished adding 10000001 data in 14334ms
```

### Test Cases
Test cases are located in `SmartARTest` class.