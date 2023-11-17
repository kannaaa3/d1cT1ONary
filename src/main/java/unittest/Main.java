package unittest;

import model.dictionary.Dictionary;
import model.word.Word;

public class Main {
    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Dictionary dictionary = new Dictionary("src/main/resources/words.txt");
        System.out.println(
                dictionary.getRecommendedWordByPrefix("opinion", 10));
        System.out.println(
                dictionary.getRecommendedWordByPrefix("concei", 10));
    }
}
