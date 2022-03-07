package app.foodapp.model.recipe;

import app.foodapp.controller.dataRequest.MainInstructionsRequest;
import app.foodapp.controller.dataRequest.SearchRecipesByIngredients;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.apache.commons.math3.util.Precision;

public class RecipeInformation {

    public static ArrayList<Recipe> listOfRecipe;
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
    private Recipe recipeForFavorite;


    public RecipeInformation(String id) {
        MainInstructionsRequest request = new MainInstructionsRequest(id);
        try {
            // Conversion: JSONObject to Map
            gsonInstance = new Gson();
            this.jsonFile = gsonInstance.fromJson(request.getResponseFromApi(), Map.class);
            this.id = jsonFile.get("id").toString();
            this.title = jsonFile.get("title").toString();
            this.image = jsonFile.get("image").toString();
            this.cookingTime = (double) jsonFile.get("readyInMinutes");
            this.serving = (double) jsonFile.get("servings");
            this.recipeForFavorite = new Recipe(this.id, this.title, this.serving, this.cookingTime);

        } catch (Exception e) {
            // Sometimes value's properties are null
            e.printStackTrace();
        }
    }

    public RecipeInformation(ArrayList<String> listOfIngredient) {
        SearchRecipesByIngredients ingredientRequest = new SearchRecipesByIngredients(listOfIngredient);

        listOfRecipe = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(ingredientRequest.getResponseFromApi());
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                this.id = jsonObject.get("id").toString();
                this.title = jsonObject.get("title").toString();
                this.image = jsonObject.get("image").toString();
                getCookingTime(this.id);
                getServingValue(this.id);
                listOfRecipe.add(new Recipe(this.id, this.title, this.serving, this.cookingTime));
            }


        } catch (Exception e) {
            // Sometimes value's properties are null
            e.printStackTrace();
        }
    }

    public String getIngredients() {
        String result = "";
        try {
            // Conversion: List to JSONArray
            List listOfIngredientsElements = (List) jsonFile.get("extendedIngredients");
            JSONArray jsonArray = new JSONArray(listOfIngredientsElements);

            // Extraction value's extendedIngredients and creation of display result
            for(int index = 0; index < jsonArray.length(); index++) {
                //TODO a finir c'est pour la troncature double scale = Math.pow(10, 3);

                JSONObject jsonObject = jsonArray.getJSONObject(index);
                Map mapDeTest = gsonInstance.fromJson(String.valueOf(jsonObject), Map.class);
                this.ingredientName = mapDeTest.get("name").toString();
                this.originalName = mapDeTest.get("originalName").toString();
                this.unitValue = mapDeTest.get("unit").toString();
                this.amountValue = (double) mapDeTest.get("amount");

                result += this.amountValue + "  " + this.ingredientName + "\n";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<Integer, String> getStepRecipeInformation() throws NullPointerException {
        Map<Integer, String> stepInstruction = new HashMap<>();

        try {
            // Conversion to use key-value system
            List listOfInstructions = (List) this.jsonFile.get("analyzedInstructions");
            if (convertListToMap(listOfInstructions) == null)
                return null;
            Map instructions = convertListToMap(listOfInstructions);

            List listOfSteps = (List) instructions.get("steps");
            JSONArray jsonArray1 = new JSONArray(listOfSteps);

            // Add step with number associated
            for (int index = 0; index < jsonArray1.length(); index++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(index);
                Map mapDeTest1 = gsonInstance.fromJson(String.valueOf(jsonObject1), Map.class);

                stepInstruction.put(index, (String) mapDeTest1.get("step"));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return stepInstruction;
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

    public String displaySimple() {
        return this.recipeForFavorite.displaySimpleCharacteristics();
    }

    public String display() {
        String result = "";
        for(int i = 0; i < listOfRecipe.size(); i++) {
            result +=
                "Menu number: " + i + ": " + listOfRecipe.get(i).getTitle() + "\n" +
                "Cooking Time: " + listOfRecipe.get(i).getCookingTime() + "\n" +
                "Serving: " + listOfRecipe.get(i).getServings() + " people(s)" + "\n" +
                "id: (information for programmer) " + listOfRecipe.get(i).getId() + "\n" +
                "-----------------------------------------------" + "\n";
        }
        return result;
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

                double usAmount = (Math.round((double) usMeasure.get("amount") *  10.0)) / 10.0;
                double metricAmount = (Math.round((double) metricMeasure.get("amount") *  10.0)) / 10.0;

                ingredientsInformation.put("usAmount", String.valueOf(usAmount));
                ingredientsInformation.put("metricAmount", String.valueOf(metricAmount));

                ingredientsInformation.put("usUnit", (String) usMeasure.get("unitShort"));
                ingredientsInformation.put("metricUnit", (String) metricMeasure.get("unitShort"));

                ingredientsListInformation.add(ingredientsInformation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingredientsListInformation;
    }
}
















