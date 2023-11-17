package model.word;

public record Phonetic(String text, String audio) {
    /**
     * Public constructors with text and parameters.
     *
     * @param text  text representation of the phonetic
     * @param audio link of audio file of the phonetic
     */
    public Phonetic {
    }

    /**
     * This function returns the string representation of the phonetic.
     *
     * @return a string in format Phonetic[text=%s,audio=%s]
     */
    public String toString() {
        return String.format("Phonetic[text=%s,audio=%s]", this.text, this.audio);
    }
}
