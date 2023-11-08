package model.util;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class APIHandler {
    public static JSONObject getJsonDataFromURL(URL url) {
        try {
            String json = IOUtils.toString(url, Charset.forName("UTF-8"));
        } catch (IOException e) {
            System.out.println();
            return null;
        }
    }
}
