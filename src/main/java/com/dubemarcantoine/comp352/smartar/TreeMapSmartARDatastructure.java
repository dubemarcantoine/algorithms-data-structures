package com.dubemarcantoine.comp352.smartar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TreeMapSmartARDatastructure<K, V> implements SmartARInternalDatastructure<K, V> {

    private TreeMap<K, TreeMap<K, List<Data<K, V>>>> treeMap;

    public TreeMapSmartARDatastructure() {
        this.treeMap = new TreeMap<>();
    }

    @Override
    public List<K> allKeys() {
        List<K> keys = new ArrayList<>();
        this.treeMap.forEach((subKey, subMap) -> keys.addAll(subMap.keySet()));
        return keys;
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
        List<V> values = null;
        TreeMap<K, List<Data<K, V>>> subTreeMap = this.treeMap.get(subKey);
        if (subTreeMap != null) {
            List<Data<K, V>> keyValues = subTreeMap.get(fullKey);
            if (keyValues != null) {
                values = keyValues.stream()
                            .map(data -> data.getValue())
                            .collect(Collectors.toList());
            }
        }
        return values;
    }

    @Override
    public K prevKey(K subKey, K fullKey) {
        K previous = null;
        TreeMap<K, List<Data<K, V>>> subTreeMap = this.treeMap.get(subKey);
        if (subTreeMap != null) {
            // Get the previous key in the sub tree map
            previous = subTreeMap.lowerKey(fullKey);
            // If there is no previous key, get the previous sub tree in the tree map
            if (previous == null) {
                K previousSubTreeMapKey = this.treeMap.lowerKey(subKey);
                if (previousSubTreeMapKey != null) {
                    TreeMap<K, List<Data<K, V>>> previousSubTree = this.treeMap.get(previousSubTreeMapKey);
                    previous = previousSubTree.lastKey();
                }
            }
        }
        return previous;
    }

    @Override
    public K nextKey(K subKey, K fullKey) {
        K next = null;
        TreeMap<K, List<Data<K, V>>> subTreeMap = this.treeMap.get(subKey);
        if (subTreeMap != null) {
            // Get the next key in the sub tree map
            next = subTreeMap.higherKey(fullKey);
            // If there is no previous key, get the next sub tree in the tree map
            if (next == null) {
                K nextSubTreeMapKey = this.treeMap.higherKey(subKey);
                if (nextSubTreeMapKey != null) {
                    TreeMap<K, List<Data<K, V>>> nextSubTree = this.treeMap.get(nextSubTreeMapKey);
                    next = nextSubTree.firstKey();
                }
            }
        }
        return next;
    }

    @Override
    public List<V> previousValues(K subKey, K fullKey) {
        List<V> values = null;
        TreeMap<K, List<Data<K, V>>> subTreeMap = this.treeMap.get(subKey);
        if (subTreeMap != null) {
            List<Data<K, V>> keyValues = subTreeMap.get(fullKey);
            if (keyValues != null) {
                values = new ArrayList<>();
                // Loop will skip on last element if it is currently registered
                int startIndex = keyValues.get(keyValues.size() - 1).isDeleted() ? 1 : 2;
                for (int i = keyValues.size() - startIndex; i >= 0; i--) {
                    values.add(keyValues.get(i).getValue());
                }
            }
        }
        return values;
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

    public static void main(String[] args) {
        TreeMapSmartARDatastructure<String, Object> treeMapSmartARDatastructure = new TreeMapSmartARDatastructure<>();
        System.out.println(treeMapSmartARDatastructure.add("123456", new Data<>("1234567", new Date())));
        System.out.println(treeMapSmartARDatastructure.add("123456", new Data<>("1234567", new Date())));
        System.out.println(treeMapSmartARDatastructure.add("123456", new Data<>("1234567", new Date())));
        System.out.println(treeMapSmartARDatastructure.add("123456", new Data<>("1234568", new Date())));
        System.out.println(treeMapSmartARDatastructure.add("123456", new Data<>("1234568", new Date())));
        System.out.println(treeMapSmartARDatastructure.add("123456", new Data<>("1234569", new Date())));
        System.out.println(treeMapSmartARDatastructure.add("123457", new Data<>("1234578", new Date())));
        System.out.println(treeMapSmartARDatastructure.add("123457", new Data<>("1234577", new Date())));
        System.out.println(treeMapSmartARDatastructure.add("123457", new Data<>("1234579", new Date())));
        System.out.println(treeMapSmartARDatastructure.add("123455", new Data<>("12345555", new Date())));
        System.out.println(treeMapSmartARDatastructure.add("123455", new Data<>("12345550", new Date())));
        System.out.println(treeMapSmartARDatastructure.add("123455", new Data<>("12345551", new Date())));
        System.out.println(treeMapSmartARDatastructure.add("123455", new Data<>("12345556", new Date())));
        System.out.println(treeMapSmartARDatastructure.remove("123456", "1234567"));
        System.out.println(treeMapSmartARDatastructure.remove("123456", "1234567"));
        System.out.println(treeMapSmartARDatastructure.getValues("123456", "1234567").size());
        System.out.println(treeMapSmartARDatastructure.getValues("123456", "1234567asdf"));
        treeMapSmartARDatastructure.getValues("123456", "1234567").forEach(d -> System.out.println(d));
        System.out.println(treeMapSmartARDatastructure.nextKey("123456", "1234567"));
        System.out.println(treeMapSmartARDatastructure.nextKey("123456", "1234568"));
        System.out.println(treeMapSmartARDatastructure.nextKey("123456", "1234569"));
        System.out.println(treeMapSmartARDatastructure.prevKey("123456", "1234567"));
        System.out.println(treeMapSmartARDatastructure.prevKey("123455", "12345550"));
        for (String s : treeMapSmartARDatastructure.allKeys()) {
            System.out.println(s);
        }
    }
}
