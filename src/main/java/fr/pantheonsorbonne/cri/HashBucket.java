package fr.pantheonsorbonne.cri;

import java.util.List;

public class HashBucket<T> {
    private int hashValue;
    private List<T> values;
    
    public boolean contains(T value) {
        return values.contains(value);
    }
    private boolean isToBeAdded(T value) {
        return value.hashCode() == hashValue && !this.contains(value);
    }
}
