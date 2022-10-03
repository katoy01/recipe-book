import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

// Code help from:
// https://stackoverflow.com/questions/18977144/how-to-parse-json-array-not-json-object-in-android

public class App {
    public static final String RECIPEBOOK = "src/recipebook.json";
    public static boolean running = true;

    public static void main(String[] args) throws Exception {
        /**
         * Main app/ home page UI
         * Reads recipebook local file and displays choices to user
         */

        // while (running) {

        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonarray = (JSONArray) parser.parse(new FileReader(RECIPEBOOK));
            for (Object x : jsonarray) {
                JSONObject recipe = (JSONObject) x;
                String name = (String) recipe.get("name");
                System.out.println(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // }
    }

}