package com.dubemarcantoine.comp352.smartar;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

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
        AtomicBoolean overwrite = new AtomicBoolean(false);
        AtomicBoolean valueInserted = new AtomicBoolean(false);
        // Search for the list with the subkey
        this.values.forEach(keyDataList -> {
            if (keyDataList.getKey().equals(subKey)) {
                // Search for the list with the key
                keyDataList.getValue().forEach(dataList -> {
                    if (dataList.getKey().equals(data.getKey())) {
                        overwrite.set(this.setLastAsDeleted(dataList.getValue()));
                        dataList.getValue().add(data);
                        valueInserted.set(true);
                    }
                });
                // The key does not exist in the subkey list, create the list
                List<Data<K, V>> list = new ArrayList<>();
                list.add(data);
                keyDataList.getValue().add(new Data<>(data.getKey(), list));
            }
        });
        Optional<Data<K, List<Data<K, List<Data<K, V>>>>>> subList = this.values.stream()
                .filter(keyDataList -> keyDataList.getKey().equals(subKey))
                .findFirst();
        subList.isPresent();
        return overwrite.get();
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

    /**
     * Marks the last value inserted in the value array as deleted
     * @param values
     * @return
     */
    private boolean setLastAsDeleted(List<Data<K, V>> values) {
        boolean deleted = false;
        if (values.size() > 0) {
            Data<K, V> lastInserted = values.get(values.size() - 1);
            if (!lastInserted.isDeleted()) {
                lastInserted.delete();
                deleted = true;
            }
        }
        return deleted;
    }
}
