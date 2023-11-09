package unittest;

import model.util.Converter;

public class Main {
    public static void main(String[] args) {
        String word = "hello";
        Integer[] ans = Converter.convertStringToBinaryArray(word);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i]);
        }
        System.out.println();
        System.out.println(Converter.convertBinaryArrayToString(ans));
    }
}
