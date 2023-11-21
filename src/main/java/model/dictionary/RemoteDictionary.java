package model.dictionary;

import model.util.APIHandler;
import model.word.Meaning;
import model.word.Phonetic;
import model.word.Word;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RemoteDictionary extends Dictionary {
    /**
     * Function to initialize a remote dictionary, this dictionary need a text file and an API to
     * get word data.
     *
     * @param filepath the path to the word's data file
     */
    public RemoteDictionary(String filepath) {
        super(readWordListFromFile(filepath));
    }

    /**
     * Function get remote word data with API.
     *
     * @param word the word we want to translate
     * @return a Word object which is fetched from the API
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
    private static Phonetic extractPhonetic(JSONObject object) {
        if (object == null) {
            return new Phonetic("", "");
        }
        String text;
        String audio;

        try {
            text = object.getString("text");
        } catch (Exception e) {
            text = "";
            e.printStackTrace();
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
    private static Meaning extractMeaning(JSONObject object) {
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

    /**
     * Function to read word data from file.
     *
     * @param filepath the path to the file
     * @return an array of string which are the word
     */
    private static String[] readWordListFromFile(String filepath) {
        if (filepath == null) {
            throw new IllegalArgumentException("Can not initialize dictionary with a null file!");
        }
        File file = new File(filepath);
        try {
            String data = new String(Files.readAllBytes(Paths.get(file.getPath())));
            return data.split("\n");
        } catch (IOException e) {
            System.out.println("Fatal error while initializing dictionary class: File not found!");
            e.printStackTrace();
            System.exit(-1);
        }
        return new String[0];
    }
}