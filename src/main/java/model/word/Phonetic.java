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
}
