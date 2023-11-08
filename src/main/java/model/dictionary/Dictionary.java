package model.dictionary;

import model.datastructure.RadixTree;
import model.util.Algorithm;
import model.util.Converter;
import model.word.Word;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private String[] words;
    private final RadixTree tree;

    /**
     * Initialization of the Dictionary, need a word list to create the base dictionary.
     *
     * @param filepath the filepath to the dictionary, for industrialize purposes, please use
     *                 relative path instead of absolute path
     */
    public Dictionary(String filepath) {
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
     * This function is used to recommend the word that have matched prefix by lexicography order.
     *
     * @param word the word we want to recommend
     * @param numberOfRecommendedWord the number of word we need to recomment
     * @return a List of String which are the recommended word
     */
    public List<String> getRecommendedWord(String word, int numberOfRecommendedWord) {
        Integer[] convertedWord = Converter.convertStringToBinaryArray(word);
        List<Integer[]> ansBinary = tree.findBestMatchWord(convertedWord, numberOfRecommendedWord);

        List<String> ans = new ArrayList<>();
        for (Integer[] integers : ansBinary) {
            ans.add(Converter.convertBinaryArrayToString(integers));
        }
        return ans;
    }

    public boolean contains(String word) {
        int l = 0, r = words.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            int compare = words[mid].compareTo(word);
            if (compare == 0) {
                return true;
            }
            if (compare < 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return words[l].compareTo(word) == 0;
    }

    /**
     * This function invoke the translation api to get the word definition in english.
     *
     * @param word the word we want to translate
     * @return a Word object for the translation of the word (english - english)
     */
    public Word getWordData(String word) {
        URL url;
        try {
            url = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/" + word);
        } catch (MalformedURLException e) {
            System.out.println("Error while creating url for API request!");
            e.printStackTrace();
            return null;
        }

        try (InputStream input = url.openStream()) {
            InputStreamReader inputStreamReader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder json = new StringBuilder();
        } catch (IOException e) {
            System.out.println("Error while fetching data from API!");
            e.printStackTrace();
            return null;
        }
    }
}