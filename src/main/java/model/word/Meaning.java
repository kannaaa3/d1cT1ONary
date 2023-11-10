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

    /**
     * This function returns the representation of the meaning object.
     *
     * @return a string in format: Meaning[partOfSpeech=%s,definition=%s,example=%s,
     * synonyms=[%s],antonyms[%s]]
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Meaning[");
        sb.append("partOfSpeech=");
        sb.append(partOfSpeech);
        sb.append(",definition=");
        sb.append(definition);
        sb.append(",example=");
        sb.append(example);
        sb.append(",synonyms=[");
        for (int i = 0; i < synonyms.length; i++) {
            sb.append(synonyms[i]);
            if (i != synonyms.length - 1) {
                sb.append(",");
            }
        }
        sb.append("],antonyms=[");
        for (int i = 0; i < antonyms.length; i++) {
            sb.append(antonyms[i]);
            if (i != antonyms.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]]");
        return sb.toString();
    }
}