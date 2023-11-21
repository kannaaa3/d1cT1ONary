package model.util;

import java.lang.reflect.Array;
import java.util.List;

public class Converter {
    public static int NUMBER_BIT_OF_CHAR = 8;

    /**
     * This function is used to convert a string to a binary array.
     *
     * @param target the string we want to convert
     * @return an Integer array which is the converted string
     */
    public static Integer[] convertStringToBinaryArray(String target) {
        Integer[] ans = new Integer[target.length() * NUMBER_BIT_OF_CHAR];
        for (int i = 0; i < target.length(); i++) {
            for (int j = 0; j < NUMBER_BIT_OF_CHAR; j++) {
                ans[NUMBER_BIT_OF_CHAR * i + j] = ((int) target.charAt(i) >> (NUMBER_BIT_OF_CHAR - j - 1)) & 1;
            }
        }
        return ans;
    }

    /**
     * This function is used to convert a binary array to a string.
     *
     * @param target the array of integer we want to convert
     * @return the recovered string
     */
    public static String convertBinaryArrayToString(Integer[] target) {
        if (target.length % NUMBER_BIT_OF_CHAR != 0) {
            throw new IllegalArgumentException("Converter exception: Converted array length: "
                    + target.length + "does not divided by " + NUMBER_BIT_OF_CHAR + "!");
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < target.length; i += NUMBER_BIT_OF_CHAR) {
            int currentChar = 0;
            for (int j = 0; j < NUMBER_BIT_OF_CHAR; j++) {
                currentChar += target[i + j] << (8 - j - 1);
            }
            ans.append((char) currentChar);
        }
        return ans.toString();
    }

    public static <K extends Comparable<K>, V extends Comparable<V> > ComparablePair<K, V>[]
        convertFromListToArray(List<ComparablePair<K, V>> list) {
        ComparablePair<K, V>[] ans =
                (ComparablePair<K, V>[]) Array.newInstance(ComparablePair.class, list.size());
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
