package model.dictionary;

import model.datastructure.RadixTree;
import model..APIHandler;
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
     * This function is used to recommend the word that have matched prefix by lexicography order.
     *
     * @param word                    the word we want to recommend
     * @param numberOfRecommendedWord the number of word we need to recommend
     * @return a List of String which are the recommended word
     */
    public List<String> getRecommendedWord(String word, int numberOfRecommendedWord) {
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
        if (word == null) {
            throw new IllegalArgumentException("Can not get word data on null object!");
        }

        JSONArray jsonArray;

        try {
            URL url = new URL(APIHandler.DICTIONARY_API_DEV + word);
            jsonArray = APIHandler.getJsonArrayDataFromURL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Can not get word data from: "
                    + APIHandler.DICTIONARY_API_DEV + word);
        }

        if (jsonArray == null) {
            return null;
        }

        if (jsonArray.isEmpty()) {
            return null;
        }

        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String wd = (String) jsonObject.get("word");

        JSONArray phoneticObjects = jsonObject.getJSONArray("phonetics");
        JSONObject phoneticObject = null;
        for (int i = 0; i < phoneticObjects.length(); i++) {
            JSONObject phoneticObjectAtI = phoneticObjects.getJSONObject(i);
            if (phoneticObjectAtI.keySet().contains("text")) {
                phoneticObject = phoneticObjectAtI;
                if (phoneticObjectAtI.keySet().contains("audio")) {
                    break;
                }
            }
        }

        JSONObject meaningObject;
        try {
            meaningObject = jsonObject.getJSONArray("meanings").getJSONObject(0);
        } catch (Exception e) {
            meaningObject = null;
        }

        return new Word(wd, extractPhonetic(phoneticObject), extractMeaning(meaningObject));
    }

    /**
     * This function is used to extract phonetic data from the json object.
     *
     * @param object a json object
     * @return a phonetic object that extract "text" and "audio" data from the json object
     */
    private Phonetic extractPhonetic(JSONObject object) {
        if (object == null) {
            return new Phonetic("", "");
        }
        String text;
        String audio;

        try {
            text = object.getString("text");
        } catch (Exception e) {
            text = "";
            System.out.println(e.toString());
            System.out.println(object.toString());
        }
        try {
            audio = object.getString("audio");
        } catch (Exception e) {
            audio = "";
        }

        return new Phonetic(text, audio);
    }

    /**
     * This function is used to extract the meaning from the json object.
     *
     * @param object the json object
     * @return a meaning object with "definition", "example", "synonyms", "antonyms"
     */
    private Meaning extractMeaning(JSONObject object) {
        String partOfSpeech;
        String definition;
        String example;
        String[] synonyms;
        String[] antonyms;

        System.out.println(object);

        try {
            partOfSpeech = object.getString("partOfSpeech");
        } catch (Exception e) {
            partOfSpeech = "";
        }

        JSONObject def;
        try {
            def = object.getJSONArray("definitions").getJSONObject(0);
        } catch (Exception e) {
            definition = "";
            example = "";
            synonyms = new String[0];
            antonyms = new String[0];
            return new Meaning(partOfSpeech, definition, example, synonyms, antonyms);
        }

        try {
            definition = def.getString("definition");
        } catch (Exception e) {
            definition = "";
        }
        try {
            example = def.getString("example");
        } catch (Exception e) {
            example = "";
        }
        try {
            synonyms = (String[]) def.get("synonyms");
        } catch (Exception e) {
            synonyms = new String[0];
        }
        try {
            antonyms = (String[]) def.get("antonyms");
        } catch (Exception e) {
            antonyms = new String[0];
        }
        return new Meaning(partOfSpeech, definition, example, synonyms, antonyms);
    }
}