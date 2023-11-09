package model.util;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class APIHandler {
    public static final String DICTIONARY_API_DEV = "https://api.dictionaryapi.dev/api/v2/entries/en/";

    public static JSONArray getJsonArrayDataFromURL(URL url) {
        try {
            String json = IOUtils.toString(url, StandardCharsets.UTF_8);
            return new JSONArray(json);
        } catch (IOException e) {
            System.out.println("Error while fetching json data from: " + url);
            return null;
        }
    }
}