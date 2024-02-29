package fr.pantheonsorbonne.cri;

import java.util.LinkedList;

public class StringHashSet {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.7;

    private LinkedList<Couple>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public StringHashSet() {
        buckets = new LinkedList[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(String key, String value) {
        int index = hash(key) % buckets.length;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        LinkedList<Couple> bucket = buckets[index];
        for (Couple couple : bucket) {
            if (couple.hash == hash(key)) {
                // Si le hash existe déjà, ne rien faire
                return;
            }
        }
        // Ajouter le couple (hash, value)
        bucket.add(new Couple(hash(key), value));
        size++;
        if ((double) size / buckets.length >= LOAD_FACTOR) {
            grow();
        }
    }

    public boolean contains(String key) {
        int index = hash(key) % buckets.length;
        if (buckets[index] != null) {
            LinkedList<Couple> bucket = buckets[index];
            for (Couple couple : bucket) {
                if (couple.hash == hash(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        int newCapacity = buckets.length * 2;
        LinkedList<Couple>[] newBuckets = new LinkedList[newCapacity];
        for (LinkedList<Couple> bucket : buckets) {
            if (bucket != null) {
                for (Couple couple : bucket) {
                    int newIndex = couple.hash % newCapacity;
                    if (newBuckets[newIndex] == null) {
                        newBuckets[newIndex] = new LinkedList<>();
                    }
                    newBuckets[newIndex].add(couple);
                }
            }
        }
        buckets = newBuckets;
    }

    private int hash(String key) {
        return key.hashCode();
    }

    private static class Couple {
        int hash;
        String value;

        Couple(int hash, String value) {
            this.hash = hash;
            this.value = value;
        }
    }
}
