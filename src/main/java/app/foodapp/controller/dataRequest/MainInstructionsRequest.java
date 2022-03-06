package app.foodapp.controller.dataRequest;

public class MainInstructionsRequest extends ApiDataRequest {

    public MainInstructionsRequest(String recipeId) {
        super();
        String rawRequest = "https://api.spoonacular.com/recipes/"
                + recipeId
                + "/information?analyzedInstructions"
                + "&number=4&apiKey=";

        checkForDataExtraction(rawRequest);
    }
}








