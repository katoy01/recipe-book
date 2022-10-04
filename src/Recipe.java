import java.util.List;
import java.util.ArrayList;

public class Recipe {
  private String name;
  private String description;
  private List<String> ingredients;
  private List<String> instructions;

  public Recipe(String name, String description, List<String> ingredients, List<String> instructions) {
    this.name = name;
    this.description = description;
    this.ingredients = ingredients;
    this.instructions = instructions;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public List<String> getIngredients() {
    return ingredients;
  }

  public List<String> getInstructions() {
    return instructions;
  }
}
