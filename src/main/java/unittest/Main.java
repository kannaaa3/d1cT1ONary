package unittest;

import model.database.Database;
import model.dictionary.Dictionary;
import model.dictionary.LocalDictionary;
import model.dictionary.RemoteDictionary;
import model.user.User;
import model.word.Word;
import model.word.WordList;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static User user = null;
    public static Dictionary dictionary;
    public static Scanner scanner = new Scanner(System.in);
    public static void printMenu() {
        System.out.print("""
                1. Register
                2. Login
                3. Get Recommended Word
                4. Search Word
                5. Show Search History
                6. Create New Word List
                7. Show My Word List
                8. Remove Word From Word List
                9. Remove Word List
                10. Remove Word From Search History
                """);
    }

    public static void register() {
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();
        Database.register(username, password);
    }

    public static void login() {
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();
        String userID = Database.login(username, password);
        if (userID == null) {
            System.out.println("Can not login!");
        } else {
            System.out.println("Successfully login as " + username);
            user = new User(userID);
        }
    }

    public static void getRecommendedWord() {
        System.out.print("Word: ");
        String word = scanner.next();
        List<String> recommended = dictionary.getRecommendedWordByPrefix(word, 10);
        System.out.println("Recommended Word:");
        for (String wd : recommended) {
            System.out.println(wd);
        }
    }

    public static void searchWord() {
        System.out.print("Word: ");
        String word = scanner.next();
        Word wd = dictionary.getWordData(word);
        if (wd == null) {
            System.out.println("Can not find word!");
            return;
        }
        System.out.println(wd);
        user.addWordToSearchHistory(word);
        System.out.println("Save?");
        int option = scanner.nextInt();
        if (option != 1) {
            return;
        }
        System.out.println("Word list id?");
        option = scanner.nextInt();
        user.addWordToWordList(wd, option);
    }

    public static void showSearchHistory() {
        System.out.println("User data:");
        for (String wd : user.getSearchHistory()) {
            System.out.println(wd);
        }
        System.out.println("Database data:");
        for (String wd : Database.getUserSearchHistory(user.getUserID())) {
            System.out.println(wd);
        }
    }

    public static void createNewWordList() {
        System.out.println("Wordlist name: ");
        String wordListName = scanner.next();
        user.createNewWordList(wordListName);
    }

    public static void showMyWordList() {
        System.out.println("User data:");
        List<WordList> userData = user.getAllWordLists();
        for (WordList wordList : userData) {
            System.out.println("Word list name: " + wordList.getName());
            for (Word word : wordList.getWords()) {
                System.out.println(word);
            }
        }
        System.out.println("Database data:");
        userData = Database.getUserWordLists(user.getUserID());
        for (WordList wordList : userData) {
            System.out.println("Word list name: " + wordList.getName());
            for (Word word : wordList.getWords()) {
                System.out.println(word);
            }
        }
    }

    public static void removeWordFromWordList() {
        System.out.println("Word list id:");
        int listID = scanner.nextInt();
        System.out.println("Word num: ");
        int wordNum = scanner.nextInt();
        user.removeWordFromWordList(listID, wordNum);
    }

    public static void removeWordList() {
        System.out.println("Word list id:");
        int listID = scanner.nextInt();
        user.removeWordList(listID);
    }

    public static void removeWordFromSearchHistory() {
        System.out.println("Word?");
        String word = scanner.next();
        user.removeWordFromSearchHistory(word);
    }

    public static void main(String[] args) {
        dictionary = new LocalDictionary();
        while (true) {
            printMenu();
            int option = scanner.nextInt();
            switch (option) {
                case 1: {
                    register();
                    break;
                }

                case 2: {
                    login();
                    break;
                }

                case 3: {
                    getRecommendedWord();
                    break;
                }

                case 4: {
                    searchWord();
                    break;
                }

                case 5: {
                    showSearchHistory();
                    break;
                }

                case 6: {
                    createNewWordList();
                    break;
                }

                case 7: {
                    showMyWordList();
                    break;
                }

                case 8: {
                    removeWordFromWordList();
                    break;
                }

                case 9: {
                    removeWordList();
                    break;
                }

                case 10: {
                    removeWordFromSearchHistory();
                    break;
                }
            }
        }
    }
}
