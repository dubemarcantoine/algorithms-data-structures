package com.github.dubemarcantoine.comp352.smartar.datastructure.implementation;

import com.github.dubemarcantoine.comp352.smartar.datastructure.AbstractSmartAR;

import java.util.*;

/**
 * Smart AR structure meant to hold a String as key and any value
 * @param <K extends String>
 * @param <V>
 */
public class SmartAR<K extends String, V> extends AbstractSmartAR<K, V> {

    private final int MIN_KEY_LENGTH = 6;
    private final int MAX_KEY_LENGTH = 12;
    private final int DEFAULT_KEY_LENGTH = 6;
    private final int GENERATED_KEY_LENGTH = 12;

    private final char[] ALPHANUMERIC_CHARS = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789")
            .toCharArray();

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

    /**
     * Randomly generates a sequence containing n new non-existing keys of alphanumeric characters
     * @param size
     */
    @SuppressWarnings("unchecked")
    public Set<String> generate(int size) {
        // Using set for uniqueness
        Set<String> keys = new HashSet<>();
        while (keys.size() != size) {
            String randomString = this.randomString();
            if (!this.internalDatastructure.contains(this.getSubkey((K)randomString), (K)randomString)) {
                keys.add(randomString);
            }
        }
        return keys;
    }

    /**
     * Returns a sequence (sorted in reverse chronological order) of cars
     * (previously) registered with the given key (license plate).
     * @param key
     * @return
     */
    public List<V> previousCars(K key) {
        return this.previousValues(key);
    }

    private String randomString() {
        StringBuilder stringBuilder = new StringBuilder();
        Random ran = new Random();

        for (int i = 0; i< GENERATED_KEY_LENGTH; i++) {
            int pos = ran.nextInt(ALPHANUMERIC_CHARS.length - 1);
            stringBuilder.append(ALPHANUMERIC_CHARS[pos]);
        }
        return stringBuilder.toString();
    }

}
