package com.dubemarcantoine.comp352.smartar;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

public class TreeMapSmartAR<K, T> implements SmartARInternal<K, T> {

    private TreeMap<K, LinkedHashMap<K, T>> values;

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
