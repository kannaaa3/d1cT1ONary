package model.user;

import model.database.Database;
import model.word.UserWord;
import model.word.Word;
import model.word.WordList;
import unittest.Main;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int index = 0;
    private final String userID;
    private final List<String> searchHistory;
    private final List<WordList> wordLists;
    private final List<UserWord> reviewWordlist;

    /**
     * Create a new user with the userID.
     *
     * @param userID the userID
     */
    public User(String userID) {
        this.userID = userID;
        this.searchHistory = Database.getUserSearchHistory(userID);
        this.wordLists = Database.getUserWordLists(userID);
        for (WordList wordList : wordLists) {
            index = Math.max(index, wordList.getWordListID() + 1);
        }
        this.reviewWordlist = new ArrayList<>();
    }

    /**
     * Function to add a new word to the search history.
     *
     * @param word a string which is the word we want to add to the history
     */
    public void addWordToSearchHistory(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Please do not add a null word "
                    + "into the search history!");
        }
        removeWordFromSearchHistory(word);
        searchHistory.add(word);
        Database.addWordToUserSearchHistory(userID, word);
    }

    /**
     * Function to remove a word from the search history.
     *
     * @param word a string which is the word we want to add to the history
     */
    public void removeWordFromSearchHistory(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Please do not remove a null word "
                    + "from the search history!");
        }
        for (int i = 0; i < searchHistory.size(); i++) {
            if (searchHistory.get(i).equals(word)) {
                searchHistory.remove(i);
                Database.deleteWordFromUserHistory(userID, word);
                break;
            }
        }
    }

    /**
     * Function to create new word list with the corresponding name.
     *
     * @param wordListName the new wordlist name
     */
    public void createNewWordList(String wordListName) {
        wordLists.add(new WordList(wordListName));
        wordLists.get(wordLists.size() - 1).setWordListID(index);
        Database.createNewWordList(userID, index, wordListName);
        index++;
    }

    /**
     * Function to add a new word to a user's word list.
     *
     * @param word       the word user want to add
     * @param wordListID the wordListID, please note that enumeration begins at 0
     */
    public void addWordToWordList(Word word, int wordListID) {
        if (wordListID < 0 || wordListID >= wordLists.size()) {
            System.out.println("Word list ID out of bound "
                    + "when adding word to word list!");
            return;
        }
        for (Word reviewWord : reviewWordlist) {
            if (reviewWord.equals(word)) {
                return;
            }
        }
        Database.addWordToWordList(userID, wordListID, word);
        wordLists.get(wordListID).addWord(word);
    }

    /**
     * Function to get wordlist name with wordlist ID.
     *
     * @param wordListID the wordlist ID
     * @return the word list name
     */
    public String getWordListName(int wordListID) {
        if (wordListID < 0 || wordListID >= wordLists.size()) {
            return null;
        }
        return wordLists.get(wordListID).getName();
    }

    /**
     * Function to get wordlist with wordlist id.
     *
     * @param wordListID the wordlistID
     * @return a word list
     */
    public WordList getWordList(int wordListID) {
        if (wordListID < 0 || wordListID >= wordLists.size()) {
            return null;
        }
        return wordLists.get(wordListID);
    }

    /**
     * Function is used when user want to remove a wordlist.
     *
     * @param wordListID id of the wordlist we want to remove
     */
    public void removeWordList(int wordListID) {
        if (wordListID < 0 || wordListID >= wordLists.size()) {
            System.out.println("WordListID out of bound when removing wordlist!");
            return;
        }
        Database.removeWordList(userID, wordLists.get(wordListID).getWordListID());
        wordLists.remove(wordListID);
    }

    /**
     * Function is used when user want to remove word from word list.
     *
     * @param wordListID the word list id
     * @param wordID the word id
     */
    public void removeWordFromWordList(int wordListID, int wordID) {
        if (wordListID < 0 || wordListID >= wordLists.size()) {
            System.out.println("WordListID invalid while remove word from wordlist!");
            return;
        }
        Word currentWord = wordLists.get(wordListID).getWord(wordID);
        if (currentWord == null) {
            return;
        }
        Database.removeWordFromWordList(userID,
                wordLists.get(wordListID).getWordListID(), currentWord);
        wordLists.get(wordListID).removeWord(wordID);
    }

    /**
     * Function to return all user's wordlist.
     *
     * @return a List object which is all the wordlist
     */
    public List<WordList> getAllWordLists() {
        return this.wordLists;
    }

    /**
     * Function to get number of wordlist.
     *
     * @return a number which is the number of wordlist
     */
    public int getNumberOfWordList() {
        return this.wordLists.size();
    }

    public String getUserID() {
        return this.userID;
    }

    public List<String> getSearchHistory() {
        return this.searchHistory;
    }
}