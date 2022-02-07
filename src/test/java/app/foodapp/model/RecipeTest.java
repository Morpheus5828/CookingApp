package app.foodapp.model;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {

    @Test
    public void testGetSteps() {
        String steps = "{0=Preheat the oven to 350 degrees and grease or butter a 913 glass baking dish., 1=In a large bowl, toss together the chopped apples, cranberries and sugar., 2=Let stand for a few minutes then pour into the baking dish., 3=Dot the mixture with the 1/2 stick of cubed butter., 4=In a medium bowl, combine the oats, brown sugar and flour., 5=Sprinkle evenly over the cranberries and apples in the baking dish.  Gently pour the melted butter over the top., 6=Cover with aluminum foil and bake for 35 minutes., 7=Remove the foil and bake for an additional 15 minutes, or until the oat topping is nicely browned., 8=Serve warm as a side or for dessert with a scoop of vanilla ice cream.}";
        Recipe recipe = new Recipe(640352, "image", "title", 2, 40);
        assertEquals(steps, recipe.getSteps().toString());
    }

    @Test
    public void testGetIngredientsList() {
        String ingredientsList = "[6.0 cups Rice Krispies cereal, 2.0 cups dried cranberries, 1.0 cup white chocolate chips, 0.25 cups butter, 5.0 cups marshmallows or 40 regular marshmallows, 1.0 tsp vanilla]";
        Recipe recipe = new Recipe(640355, "image", "title", 2, 40);
        assertEquals(ingredientsList, recipe.getIngredientsList().toString());
    }
}
