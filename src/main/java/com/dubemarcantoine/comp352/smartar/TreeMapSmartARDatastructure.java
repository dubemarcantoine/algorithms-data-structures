package com.dubemarcantoine.comp352.smartar;

import java.util.*;

public class TreeMapSmartARDatastructure<K, V> implements SmartARInternalDatastructure<K, V> {

    private TreeMap<K, TreeMap<K, List<Data<K, V>>>> treeMap;

    public TreeMapSmartARDatastructure() {
        this.treeMap = new TreeMap<>();
    }

    @Override
    public boolean add(K subKey, Data<K, V> data) {
        boolean overwrites = false;
        TreeMap<K, List<Data<K, V>>> subTreeMap = this.treeMap.get(subKey);
        // Subkey has never been used
        if (subTreeMap == null) {
            subTreeMap = new TreeMap<>();
            List<Data<K, V>> values = new ArrayList<>();
            values.add(data);
            subTreeMap.put(data.getKey(), values);
            this.treeMap.put(subKey, subTreeMap);
        }
        else {
            List<Data<K, V>> values = subTreeMap.get(data.getKey());
            // Full key has never been used
            if (values == null) {
                values = new ArrayList<>();
                values.add(data);
                subTreeMap.put(data.getKey(), values);
            } else {
                // Set the last value in the list as deleted
                overwrites = this.setLastAsDeleted(values);
                // Insert the data in the list
                values.add(data);
            }
        }

        return overwrites;
    }

    @Override
    public boolean remove(K subKey, K fullKey) {
        boolean deleted = false;
        TreeMap<K, List<Data<K, V>>> subTreeMap = this.treeMap.get(subKey);
        if (subTreeMap != null) {
            List<Data<K, V>> values = subTreeMap.get(fullKey);
            if (values != null) {
                deleted = this.setLastAsDeleted(values);
            }
        }
        return deleted;
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

    public static void main(String[] args) {
        TreeMapSmartARDatastructure<String, Object> treeMapSmartARDatastructure = new TreeMapSmartARDatastructure<>();
        System.out.println(treeMapSmartARDatastructure.add("123456", new Data<>("1234567", new Date())));
        System.out.println(treeMapSmartARDatastructure.add("123456", new Data<>("1234567", new Date())));
        System.out.println(treeMapSmartARDatastructure.add("123456", new Data<>("1234568", new Date())));
        System.out.println(treeMapSmartARDatastructure.add("123456", new Data<>("1234568", new Date())));
        System.out.println(treeMapSmartARDatastructure.add("123456", new Data<>("1234569", new Date())));
        System.out.println(treeMapSmartARDatastructure.remove("123456", "1234567"));
        System.out.println(treeMapSmartARDatastructure.remove("123456", "1234567"));
    }
}
