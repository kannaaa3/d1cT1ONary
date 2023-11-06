package model.word;

public class Word {
    String word;
    Phonetic phonetic;
    Meaning meaning;

    /**
     * Public constructor for a word. Note that meaning contains definition, part of speech, example
     * of the word, synonyms and antonyms.
     *
     * @param word text representation of the word
     * @param phonetic phonetic of the word
     * @param meaning meaning of the word
     */
    public Word(String word, Phonetic phonetic, Meaning meaning) {
        this.word = word;
        this.phonetic = phonetic;
        this.meaning = meaning;
    }

    public String getWord() {
        return word;
    }

    public Phonetic getPhonetic() {
        return phonetic;
    }

    public Meaning getMeaning() {
        return meaning;
    }
}