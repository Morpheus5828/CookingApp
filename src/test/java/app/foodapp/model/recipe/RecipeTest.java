package app.foodapp.model.recipe;

import app.foodapp.controller.dataRequest.ApiDataRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public final class RecipeTest {
    private Recipe recipe;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /*public RecipeTest() {
        this.recipe = new Recipe("622561", null, "Pasta", 52, 42);
    }*/

    @BeforeEach
    public void setUp() {
        this.recipe = new Recipe("622561", null, "Pasta", 52, 42);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void getterTest() {
        assertEquals(recipe.getId(), "622561");
        assertNull(recipe.getImage());
        assertEquals(recipe.getTitle(), "Pasta");
        assertEquals(recipe.getServings(), 52);
        assertEquals(recipe.getCookingTime(), 42);
    }

    @Test
    public void getStepsTest() {
        String expectedSteps =
            "\t• 0 Preheat your oven to 350 degrees.First chop the fresh spinach, dice the onion, and mince the garlic.Then in a skillet, saute the spinach, onion and garlic in a little bit of olive oil, or use about 2 tsp of the bacon grease from your cooked bacon. Salt & pepper to taste.\n" +
            "\t• 1 Remove from the heat.Stir in the goat cheese, sour cream, chopped artichoke and bacon.\n" +
            "\t• 2 Add the mixture to an oven proof dish and bake for 25 - 30 minutes or until the mixture is bubbling.\n" +
            "\t• 3 Serve with baguette slices or crackers.\n";
        assertEquals(recipe.getSteps(), expectedSteps);
    }

    @Test
    public void getIngredientTest() {
        String expectedIngredients = "8.0  cooked bacon\n" +
                "8.0  crackers\n" +
                "2.0  garlic\n" +
                "140.0  goat cheese\n" +
                "0.25  marinated artichoke hearts\n" +
                "1.0  onion\n" +
                "8.0  Salt & Pepper\n" +
                "0.75  sour cream\n" +
                "225.0  spinach" + "\n";
        assertEquals(recipe.getIngredients(), expectedIngredients);
    }

    @Test
    public void displaySimpleCharacteristicTest() {
        // we display simple details in console
        recipe.displaySimpleCharacteristics();
        assertEquals(
            "Recipe :Pasta\n" +
            "Cooking Time: 42.0\n" +
            "Serving: 52.0 people(s)", outputStreamCaptor.toString().trim());
    }
    //TODO finir avec le dernier test pour les details
}
