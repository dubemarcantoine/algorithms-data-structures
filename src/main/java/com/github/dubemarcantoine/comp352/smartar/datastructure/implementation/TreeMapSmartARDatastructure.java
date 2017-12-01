package com.github.dubemarcantoine.comp352.smartar.datastructure.implementation;

import com.github.dubemarcantoine.comp352.smartar.datastructure.Data;
import com.github.dubemarcantoine.comp352.smartar.datastructure.SmartARInternalDatastructure;

import java.util.*;
import java.util.stream.Collectors;

public class TreeMapSmartARDatastructure<K, V> implements SmartARInternalDatastructure<K, V> {

    private TreeMap<K, TreeMap<K, List<Data<K, V>>>> treeMap;

    public TreeMapSmartARDatastructure() {
        this.treeMap = new TreeMap<>();
    }

    @Override
    public List<K> allKeys() {
        List<K> keys = new ArrayList<>();
        this.treeMap.forEach((subKey, subMap) -> keys.addAll(subMap.keySet()));
        return keys;
    }

    @Override
    public boolean add(K subKey, Data<K, V> data) {
        TreeMap<K, List<Data<K, V>>> subTreeMap = this.treeMap.get(subKey);
        // Subkey has never been used
        if (subTreeMap == null) {
            subTreeMap = new TreeMap<>();
            List<Data<K, V>> values = new ArrayList<>();
            values.add(data);
            subTreeMap.put(data.getKey(), values);
            this.treeMap.put(subKey, subTreeMap);
            return false;
        }
        List<Data<K, V>> values = subTreeMap.get(data.getKey());
        // Full key has never been used
        if (values == null) {
            values = new ArrayList<>();
            values.add(data);
            subTreeMap.put(data.getKey(), values);
            return false;
        }
        // Set the last value in the list as deleted
        boolean overwrites = this.setLastAsDeleted(values);
        // Insert the data in the list
        values.add(data);

        return overwrites;
    }

    @Override
    public boolean remove(K subKey, K fullKey) {
        TreeMap<K, List<Data<K, V>>> subTreeMap = this.treeMap.get(subKey);
        if (subTreeMap == null) {
            return false;
        }
        List<Data<K, V>> values = subTreeMap.get(fullKey);
        if (values == null) {
            return false;
        }
        return this.setLastAsDeleted(values);
    }

    @Override
    public List<V> getValues(K subKey, K fullKey) {
        return this.getValues(subKey, fullKey, false);
    }

    @Override
    public K prevKey(K subKey, K fullKey) {
        TreeMap<K, List<Data<K, V>>> subTreeMap = this.treeMap.get(subKey);
        if (subTreeMap == null) {
            return null;
        }
        // Get the previous key in the sub tree map
        K previous = subTreeMap.lowerKey(fullKey);
        if (previous != null) {
            return previous;
        }
        K previousSubTreeMapKey = this.treeMap.lowerKey(subKey);
        if (previousSubTreeMapKey == null) {
            return null;
        }
        TreeMap<K, List<Data<K, V>>> previousSubTree = this.treeMap.get(previousSubTreeMapKey);
        return previousSubTree.lastKey();
    }

    @Override
    public K nextKey(K subKey, K fullKey) {
        TreeMap<K, List<Data<K, V>>> subTreeMap = this.treeMap.get(subKey);
        if (subTreeMap == null) {
            return null;
        }
        // Get the previous key in the sub tree map
        K next = subTreeMap.higherKey(fullKey);
        if (next != null) {
            return next;
        }
        K nextSubTreeMapKey = this.treeMap.higherKey(subKey);
        if (nextSubTreeMapKey == null) {
            return null;
        }
        TreeMap<K, List<Data<K, V>>> nextSubTree = this.treeMap.get(nextSubTreeMapKey);
        return nextSubTree.firstKey();
    }

    @Override
    public List<V> previousValues(K subKey, K fullKey) {
        List<V> values = this.getValues(subKey, fullKey, true);
        if (values != null) {
            Collections.reverse(values);
        }
        return values;
    }

    @Override
    public boolean contains(K subKey, K fullKey) {
        TreeMap<K, List<Data<K, V>>> subTreeMap = this.treeMap.get(subKey);
        if (subTreeMap == null) {
            return false;
        }
        List<Data<K, V>> values = subTreeMap.get(fullKey);
        return values != null;
    }

    /**
     * Marks the last value inserted in the value array as deleted
     * @param values
     * @return
     */
    private boolean setLastAsDeleted(List<Data<K, V>> values) {
        boolean deleted = false;
        if (values.size() > 0) {
            Data<K, V> lastInserted = values.get(values.size() - 1);
            if (!lastInserted.isDeleted()) {
                lastInserted.delete();
                deleted = true;
            }
        }
        return deleted;
    }

    /**
     * Returns the values for a key
     * @param subKey
     * @param fullKey
     * @param getOnlyDeleted
     * @return
     */
    private List<V> getValues(K subKey, K fullKey, boolean getOnlyDeleted) {
        TreeMap<K, List<Data<K, V>>> subTreeMap = this.treeMap.get(subKey);
        if (subTreeMap == null) {
            return null;
        }
        List<Data<K, V>> keyValues = subTreeMap.get(fullKey);
        if (keyValues == null) {
            return null;
        }
        return keyValues.stream()
                .filter(data -> !getOnlyDeleted || data.isDeleted())
                .map(data -> data.getValue())
                .collect(Collectors.toList());
    }
}
