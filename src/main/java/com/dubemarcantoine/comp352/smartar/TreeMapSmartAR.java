package com.dubemarcantoine.comp352.smartar;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;

public class TreeMapSmartAR<K, V> implements SmartARInternal<K, V> {

    private TreeMap<K, TreeMap<K, LinkedHashSet<V>>> treeMap;

    @Override
    public void add(K key, V value) {

    }

    @Override
    public void remove(K key) {

    }

    @Override
    public List<V> getValues(K key) {
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
    public List<V> previousValues(K key) {
        return null;
    }

    public static void main(String[] args) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i=0; i<100; ++i) {
            map.put(i, i);
        }
        System.out.println(map.higherEntry(0).getKey());
        System.out.println(map.lowerEntry(0).getKey());
    }
}
