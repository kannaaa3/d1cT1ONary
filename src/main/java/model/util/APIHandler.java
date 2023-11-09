package model.util;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class APIHandler {
    public static final String DICTIONARY_API_DEV =
            "https://api.dictionaryapi.dev/api/v2/entries/en/";

    /**
     * This function is used to fetch the JSONArray from the API. This function is currently
     * dedicated for the dictionary api dev.
     *
     * @param url the url of the API
     * @return an JSONArray which is the requested data
     */
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