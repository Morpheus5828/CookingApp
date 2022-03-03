package app.foodapp.model.node;

import app.foodapp.model.recipe.RecipeInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetRecipeByIngredientTest {

    private final PrintStream standardOut = System.out;
    private GetRecipeByIngredient recipe;
    private RecipeInformation recipeInformation;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void askToEnterIngredientsTest() {
        recipe = new GetRecipeByIngredient();
        String result =
                "Please enter ingredient(s) and type 'end' when you're finished \n\n" +
                "\t Type ingredient(s) :";
        System.out.println(recipe.askToEnterIngredients());
        assertEquals(result, outputStreamCaptor.toString().trim());
    }

    @Test
    public void sendRequestTest() {
        recipe = new GetRecipeByIngredient();
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        recipeInformation = new RecipeInformation(list);
        String result = recipe.sendRequest();
        assertEquals(recipeInformation.display(), recipe.sendRequest().toString());
    }



}
