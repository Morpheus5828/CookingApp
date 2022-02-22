package app.foodapp.model;

import app.foodapp.model.dataManipulation.MeasureSystem;
import app.foodapp.model.dataManipulation.recipe.Recipe;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {
    private Recipe recipe = new Recipe("640355", "image", "title", 2, 40);

    @Test
    public void testGetSteps() {
        String steps = "{0=Add the cereal, cranberries and white chocolate chips into a large bowl., "
                + "1=In a large micro-wave bowl, add the marshmallows and butter. Microwave on high for 3 minutes. Stir the mixture after two minutes. Stir in the vanilla at the end of the cooking time., "
                + "2=Pour the melted marshmallows into the large bowl containing the cereal mixture., "
                + "3=Stir with a wooden spoon to combine., "
                + "4=Transfer to a 9\"x13\" buttered rectangular pan and pat down evenly with your hands or a wooden spoon., "
                + "5=Cut into squares and serve.}";
        assertEquals(steps, recipe.getSteps().toString());
    }

    @Test
    public void testGetIngredientsList_UsMeasureSystem() throws IOException {
        MeasureSystem currentMeasureSystem = MeasureSystem.getMeasureSystem();

        MeasureSystem.setMeasureSystem(MeasureSystem.US);
        String ingredientsListUs = "[6.0 cups Rice Krispies cereal, 2.0 cups dried cranberries, 1.0 cup white chocolate chips, 0.3 cups butter, 5.0 cups marshmallows or 40 regular marshmallows, 1.0 tsp vanilla]";
        assertEquals(ingredientsListUs, recipe.getIngredients());

        MeasureSystem.setMeasureSystem(currentMeasureSystem);
    }

    @Test
    public void testGetIngredientsList_MetricMeasureSystem() throws IOException {
        MeasureSystem currentMeasureSystem = MeasureSystem.getMeasureSystem();

        MeasureSystem.setMeasureSystem(MeasureSystem.METRIC);
        String ingredientsListMetric = "[1.4 l Rice Krispies cereal, 473.2 ml dried cranberries, 236.6 ml white chocolate chips, 59.1 ml butter, 1.2 l marshmallows or 40 regular marshmallows, 1.0 tsp vanilla]";
        assertEquals(ingredientsListMetric, recipe.getIngredients());

        MeasureSystem.setMeasureSystem(currentMeasureSystem);
    }

}
