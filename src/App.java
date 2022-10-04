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
      // prompt page
      case 1: {
        System.out.println("Welcome to Chefbook! \nWhat would you like to do?");
        System.out.println("\t(1) List all recipes in the book");
        System.out.println("\t(2) Create a recipe");
        System.out.println("\t(press `x` to exit)");
        break;
      }
      // view saved recipes
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
      // create a recipe
      case 3: {
        create_recipe();
        break;
      }
    }
  }

  public static void display_recipe(int recipe_num) {
    System.out.printf("You have chosen recipe#%d! (To be implemented)\n", recipe_num);
  }

  // prompt the user to enter information to create and save a new recipe
  public static void create_recipe() {
    Scanner scanner = new Scanner(System.in);

    // get name and description
    System.out.print("Name: ");
    String name = scanner.nextLine();
    System.out.print("Description: ");
    String description = scanner.nextLine();

    // get ingredients
    System.out.println("Enter the ingredients. Press enter after each ingredient. Enter `done` to finish.");
    ArrayList<String> ingredients = new ArrayList<>();
    while (scanner.hasNextLine()) {
      String input = scanner.nextLine();
      if (input.equalsIgnoreCase("done")) {
        break;
      }
      ingredients.add(input);
    }

    // get instructions
    System.out.println("Enter the instructions. Press enter after each instruction. Enter `done` to finish.");
    ArrayList<String> instructions = new ArrayList<>();
    while (scanner.hasNextLine()) {
      String input = scanner.nextLine();
      if (input.equalsIgnoreCase("done")) {
        break;
      }
      instructions.add(input);
    }

    Recipe r = new Recipe(name, description, ingredients, instructions);
    System.out.println("name: " + r.getName());
    System.out.println("description: " + r.getDescription());
    System.out.println("ingredients: " + r.getIngredients());
    System.out.println("instructions: " + r.getInstructions());
    System.out.println("saving recipe (to be implemented)");
    scanner.close();
  }

  public static boolean check(String input) {
    // System.out.println("\t user input: " + input + "\n\t ");

    // if user wants to exit
    if (input.equals("x")) {
      running = false;
      System.out.println("\nGood bye!");
      return true;
    }
    switch (page_num) {
      // prompt page
      case 1: {
        switch (input) {
          case "1": {
            page_num = 2;
            break;
          }
        }
        break;
      }
      // viewing saved recipes page
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