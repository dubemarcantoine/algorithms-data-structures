package com.dubemarcantoine.comp352.smartar;

import java.util.List;

public class SmartAR<K, T> implements SmartARInternal<K, T>  {

    private int threshold;

    private int keyLength;

    /**
     * Defines when a listing should be implemented with a data structure such as a Tree,
     * Hash Table, AVL tree, binary tree, if its size is greater than or equal to value of
     * Threshold. Otherwise it is implemented as a Sequence.
     *
     * @param threshold
     */
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    /**
     * Number that defines the fixed string length of keys.
     * @param keyLength
     */
    public void setKeyLength(int keyLength) {
        this.keyLength = keyLength;
    }

    /**
     * Randomly generates a sequence containing n new non-existing keys of alphanumeric characters
     * @param size
     */
    public void generate(int size) {

    }

    /**
     * Return all keys as a sorted sequence (lexicographic order)
     * @return
     */
    public List<K> allKeys() {
        return null;
    }

    @Override
    public void add(K key, T value) {

    }

    @Override
    public void remove(K key) {

    }

    @Override
    public List<T> getValues(K key) {
        return null;
    }

    @Override
    public K prevKey(K key) {
        return null;
    }

    @Override
    public K nextKey(K key) {
        return null;
    }

    @Override
    public List<T> previousValues(K key) {
        return null;
    }
}
