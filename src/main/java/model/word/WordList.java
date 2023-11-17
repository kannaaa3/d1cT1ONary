package model.word;

import java.util.ArrayList;
import java.util.List;

public class WordList {
    private String name;
    private final List<Word> words;

    /**
     * This function is used to create a new word list. Please only use this function to create a
     * new wordList, if you already have a collection of the word, please use the constructor with
     * two parameters.
     *
     * @param name the name of the word list (i.e: fruit, health)
     */
    public WordList(String name) {
        this.name = name;
        this.words = new ArrayList<>();
    }

    /**
     * This function is used when you want to load a word list. Please do not pass a null word list.
     *
     * @param name the name of the word list (i.e: fruit, health)
     * @param words the wordlist that we want to load
     */
    public WordList(String name, List<Word> words) {
        if (words == null) {
            throw new IllegalArgumentException("Please do not create a wordlist will a null "
                    + "collection of word");
        }
        this.name = name;
        this.words = words;
    }

    /**
     * Function to add a new word to the word list.
     *
     * @param word the word we want to add
     * @return true if the word does not appear in the wordlist and add the word, false otherwise
     */
    public boolean addWord(Word word) {
        if (word == null) {
            throw new IllegalArgumentException("Please do not add a null word to the word list!");
        }
        for (Word value : words) {
            if (value.equals(word)) {
                return false;
            }
        }
        words.add(word);
        return true;
    }

    /**
     * Function to remove a word from the word list.
     *
     * @param word the word user want to remove from the list.
     */
    public void removeWord(Word word) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).equals(word)) {
                words.remove(i);
                return;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Word> getWords() {
        return words;
    }
}