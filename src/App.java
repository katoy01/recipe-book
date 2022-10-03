import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

// Code help from:
// https://stackoverflow.com/questions/18977144/how-to-parse-json-array-not-json-object-in-android

public class App {
    public static final String RECIPEBOOK = "src/recipebook.json";
    public static boolean running = true;
    public static int page_num = 1;

    public static void output(int pos) {
        switch (pos) {
            case 1: {
                System.out.println("Welcome to Chefbook! \nWhat would you like to do?");
                System.out.println("\t(1) List all recipes in the book\n\t(press `x` to exist)");
                break;
            }
            case 2: {
                System.out.println("Here are all the Recipes we have stored!");
                System.out.println("If you would like to check a specific recipe, enter the corresponding number.");
                JSONParser parser = new JSONParser();
                try {
                    JSONArray jsonarray = (JSONArray) parser.parse(new FileReader(RECIPEBOOK));
                    for (int i = 0; i < jsonarray.size(); i++) {
                        JSONObject recipe = (JSONObject) jsonarray.get(i);
                        String name = (String) recipe.get("name");
                        System.out.printf("\t(%d) %s\n", i + 1, name);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static void display_recipe(int recipe_num) {
        System.out.printf("You have chosen recipe#%d! (To be implemented)\n", recipe_num);
    }

    public static boolean check(String input) {
        // System.out.println("\t user input: " + input + "\n\t ");
        if (input.equals("x")) {
            running = false;
            System.out.println("\nGood bye!");
            return true;
        }
        switch (page_num) {
            case 1: {
                switch (input) {
                    case "1": {
                        page_num = 2;
                        break;
                    }
                }
                break;
            }
            case 2: {
                display_recipe(Integer.parseInt(input));
                break;
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        /**
         * Main app/ home page UI
         * Reads recipebook local file and displays choices to user
         */

        Scanner scanner = new Scanner(System.in);
        while (running) {

            output(page_num);

            String line = scanner.nextLine();
            if (check(line)) {
                break;
            }
        }
        scanner.close();
    }
}