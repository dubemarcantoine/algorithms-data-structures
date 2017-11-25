package com.dubemarcantoine.comp352.smartar;

import java.util.List;

public interface SmartARInternal<K, V> {

    /**
     * Add an entry for the given key and value
     * @param key
     * @param value
     */
    void add(K key, V value);

    /**
     * Remove the entry for the given key
     * @param key
     */
    void remove(K key);

    /**
     * Return the values of the given key
     * @param key
     * @return
     */
    List<V> getValues(K key);

    /**
     * Return the key for the predecessor of key
     * @param key
     * @return
     */
    K prevKey(K key);

    /**
     * Return the key for the successor of key
     * @param key
     * @return
     */
    K nextKey(K key);

    /**
     * Returns a sequence (sorted in reverse chronological order) of values
     * @param key
     * @return
     */
    List<V> previousValues(K key);
}
