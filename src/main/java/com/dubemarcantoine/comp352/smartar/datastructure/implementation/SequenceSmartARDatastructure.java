package com.dubemarcantoine.comp352.smartar.datastructure.implementation;

import com.dubemarcantoine.comp352.smartar.datastructure.Data;
import com.dubemarcantoine.comp352.smartar.datastructure.SmartARInternalDatastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SequenceSmartARDatastructure<K extends Comparable, V> implements SmartARInternalDatastructure<K, V> {

    private List<Data<K, List<Data<K, List<Data<K, V>>>>>> values;

    public SequenceSmartARDatastructure() {
        this.values = new ArrayList<>();
    }

    @Override
    public List<K> allKeys() {
        // Parallel streams used as the order does not matter since the structure is unordered
        List<K> keys = this.values
                .parallelStream()
                .map(subKeyList -> subKeyList.getValue()
                        .parallelStream()
                        .map(keyList -> keyList.getKey())
                        .collect(Collectors.toList()))
                .flatMap(list -> list.parallelStream())
                .collect(Collectors.toList());
        // Sort the list by keys
        Collections.sort(keys);
        return keys;
    }

    @Override
    public boolean add(K subKey, Data<K, V> data) {
        // Check if the subkey exists
        Optional<Data<K, List<Data<K, List<Data<K, V>>>>>> subList = this.getSubKeyList(subKey);
        // If not, create the full structure
        if (!subList.isPresent()) {
            List<Data<K, V>> list = new ArrayList<>();
            list.add(data);
            List<Data<K, List<Data<K, V>>>> sameKeyList = new ArrayList<>();
            Data<K, List<Data<K, V>>> sameKeyData = new Data<>(data.getKey(), list);
            sameKeyList.add(sameKeyData);
            Data<K, List<Data<K, List<Data<K, V>>>>> sameSubKeyData = new Data<>(subKey, sameKeyList);
            this.values.add(sameSubKeyData);
            return false;
        }
        // Check if the full key exists
        Optional<Data<K, List<Data<K, V>>>> subDataList = this.getKeyList(data.getKey(), subList.get().getValue());
        // If not exists, create the sub structure
        if (!subDataList.isPresent()) {
            List<Data<K, V>> list = new ArrayList<>();
            list.add(data);
            Data<K, List<Data<K, V>>> sameKeyData = new Data<>(data.getKey(), list);
            subList.get().getValue().add(sameKeyData);
            return false;
        }
        // All lists exist, simply insert the data
        // Set the last element of the list as deleted before the insertion
        boolean overwrite = this.setLastAsDeleted(subDataList.get().getValue());
        subDataList.get().getValue().add(data);
        return overwrite;
    }

    @Override
    public boolean remove(K subKey, K fullKey) {
        Optional<Data<K, List<Data<K, List<Data<K, V>>>>>> subList = this.getSubKeyList(subKey);
        if (!subList.isPresent()) {
            return false;
        }

        Optional<Data<K, List<Data<K, V>>>> subDataList = this.getKeyList(fullKey, subList.get().getValue());
        if (!subDataList.isPresent()) {
            return false;
        }

        return this.setLastAsDeleted(subDataList.get().getValue());
    }

    @Override
    public List<V> getValues(K subKey, K fullKey) {
        return getValues(subKey, fullKey, false);
    }

    @Override
    public K prevKey(K subKey, K fullKey) {
        List<K> sortedKeys = this.allKeys();
        int keyIndex = sortedKeys.indexOf(fullKey);
        if (keyIndex > 0) {
            return sortedKeys.get(keyIndex - 1);
        }
        return null;
    }

    @Override
    public K nextKey(K subKey, K fullKey) {
        List<K> sortedKeys = this.allKeys();
        int keyIndex = sortedKeys.indexOf(fullKey);
        if (keyIndex < sortedKeys.size() - 1) {
            return sortedKeys.get(keyIndex + 1);
        }
        return null;
    }

    @Override
    public List<V> previousValues(K subKey, K fullKey) {
        List<V> values = this.getValues(subKey, fullKey, true);
        if (values != null) {
            Collections.reverse(values);
        }
        return values;
    }

    @Override
    public boolean contains(K subKey, K fullKey) {
        Optional<Data<K, List<Data<K, List<Data<K, V>>>>>> subList = this.getSubKeyList(subKey);
        if (!subList.isPresent()) {
            return false;
        }

        Optional<Data<K, List<Data<K, V>>>> subDataList = this.getKeyList(fullKey, subList.get().getValue());
        return subDataList.isPresent();
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

    /**
     * Returns the values for a key
     * @param subKey
     * @param fullKey
     * @param getOnlyDeleted
     * @return
     */
    private List<V> getValues(K subKey, K fullKey, boolean getOnlyDeleted) {
        Optional<Data<K, List<Data<K, List<Data<K, V>>>>>> subList = this.getSubKeyList(subKey);
        if (!subList.isPresent()) {
            return null;
        }

        Optional<Data<K, List<Data<K, V>>>> subDataList = this.getKeyList(fullKey, subList.get().getValue());
        if (!subDataList.isPresent()) {
            return null;
        }

        return subDataList.get().getValue()
                .stream()
                .filter(data -> !getOnlyDeleted || data.isDeleted())
                .map(data -> data.getValue())
                .collect(Collectors.toList());
    }

    /**
     * Returns the sub-data list with the sub-key
     * @param subKey
     * @return
     */
    private Optional<Data<K, List<Data<K, List<Data<K, V>>>>>> getSubKeyList(K subKey) {
        return this.values.parallelStream()
                .filter(subKeyDataList -> subKey.equals(subKeyDataList.getKey()))
                .findFirst();
    }

    /**
     * Returns the data list with the key
     * @param key
     * @param subList
     * @return
     */
    private Optional<Data<K, List<Data<K, V>>>> getKeyList(K key, List<Data<K, List<Data<K, V>>>> subList) {
        return subList.parallelStream()
                .filter(keyDataList -> key.equals(keyDataList.getKey()))
                .findFirst();
    }
}
