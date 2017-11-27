package com.dubemarcantoine.comp352.smartar.datastructure;

import com.dubemarcantoine.comp352.smartar.datastructure.Data;
import com.dubemarcantoine.comp352.smartar.datastructure.SmartARInternalDatastructure;
import com.dubemarcantoine.comp352.smartar.datastructure.implementation.SequenceSmartARDatastructure;
import com.dubemarcantoine.comp352.smartar.datastructure.implementation.TreeMapSmartARDatastructure;

import java.util.List;

/**
 * Abstract SmartAR
 * @param <K extends Comparable>
 * @param <V>
 */
public abstract class AbstractSmartAR<K extends Comparable, V>  {

    protected final int MIN_THRESHOLD = 100;
    protected final int MAX_THRESHOLD = 500000;
    protected final int DEFAULT_THRESHOLD = 100;

    protected int threshold;

    /**
     * The total number of items not marked as soft deleted
     */
    protected int activeSize;

    /**
     * The total number of items in the structure
     */
    protected int totalSize;

    protected SmartARInternalDatastructure<K, V> internalDatastructure;

    public AbstractSmartAR() {
        this.setThreshold(DEFAULT_THRESHOLD);
        this.internalDatastructure = new SequenceSmartARDatastructure<>();
        this.activeSize = 0;
        this.totalSize = 0;
    }

    public AbstractSmartAR(int threshold) {
        this.setThreshold(threshold);
        this.internalDatastructure = new SequenceSmartARDatastructure<>();
        this.activeSize = 0;
        this.totalSize = 0;
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

    /**
     * Adds the value with it's associated key
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        boolean overwrite = this.internalDatastructure.add(this.getSubkey(key), new Data<>(key, value));
        if (!overwrite) {
            this.activeSize++;
        }
        this.totalSize++;
        this.changeStructureIfPassedThreshold();
    }

    /**
     * Soft deletes a key
     * @param key
     * @return
     */
    public boolean remove(K key) {
        boolean removed = this.internalDatastructure.remove(this.getSubkey(key), key);
        if (removed) {
            this.activeSize--;
        }
        return removed;
    }

    /**
     * Returns the values for a key
     * @param key
     * @return
     */
    public List<V> getValues(K key) {
        return this.internalDatastructure.getValues(this.getSubkey(key), key);
    }

    /**
     * Returns the previous key for a key
     * @param key
     * @return
     */
    public K prevKey(K key) {
        return this.internalDatastructure.prevKey(this.getSubkey(key), key);
    }

    /**
     * Returns the next key for a key
     * @param key
     * @return
     */
    public K nextKey(K key) {
        return this.internalDatastructure.nextKey(this.getSubkey(key), key);
    }

    /**
     * Returns the previous values for a key
     * @param key
     * @return
     */
    public List<V> previousValues(K key) {
        return this.internalDatastructure.previousValues(this.getSubkey(key), key);
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
                K subKey = this.getSubkey(key);
                List<V> values = this.internalDatastructure.getValues(subKey, key);
                values.forEach(value -> newInternalDatastructure.add(subKey, new Data<>(key, value)));
            });
            this.internalDatastructure = newInternalDatastructure;
        }
    }

    /**
     * Based on a custom implementation, gets the subkey of a key (or simply returns the full key)
     * @param key
     * @return
     */
    protected abstract K getSubkey(K key);
}
