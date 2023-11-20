package unittest;

import model.database.Database;

public class ModelUserQueryTest {
    public static void main(String[] args) {
        String[] meaningList = new String[]{
                "unfit", "good", "subtle", "ill"
        };
        String[] synonymList = new String[]{
                "hello", "love", "alive", "hard"
        };
        String[] antonymList = new String[]{
                "hate", "hard", "fit"
        };
        String[] synonym;
        String[] antonym;
        for (String word : meaningList) {
            System.out.println("Meaning of word " + word + ": " + Database.getWordMeaning(word));
        }

        for (String word : synonymList) {
            synonym = Database.getSynonym(word);
            System.out.println("Synonym of word " + word + ": ");
            for (String s : synonym) {
                System.out.print(s + " ");
            }
            System.out.println();
        }

        for (String word : antonymList) {
            antonym = Database.getAntonym(word);
            System.out.println("Antonym of word " + word + ": ");
            for (String s : antonym) {
                System.out.print(s + " ");
            }
            System.out.println();
        }

    }
}
