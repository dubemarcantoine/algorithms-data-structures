package com.dubemarcantoine.comp352.smartar;

import java.util.List;

public class SequenceSmartAR<K, T> implements SmartARInternal<K, T> {

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
