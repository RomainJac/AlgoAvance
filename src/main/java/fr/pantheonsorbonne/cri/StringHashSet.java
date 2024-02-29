package fr.pantheonsorbonne.cri;

import java.util.LinkedList;
import java.util.Objects;

public class StringHashSet {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.7;

    public LinkedList<Couple>[] buckets;
    public int size;

    @SuppressWarnings("unchecked")
    public StringHashSet() {
        buckets = new LinkedList[DEFAULT_CAPACITY];
        size = 0;
    }

    public boolean add(String s) {
        int hash = s.hashCode();
        int index = Math.abs(hash) % buckets.length;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<Couple>();
        }
        for (Couple c : buckets[index]) {
            if (c.hash == hash && c.value.equals(s)) {
                return false;
            }
        }
        buckets[index].add(new Couple(hash, s));
        size++;
        if ((double) size / buckets.length >= LOAD_FACTOR) {
            grow();
        }
        return true;
    }
    

    public boolean contains(String key) {
        int index = Math.abs(hash(key)) % buckets.length;
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

    public static class Couple {
        int hash;
        String value;

        Couple(int hash, String value) {
            this.hash = hash;
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Couple other = (Couple) obj;
            return Objects.equals(value, other.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

    }

}
