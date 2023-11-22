package model;

import model.game.agility.AgilityGame;
import model.word.Word;
import model.word.WordList;

public class Main {
    public static void main(String[] args) {
        WordList wordList = new WordList("Hello");
        wordList.writeWordListDataToFile("C:\\Users\\user\\Desktop\\ta.txt");
    }
}