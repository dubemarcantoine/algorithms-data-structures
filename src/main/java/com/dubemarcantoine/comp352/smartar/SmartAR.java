package com.dubemarcantoine.comp352.smartar;

import java.util.List;

public class SmartAR<K, V> implements SmartARInternal<K, V>  {

    private int threshold;

    private int maxKeyLength;

    private SmartARInternal<K, V> currentAlgo;

    public SmartAR() {
    }

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
        this.maxKeyLength = keyLength;
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
    public void add(K key, V value) {
        key = (K)this.truncateKey((String) key);
    }

    @Override
    public void remove(K key) {

    }

    @Override
    public List<V> getValues(K key) {
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
    public List<V> previousValues(K key) {
        return null;
    }

    private String truncateKey(String key) {
        if (key.length() > this.maxKeyLength) {
            return key.substring(0, this.maxKeyLength);
        }
        return key;
    }
}
