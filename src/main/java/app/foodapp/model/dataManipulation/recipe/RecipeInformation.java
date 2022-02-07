package app.foodapp.model.dataManipulation.recipe;

import app.foodapp.controller.apiHttpRequest.MainInstructionsRequest;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeInformation {

    // We lit attributes that we need to display
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


    public RecipeInformation(String id) {
        MainInstructionsRequest request = new MainInstructionsRequest(id);
        try {
            // Conversion: JSONObject to Map
            gsonInstance = new Gson();
            this.jsonFile = gsonInstance.fromJson(request.getResponseFromApi(), Map.class);

            this.title = jsonFile.get("title").toString();
            this.image = jsonFile.get("image").toString();
            this.cookingTime = (double) jsonFile.get("readyInMinutes");
            this.serving = (double) jsonFile.get("servings");


        } catch (Exception e) {
            // Sometimes value's properties are null
            e.printStackTrace();
        }
    }

    public void getIngredients() {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Map<Integer, String> getStepRecipeInformation() {
        Map<Integer, String> stepInstruction = new HashMap<>();

        try {
           // Conversion to use key-value system
           List listOfInstructions = (List) this.jsonFile.get("analyzedInstructions");
           if(convertListToMap(listOfInstructions) == null)
               return null;
           Map instructions = convertListToMap(listOfInstructions);

           List listOfSteps = (List) instructions.get("steps");
           JSONArray jsonArray1 = new JSONArray(listOfSteps);

           // Add step with number associated
           for(int index = 0; index < jsonArray1.length(); index++) {
              JSONObject jsonObject1 = jsonArray1.getJSONObject(index);
               Map mapDeTest1 = gsonInstance.fromJson(String.valueOf(jsonObject1), Map.class);

               stepInstruction.put(index, (String) mapDeTest1.get("step"));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
        return stepInstruction;
    }

    public ArrayList<Map<String, String>> getIngredientsInformation() {
        ArrayList<Map<String, String>> ingredientsListInformation = new ArrayList<>();
        try {
            List listOfIngredients = (List) this.jsonFile.get("extendedIngredients");
            JSONArray ingredients = new JSONArray(listOfIngredients);

            for (int index = 0; index < ingredients.length(); index++) {
                Map<String, String> ingredientsInformation = new HashMap<>();
                Map information = gsonInstance.fromJson(String.valueOf(ingredients.getJSONObject(index)), Map.class);
                ingredientsInformation.put("fullDescription", (String) information.get("original"));
                ingredientsInformation.put("description", (String) information.get("originalName"));
                ingredientsInformation.put("unit", (String) information.get("unit"));

                Map measure = (Map) information.get("measures");
                Map usMeasure = (Map) measure.get("us");
                Map metricMeasure = (Map) measure.get("metric");

                ingredientsInformation.put("usAmount", String.valueOf(usMeasure.get("amount")));
                ingredientsInformation.put("usUnit", (String) usMeasure.get("unitShort"));
                ingredientsInformation.put("metricAmount", String.valueOf(metricMeasure.get("amount")));
                ingredientsInformation.put("metricUnit", (String) metricMeasure.get("unitShort"));

                ingredientsListInformation.add(ingredientsInformation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingredientsListInformation;
    }

    private Map convertListToMap(List list) {
        // Conversion JSONArray object to --> JSONObjet
        JSONArray jsonArray = new JSONArray(list);
        // First index is 0

        if(jsonArray.isEmpty())
            return null;
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        return this.gsonInstance.fromJson(String.valueOf(jsonObject), Map.class);
    }


}
















