package unittest;

import model.dictionary.Dictionary;
import model.util.APIHandler;
import model.util.Converter;
import model.word.Word;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Dictionary dictionary = new Dictionary("src/main/resources/words.txt");
        Word word = dictionary.getWordData("opinions");
        System.out.println(word);
    }
}
