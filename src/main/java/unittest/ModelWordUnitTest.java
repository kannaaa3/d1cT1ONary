package unittest;

import model.word.Meaning;
import model.word.Phonetic;
import model.word.Word;
import model.word.WordList;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

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
    public void addWordWordListTest1() {
        WordList wordList = new WordList("hello");
        Word word1 = new Word("hello1", null, null);
        Word word2 = new Word("hello2", null, null);
        Word word3 = new Word("hello3", null, null);

        wordList.addWord(word1);
        wordList.addWord(word2);
        wordList.addWord(word3);

        Assert.assertEquals(wordList.getWords(), new ArrayList<Word>() {{
            add(word1);
            add(word2);
            add(word3);
        }});
    }

    @Test
    public void addWordWordListTest2() {
        WordList wordList = new WordList("hello");
        Word word1 = new Word("hello1", null, null);
        Word word2 = new Word("hello2", null, null);
        Word word3 = new Word("hello3", null, null);

        wordList.addWord(word1);
        wordList.addWord(word2);
        wordList.addWord(word2);
        wordList.addWord(word3);
        wordList.addWord(word3);

        Assert.assertEquals(wordList.getWords(), new ArrayList<Word>() {{
            add(word1);
            add(word2);
            add(word3);
        }});
    }

    @Test
    public void removeWordWordListTest1() {
        WordList wordList = new WordList("hello");
        Word word1 = new Word("hello1", null, null);
        Word word2 = new Word("hello2", null, null);
        Word word3 = new Word("hello3", null, null);

        wordList.addWord(word1);
        wordList.addWord(word2);
        wordList.addWord(word3);
        wordList.removeWord(word2);
        Assert.assertEquals(wordList.getWords(), new ArrayList<Word>() {{
            add(word1);
            add(word3);
        }});
    }

    @Test
    public void removeWordWordListTest2() {
        WordList wordList = new WordList("hello");
        Word word1 = new Word("hello1", null, null);
        Word word2 = new Word("hello2", null, null);
        Word word3 = new Word("hello3", null, null);
        Word word4 = new Word("hello4", null, null);

        wordList.addWord(word1);
        wordList.addWord(word2);
        wordList.addWord(word3);
        wordList.removeWord(word4);
        Assert.assertEquals(wordList.getWords(), new ArrayList<Word>() {{
            add(word1);
            add(word2);
            add(word3);
        }});
    }
}