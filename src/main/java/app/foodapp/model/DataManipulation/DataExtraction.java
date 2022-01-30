package app.foodapp.model.DataManipulation;



import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class DataExtraction {
    // We lit attributes we need to display
    private String title;
    private String image;
    private String usedIngredient;
    private int cookingTime;
    private int serving;
    private Gson gson;

    public DataExtraction(String dataIngredientText) {
        // JSON Array
        /*JSONArray jsonArray = new JSONArray(dataIngredientText);
        JSONObject jobj = jsonArray.getJSONObject(0);
        gson = new Gson();
        Map jsonFile = gson.fromJson(String.valueOf(jobj), Map.class);*/

        //JSON Object
        gson = new Gson();
        Map jsonFile = gson.fromJson(dataIngredientText, Map.class);

        // getTitle
        jsonFile.get("title");

        //getCookingTime
        jsonFile.get("readyInMinutes");

        //getPicture
        jsonFile.get("image");

        //getServingValue
        jsonFile.get("servings");

        //getIngredients
        List test = (List) jsonFile.get("extendedIngredients");
        JSONArray jsonArray = new JSONArray(test);
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jj = jsonArray.getJSONObject(i);
            Map mapDeTest = gson.fromJson(String.valueOf(jj), Map.class);
            System.out.println(mapDeTest.get("name"));
            System.out.println(mapDeTest.get("originalName"));
            System.out.println(mapDeTest.get("aisle"));
            System.out.println(mapDeTest.get("unit"));
// amount for us et unityshort et aussi en metrics


        }
    }
}
