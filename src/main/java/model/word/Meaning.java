package model.word;

public class Meaning {
    private final String partOfSpeech;
    private final String definition;
    private final String example;
    private final String[] synonyms;
    private final String[] antonyms;

    /**
     * Public constructor of a Meaning, please note that we assume definition is a part of the
     * larger meaning.
     *
     * @param partOfSpeech the part of speech of the word (i.e: noun, verb, ...)
     * @param definition   the definition of the word
     * @param example      an example using the word
     * @param synonyms     the synonyms of the word
     * @param antonyms     the antonyms of the word
     */
    public Meaning(String partOfSpeech, String definition, String example,
                   String[] synonyms, String[] antonyms) {
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
        this.example = example;
        this.synonyms = synonyms;
        this.antonyms = antonyms;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public String getDefinition() {
        return definition;
    }

    public String getExample() {
        return example;
    }

    public String[] getSynonyms() {
        return synonyms;
    }

    public String[] getAntonyms() {
        return antonyms;
    }
}
