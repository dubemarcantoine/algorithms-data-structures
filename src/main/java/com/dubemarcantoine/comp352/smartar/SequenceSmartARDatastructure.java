package com.dubemarcantoine.comp352.smartar;

import java.util.ArrayList;
import java.util.List;

public class SequenceSmartARDatastructure<K, V> implements SmartARInternalDatastructure<K, V> {

    private List<Data<K, List<Data<K, List<Data<K, V>>>>>> values;

    public SequenceSmartARDatastructure() {
        this.values = new ArrayList<>();
    }

    @Override
    public List<K> allKeys() {
        return null;
    }

    @Override
    public boolean add(K subKey, Data<K, V> data) {
        boolean overwrite = false;
        this.values.forEach(dataList -> {
            if (dataList.getKey().equals(subKey)) {

            }
        });
        return overwrite;
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
