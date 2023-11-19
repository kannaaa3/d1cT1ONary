package model.util;

import javafx.util.Pair;

public class ComparablePair<K extends Comparable<K>, V extends Comparable<V>> extends
        Pair<K, V> implements Comparable<ComparablePair<K, V>> {
    public ComparablePair(K key, V value) {
        super(key, value);
    }

    @Override
    public int compareTo(ComparablePair<K, V> o) {
        int compare = this.getKey().compareTo(o.getKey());
        if (compare == 0) {
            return this.getValue().compareTo(o.getValue());
        } else {
            return compare;
        }
    }
}