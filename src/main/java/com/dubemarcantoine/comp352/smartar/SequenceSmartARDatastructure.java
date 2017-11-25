package com.dubemarcantoine.comp352.smartar;

import java.util.List;

public class SequenceSmartARDatastructure<K, V> implements SmartARInternalDatastructure<K, V> {


    @Override
    public boolean add(K key, Data<K, V> data) {
        return false;
    }

    @Override
    public boolean remove(K subKey, K fullKey) {
        return true;
    }

    @Override
    public List<V> getValues(K subKey, K fullKey) {
        return null;
    }

    @Override
    public K prevKey(K subKey, K fullKey) {
        return null;
    }

    @Override
    public K nextKey(K subKey, K fullKey) {
        return null;
    }

    @Override
    public List<V> previousValues(K subKey, K fullKey) {
        return null;
    }
}
