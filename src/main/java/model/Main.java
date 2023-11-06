package model;

import model.word.Word;
import model.word.Phonetic;
import model.word.Meaning;

public class Main {
    public static void main(String[] args) {
        Phonetic phonetic = new Phonetic("həˈləʊ", "//ssl.gstatic.com/diction" +
                "ary/static/sounds/20200429/hello--_gb_1.mp3");
        String[] x = new String[]{
            "aa", "bb", "cc"
        };
        String[] y = new String[]{
                "dd", "ee", "ff"
        };
        Meaning meaning = new Meaning("noun", "aaa", "Hello!", x, y);
        Word word = new Word("hello", phonetic, meaning);
    }
}
