package controller.DataManipulation;



import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataExtraction {
    // We lit attributes we need to display
    private Object ingredientTitle;
    private Object ingredientImage;
    private Object usedIngredientCount;
    private Object missedIngredient;
    private Object numberOfLikes;
    private Gson gson;

    public DataExtraction(String dataIngredientText) {


        JSONArray jsonArray = new JSONArray(dataIngredientText);
        JSONObject jobj = jsonArray.getJSONObject(0);
//        String id = jobj.getString("0");
        System.out.println(jobj);


    }
}
