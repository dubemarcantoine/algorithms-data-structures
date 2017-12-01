package com.github.dubemarcantoine.comp352.smartar.datastructure;

import java.util.List;

public interface SmartARInternalDatastructure<K, V> {

    List<K> allKeys();

    /**
     * Add an entry for the given key and value
     * @param key
     * @param data
     */
    boolean add(K key, Data<K, V> data);

    /**
     * Remove the entry for the given key
     * @param subKey
     * @param fullKey
     */
    boolean remove(K subKey, K fullKey);

    /**
     * Return the values of the given key
     * @param subKey
     * @param fullKey
     * @return
     */
    List<V> getValues(K subKey, K fullKey);

    /**
     * Return the key for the predecessor of key
     * @param subKey
     * @param fullKey
     * @return
     */
    K prevKey(K subKey, K fullKey);

    /**
     * Return the key for the successor of key
     * @param subKey
     * @param fullKey
     * @return
     */
    K nextKey(K subKey, K fullKey);

    /**
     * Returns a sequence (sorted in reverse chronological order) of values
     * @param subKey
     * @param fullKey
     * @return
     */
    List<V> previousValues(K subKey, K fullKey);

    /**
     * Checks if a key exists
     * @param subKey
     * @param fullKey
     * @return
     */
    boolean contains(K subKey, K fullKey);
}
