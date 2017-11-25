package com.dubemarcantoine.comp352.smartar;

public class Data<K, V> {

    private K key;
    private V value;
    private boolean isDeleted;

    public Data(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }
}
