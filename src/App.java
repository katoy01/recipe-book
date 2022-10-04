import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

// Code help from:
// https://stackoverflow.com/questions/18977144/how-to-parse-json-array-not-json-object-in-android

public class App {
  public static final String RECIPEBOOK = "src/recipebook.json";

  // prompt the user to enter information to create and save a new recipe
  public static void create_recipe(Scanner scanner) {
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
  }

  // TODO: Implement
  // Display recipe information
  public static void display_recipe(int recipe_num) {
    System.out.printf("You have chosen recipe#%d! (To be implemented)\n", recipe_num);
  }

  // list all recipes and prompt user to select which one to view
  public static void list_all_recipes(Scanner scanner) {
    System.out.println("Here are all the recipes we have stored!");
    System.out.println("If you would like to check a specific recipe, enter the corresponding number.");
    System.out.println("Enter `back` to go back to the main menu.");

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

    // process user input
    String input = scanner.nextLine();
    // if user wants to go back to main menu
    if (input.equalsIgnoreCase("back")) {
      return;
    }
    // display recipe based on number
    display_recipe(Integer.parseInt(input)); // TODO: handle non-integers and out of bounds integers
  }

  // display main menu prompt
  public static void main_menu() {
    System.out.println("--------");
    System.out.println("Welcome to Chefbook! \nWhat would you like to do?");
    System.out.println("\t(1) List all recipes in the book");
    System.out.println("\t(2) Create a recipe");
    System.out.println("\t(press `x` to exit)");
  }

  public static void main(String[] args) throws Exception {
    /**
     * Main app / home page UI
     * Reads recipebook local file and displays choices to user
     */
    Scanner scanner = new Scanner(System.in);
    while (true) {
      main_menu();
      String input = scanner.nextLine();
      // exit condition
      if (input.equalsIgnoreCase("x")) {
        System.out.println("Good bye!");
        scanner.close();
        break;
      }
      switch (input) {
        case "1": {
          list_all_recipes(scanner);
          break;
        }
        case "2": {
          create_recipe(scanner);
          break;
        }
      }
    }
  }
}