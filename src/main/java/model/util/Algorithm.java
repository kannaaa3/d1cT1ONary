package model.util;

public class Algorithm {
    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        if (hi + 1 - lo >= 0) System.arraycopy(a, lo, aux, lo, hi + 1 - lo);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static <T extends Comparable<T>> void mergeSort(T[] a, T[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static <T extends Comparable<T>> void sort(T[] a) {
        T[] aux = (T[]) new Object[a.length];
        mergeSort(a, aux, 0, a.length - 1);
    }
}
