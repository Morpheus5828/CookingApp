package app.foodapp.model.dataManipulation.recipe;

import app.foodapp.controller.*;
import app.foodapp.controller.apiHttpRequest.MainInstructionsRequest;
import app.foodapp.controller.apiHttpRequest.SearchRecipesByIngredients;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeInformation {

    private ArrayList<Recipe> listOfRecipe;
    private String id;
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

    public RecipeInformation(ArrayList<String> listOfIngredient) {
        SearchRecipesByIngredients ingredientRequest = new SearchRecipesByIngredients(listOfIngredient);
        this.listOfRecipe = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(ingredientRequest.getResponseFromApi());
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                this.id = jsonObject.get("id").toString();
                this.title = jsonObject.get("title").toString();
                this.image = jsonObject.get("image").toString();
                getCookingTime(this.id);
                getServingValue(this.id);
                this.listOfRecipe.add(new Recipe(this.title, this.serving, this.cookingTime));
            }



        } catch (Exception e) {
            // Sometimes value's properties are null
            e.printStackTrace();
        }
    }
    //TODO A finir 
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

            //TODO To be continued

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

    public void display() {
        for(int i = 0; i < this.listOfRecipe.size(); i++) {
            System.out.println(
                    "Recipe: " + this.listOfRecipe.get(i).getTitle() + "\n" +
                    "Cooking Time: " + this.listOfRecipe.get(i).getCookingTime() + "\n" +
                    "Serving: " + this.listOfRecipe.get(i).getServings() + "\n" +
                    "-----------------------------------------------" + "\n"
            );
        }

    }

    private void getCookingTime(String idString) {
        RecipeInformation instance = new RecipeInformation(idString);
        this.cookingTime = instance.cookingTime;

    }

    private void getServingValue(String idString) {
        RecipeInformation instance = new RecipeInformation(idString);
        this.serving = instance.serving;
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
















