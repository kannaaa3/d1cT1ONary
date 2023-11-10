package model.word;

public class Phonetic {
    /**
     * Potato
     */
    private final String text;
    private final String audio;

    /**
     * Public constructors with text and parameters.
     *
     * @param text text representation of the phonetic
     * @param audio link of audio file of the phonetic
     */
    public Phonetic(String text, String audio) {
        this.text = text;
        this.audio = audio;
    }

    public String getText() {
        return text;
    }

    public String getAudio() {
        return audio;
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
