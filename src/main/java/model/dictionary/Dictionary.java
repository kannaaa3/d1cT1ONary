package model.dictionary;

import model.database.Database;
import model.database.WordDataQueryHandler;
import model.datastructure.RadixTree;
import model.util.APIHandler;
import model.util.Algorithm;
import model.util.Converter;
import model.word.Meaning;
import model.word.Phonetic;
import model.word.Word;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class Dictionary {
    protected String[] words;
    private final RadixTree tree;

    /**
     * Initialization of the Dictionary, need a word list to create the base dictionary.
     *
     * @param filepath the filepath to the dictionary, for industrialize purposes, please use
     *                 relative path instead of absolute path
     */
    public Dictionary(String filepath) {
        if (filepath == null) {
            throw new IllegalArgumentException("Can not initialize dictionary with a null file!");
        }
        File file = new File(filepath);
        try {
            String data = new String(Files.readAllBytes(Paths.get(file.getPath())));
            words = data.split("\n");
        } catch (IOException e) {
            System.out.println("Fatal error while initializing dictionary class: File not found!");
            e.printStackTrace();
        }

        tree = new RadixTree();
        for (String word : words) {
            tree.add(Converter.convertStringToBinaryArray(word));
        }
        Algorithm.sort(words);
    }

    /**
     * Function to add a word list to the dictionary.
     *
     * @param words the words in the dictionary
     */
    public Dictionary(String[] words) {
        this.words = words;
        tree = new RadixTree();
        for (String word : words) {
            tree.add(Converter.convertStringToBinaryArray(word));
        }
        Algorithm.sort(words);
    }

    /**
     * This function is used to recommend the word that have matched prefix by lexicography order.
     *
     * @param word                    the word we want to recommend
     * @param numberOfRecommendedWord the number of word we need to recommend
     * @return a List of String which are the recommended word
     */
    public List<String> getRecommendedWordByPrefix(String word, int numberOfRecommendedWord) {
        if (word == null) {
            throw new IllegalArgumentException("Can not get recommended for null word!");
        }
        Integer[] convertedWord = Converter.convertStringToBinaryArray(word);
        List<Integer[]> ansBinary = tree.findBestMatchWord(convertedWord, numberOfRecommendedWord);

        List<String> ans = new ArrayList<>();
        for (Integer[] integers : ansBinary) {
            ans.add(Converter.convertBinaryArrayToString(integers));
        }
        return ans;
    }

    /**
     * Function to check if a word present in the dictionary.
     *
     * @param word the word we want to check
     * @return true if the dictionary contains word
     */
    public boolean contains(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Can not check if null word exists!");
        }
        Integer[] convertedWord = Converter.convertStringToBinaryArray(word);
        return tree.contains(convertedWord);
    }

    /**
     * This function invoke the translation api to get the word definition in english.
     *
     * @param word the word we want to translate
     * @return a Word object for the translation of the word (english - english)
     */
    public abstract Word getWordData(String word);
}