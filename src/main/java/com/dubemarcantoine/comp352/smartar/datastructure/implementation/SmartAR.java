package com.dubemarcantoine.comp352.smartar.datastructure.implementation;

import com.dubemarcantoine.comp352.smartar.Car;
import com.dubemarcantoine.comp352.smartar.datastructure.AbstractSmartAR;

/**
 * Smart AR structure meant to hold a String as key and any value
 * @param <K extends String>
 * @param <V>
 */
public class SmartAR<K extends String, V> extends AbstractSmartAR<K, V> {

    private final int MIN_KEY_LENGTH = 6;
    private final int MAX_KEY_LENGTH = 12;
    private final int DEFAULT_KEY_LENGTH = 6;

    protected int maxKeyLength;

    public SmartAR() {
        super();
        this.setKeyLength(DEFAULT_KEY_LENGTH);
    }

    /**
     * Sets the fixed threshold and key length
     * @param threshold
     * @param keyLength
     */
    public SmartAR(int threshold, int keyLength) {
        super(threshold);
        this.setKeyLength(keyLength);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected K getSubkey(K key) {
        if (key.length() < this.maxKeyLength) {
            return key;
        }
        return (K)key.substring(0, this.maxKeyLength);
    }

    /**
     * Number that defines the fixed string length of keys.
     * @param keyLength
     */
    public void setKeyLength(int keyLength) {
        if (this.totalSize != 0) {
            throw new RuntimeException("Can't set key length when data structure is not empty");
        }
        if (keyLength < MIN_KEY_LENGTH || keyLength > MAX_KEY_LENGTH) {
            throw new RuntimeException("Key length has to be between " + MIN_KEY_LENGTH + " and " + MAX_KEY_LENGTH);
        }
        this.maxKeyLength = keyLength;
    }

    public static void main(String[] args) {
        SmartAR<String, Car> smartAR = new SmartAR<>(100, 6);
        smartAR.add("1234567", new Car("1234567"));
        for (int i=0; i< 100; i++) {
            smartAR.add("1234567", new Car("1234567"));
        }
        smartAR.add("1234568", new Car("1234568"));
        smartAR.add("1234569", new Car("1234568"));
        smartAR.add("1234567", new Car("1234568"));
        smartAR.add("1234567", new Car("1234568"));
        smartAR.add("12345610", new Car("1234568"));
        smartAR.add("12345611", new Car("1234568"));
        smartAR.add("12345612", new Car("1234568"));
        smartAR.add("1234578", new Car("1234568"));
        smartAR.add("1234579", new Car("1234568"));
        smartAR.add("12345710", new Car("1234568"));
        System.out.println(smartAR.getValues("12345710"));
        System.out.println(smartAR.getValues("1234567"));
        System.out.println(smartAR.previousValues("1234567"));
        System.out.println(smartAR.previousValues("12345711"));
    }
}
