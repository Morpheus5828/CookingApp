package app.foodapp.model.DataManipulation.DataExtraction;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class RecipeInformation {

    // We lit attributes, we need to display
    private String title;
    private String image;
    private double cookingTime;
    private double serving;
    private String ingredientName;
    private String originalName;
    private double unitValue;
    private double amountValue;
    private Gson gsonInstance;


    public RecipeInformation(String dataIngredientText) {

        // Conversion: JSONObject to Map
        gsonInstance = new Gson();
        Map jsonFile = gsonInstance.fromJson(dataIngredientText, Map.class);

        this.title = jsonFile.get("title").toString();
        /*this.image = jsonFile.get("image").toString();
        this.cookingTime = (double) jsonFile.get("readyInMinutes");
        this.serving = (double) jsonFile.get("servings");

        // Conversion: List to JSONArray
        List listOfIngredientsElements = (List) jsonFile.get("extendedIngredients");
        JSONArray jsonArray = new JSONArray(listOfIngredientsElements);

        for(int index = 0; index < jsonArray.length(); index++) {
            JSONObject jsonObject = jsonArray.getJSONObject(index);
            Map mapDeTest = gsonInstance.fromJson(String.valueOf(jsonObject), Map.class);
            this.ingredientName = mapDeTest.get("name").toString();
            this.originalName = mapDeTest.get("originalName").toString();
            this.unitValue = (double) mapDeTest.get("unit");
            this.amountValue = (double) mapDeTest.get("mount");

            // Waiting Chloe to other value
        }*/



    }
}
