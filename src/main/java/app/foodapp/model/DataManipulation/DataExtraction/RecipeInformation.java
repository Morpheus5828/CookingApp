package app.foodapp.model.DataManipulation.DataExtraction;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
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
    private String unitValue;
    private double amountValue;
    private Gson gsonInstance;
    private Map jsonFile;


    public RecipeInformation(String dataIngredientText) {
        try {
            // Conversion: JSONObject to Map
            gsonInstance = new Gson();
            this.jsonFile = gsonInstance.fromJson(dataIngredientText, Map.class);

            this.title = jsonFile.get("title").toString();
            //this.image = jsonFile.get("image").toString();
            this.cookingTime = (double) jsonFile.get("readyInMinutes");
            this.serving = (double) jsonFile.get("servings");


        } catch (Exception e) {
            // Sometimes value's properties are null
            e.printStackTrace();
        }
    }

    public void getIngredients() {
        // Conversion: List to JSONArray
        List listOfIngredientsElements = (List) jsonFile.get("extendedIngredients");
        JSONArray jsonArray = new JSONArray(listOfIngredientsElements);

        // Extraction value's extendedIngredients
        for(int index = 0; index < jsonArray.length(); index++) {
            JSONObject jsonObject = jsonArray.getJSONObject(index);
            Map mapDeTest = gsonInstance.fromJson(String.valueOf(jsonObject), Map.class);
            this.ingredientName = mapDeTest.get("name").toString();
            this.originalName = mapDeTest.get("originalName").toString();
            this.unitValue = mapDeTest.get("unit").toString();
            this.amountValue = (double) mapDeTest.get("amount");
        }

        // to be continued
    }

    public Map<Integer, String> getStepRecipeInformation() {
        // Conversion: List to JSONArray
        List listOfIngredientsElements = (List) this.jsonFile.get("analyzedInstructions");
        JSONArray jsonArray = new JSONArray(listOfIngredientsElements);
        Map<Integer, String> result = new HashMap<>();

        JSONObject jsonObject = jsonArray.getJSONObject(0);
        Map mapDeTest = gsonInstance.fromJson(String.valueOf(jsonObject), Map.class);

        List listOfSteps = (List) mapDeTest.get("steps");
        JSONArray jsonArray1 = new JSONArray(listOfSteps);
        Map mapDeTest1 = null;
        for(int index = 0; index < jsonArray1.length(); index++) {
            JSONObject jsonObject1 = jsonArray1.getJSONObject(index);
            mapDeTest1 = gsonInstance.fromJson(String.valueOf(jsonObject1), Map.class);
            result.put(index, (String) mapDeTest1.get("step"));
        }

        return result;



    }

}
















