package unittest;

import model.word.Meaning;
import model.word.Phonetic;
import model.word.Word;
import org.junit.Assert;
import org.junit.Test;

public class ModelWordUnitTest {
    @Test
    public void wordToStringTest1() {
        Word word = new Word("dummy",
                new Phonetic("/həˈləʊ/", "https://api.dictionaryapi.dev"
                        + "/media/pronunciations/en/hello-uk.mp3"),
                new Meaning("noun", "\"Hello!\" or an equivalent greeting.",
                        "Hewwo!", new String[]{"donowall", "kappa"},
                        new String[]{"hmm", "goodbye"}));
        Assert.assertEquals(word.toString(), "Word[word=dummy,phonetic=Phonetic[text=/həˈ"
                + "ləʊ/,audio=https://api.dictionaryapi.dev/media/pronunciations/en/hello-uk.mp3],"
                + "meaning=Meaning[partOfSpeech=noun,definition=\"Hello!\" or an equivalent "
                + "greeting.,example=Hewwo!,synonyms=[donowall,kappa],antonyms=[hmm,goodbye]]]");
    }

    @Test
    public void wordToStringTest2() {

    }
}
