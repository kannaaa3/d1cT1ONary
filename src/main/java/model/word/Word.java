package model.word;

public class Word {
    private final Long wordID;
    private final Long wordNum;
    private final String word;
    private final Phonetic phonetic;
    private final Meaning meaning;

    /**
     * Public constructor for a word. Note that meaning contains definition, part of speech, example
     * of the word, synonyms and antonyms.
     *
     * @param word     text representation of the word
     * @param phonetic phonetic of the word
     * @param meaning  meaning of the word
     */
    public Word(String word, Phonetic phonetic, Meaning meaning) {
        this.wordID = 0L;
        this.wordNum = 0L;
        this.word = word;
        this.phonetic = phonetic;
        this.meaning = meaning;
    }

    /**
     * Constructor with 5 parameter for local usage.
     *
     * @param wordID   the word's id
     * @param wordNum  the word's number
     * @param word     the word
     * @param phonetic word's phonetic
     * @param meaning  word's meaning
     */
    public Word(Long wordID, Long wordNum, String word, Phonetic phonetic, Meaning meaning) {
        this.wordID = wordID;
        this.wordNum = wordNum;
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

    public Long getWordID() {
        return wordID;
    }

    public Long getWordNum() {
        return wordNum;
    }

    /**
     * The representation of the word.
     *
     * @return the representation in format: Word[word=%s,phonetic=%s,meaning=%s]
     */
    public String toString() {
        return String.format("Word[word=%s,phonetic=%s,meaning=%s]",
                word, phonetic.toString(), meaning.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Word otherWord)) {
            return false;
        }
        return this.word.equals(otherWord.word);
    }
}