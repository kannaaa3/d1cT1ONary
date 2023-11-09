package model.util;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class APIHandler {
    public static JSONObject getJsonDataFromURL(URL url) {
        try {
            String json = IOUtils.toString(url, StandardCharsets.UTF_8);
            return new JSONObject(json);
        } catch (IOException e) {
            System.out.println("Error while fetching json data from: " + url);
            return null;
        }
    }
}