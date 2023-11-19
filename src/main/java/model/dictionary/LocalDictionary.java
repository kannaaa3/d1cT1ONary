package model.dictionary;

import model.database.Database;
import model.word.Word;

public class LocalDictionary extends Dictionary {
    public LocalDictionary() {
        super(Database.getAllWordFromDatabase());
    }

    public Word getWordData(String word) {
        return null;
    }
}
