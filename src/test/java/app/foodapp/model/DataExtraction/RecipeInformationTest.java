package app.foodapp.model.DataExtraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import app.foodapp.model.dataManipulation.recipe.RecipeInformation;
import org.junit.jupiter.api.Test;


public class RecipeInformationTest {

    @Test
    public void getStepRecipeInformation() {
        RecipeInformation test = new RecipeInformation("662458");
        RecipeInformation test1 = new RecipeInformation("673463"); // recipe without step instruction
        String result = "{0=n a small bowl, sift together the flours, flax seed meal, baking soda, salt, ginger, cinnamon, cloves, nutmeg and xanthan gum, 1=In a standing mixer with the paddle attachment, beat butter and sugar until light and fluffy., 2=Add molasses and maple syrup and beat to combine., 3=Add egg and beat to combine., 4=Slowly add in flour until combined., 5=Divide in half, flatten into disks and individually wrap in plastic.  Set in refrigerator at least two hours or (preferably) overnight., 6=When ready to bake, preheat oven to 35, 7=Line 4 cookie sheets with parchment paper., 8=Flour work surface with rice flour and roll disk to 1/4 inch thick, rotating dough regularly to prevent sticking (re-flour board as necessary)., 9=Cut into circles about 1 3/4 inch in diameter., 10=Bake for 12 minutes or until still slightly soft on top.  If you prefer crispy cookies, bake for 16 minutes., 11=Remove from oven and flip upside-down on a cool cookie sheet or flat work surface.  With the bottom of a shot glass or a small spoon, press a small circle into the bottom of each cookie.  Allow to cool completely., 12=When cool, fill 1 cookie impression with about a teaspoon of your favorite jam (mine was Blueberry Ginger Lime Jam) and use a second cookie to sandwich, repeating until all are done!}";
        assertEquals(result, test.getStepRecipeInformation().toString());
        assertNull(test1.getStepRecipeInformation());
    }

    @Test
    public void getIngredientsInformation() {
        RecipeInformation recipeInformation = new RecipeInformation("640355");
        String ingredientsInformation = "[{usUnit=cups, unit=cups, usAmount=6.0, description=Rice Krispies cereal, metricAmount=1.4, fullDescription=6 cups Rice Krispies cereal, metricUnit=l}, "
                + "{usUnit=cups, unit=cups, usAmount=2.0, description=dried cranberries, metricAmount=473.2, fullDescription=2 cups dried cranberries, metricUnit=ml}, "
                + "{usUnit=cup, unit=cup, usAmount=1.0, description=white chocolate chips, metricAmount=236.6, fullDescription=1 cup white chocolate chips, metricUnit=ml}, "
                + "{usUnit=cups, unit=cup, usAmount=0.3, description=butter, metricAmount=59.1, fullDescription=1/4 cup butter, metricUnit=ml}, "
                + "{usUnit=cups, unit=cups, usAmount=5.0, description=marshmallows or 40 regular marshmallows, metricAmount=1.2, fullDescription=5 cups small marshmallows or 40 regular marshmallows, metricUnit=l}, "
                + "{usUnit=tsp, unit=teaspoon, usAmount=1.0, description=vanilla, metricAmount=1.0, fullDescription=1 teaspoon vanilla, metricUnit=tsp}]";

        assertEquals(ingredientsInformation, recipeInformation.getIngredientsInformation().toString());
    }
}
