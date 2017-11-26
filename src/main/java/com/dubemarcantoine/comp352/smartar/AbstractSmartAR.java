package com.dubemarcantoine.comp352.smartar;

import java.util.List;

public abstract class AbstractSmartAR<K extends Comparable, V>  {

    protected final int MIN_THRESHOLD = 100;
    protected final int MAX_THRESHOLD = 500000;

    protected final int MIN_KEY_LENGTH = 6;
    protected final int MAX_KEY_LENGTH = 12;

    protected int threshold;

    protected int maxKeyLength;

    protected int activeSize;

    protected int totalSize;

    protected SmartARInternalDatastructure<K, V> internalDatastructure;

    public AbstractSmartAR(int threshold, int keyLength) {
        this.setThreshold(threshold);
        this.setKeyLength(keyLength);
        this.internalDatastructure = new SequenceSmartARDatastructure<>();
    }

    /**
     * Defines when a listing should be implemented with a data structure such as a Tree,
     * Hash Table, AVL tree, binary tree, if its size is greater than or equal to value of
     * Threshold. Otherwise it is implemented as a Sequence.
     *
     * @param threshold
     */
    public void setThreshold(int threshold) {
        if (this.totalSize != 0) {
            throw new RuntimeException("Can't set threshold when data structure is not empty");
        }
        if (threshold < MIN_THRESHOLD || threshold > MAX_THRESHOLD) {
            throw new RuntimeException("Threshold has to be between " + MIN_THRESHOLD + " and " + MAX_THRESHOLD);
        }
        this.threshold = threshold;
    }

    /**
     * Number that defines the fixed string length of keys.
     * @param keyLength
     */
    public void setKeyLength(int keyLength) {
        if (this.totalSize != 0) {
            throw new RuntimeException("Can't set key length when data structure is not empty");
        }
        if (keyLength < MIN_KEY_LENGTH || keyLength > MAX_KEY_LENGTH) {
            throw new RuntimeException("Key length has to be between " + MIN_KEY_LENGTH + " and " + MAX_KEY_LENGTH);
        }
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
        return this.internalDatastructure.allKeys();
    }

    public void add(K key, V value) {
        this.internalDatastructure.add(this.getSubkey(key), new Data<>(key, value));
        this.totalSize++;
    }

    public boolean remove(K key) {
        return this.internalDatastructure.remove(this.getSubkey(key), key);
    }

    public List<V> getValues(K key) {
        return this.internalDatastructure.getValues(this.getSubkey(key), key);
    }

    public K prevKey(K key) {
        return this.internalDatastructure.prevKey(this.getSubkey(key), key);
    }

    public K nextKey(K key) {
        return this.internalDatastructure.nextKey(this.getSubkey(key), key);
    }

    public List<V> previousValues(K key) {
        return this.internalDatastructure.previousValues(this.getSubkey(key), key);
    }

    private String truncateKey(String key) {
        if (key.length() > this.maxKeyLength) {
            return key.substring(0, this.maxKeyLength);
        }
        return key;
    }

    /**
     * Changes the internal datastructure if the total number of items is
     * passed the set threshold
     */
    protected void changeStructureIfPassedThreshold() {
        if (this.totalSize > this.threshold) {
            SmartARInternalDatastructure<K, V> newInternalDatastructure = new TreeMapSmartARDatastructure<>();
            List<K> keys = this.internalDatastructure.allKeys();
            keys.forEach(key -> {
                List<V> values = this.internalDatastructure.getValues(key, key);
                values.forEach(value -> newInternalDatastructure.add(this.getSubkey(key), new Data<>(key, value)));
            });
            this.internalDatastructure = newInternalDatastructure;
        }
    }

    abstract K getSubkey(K key);
}
