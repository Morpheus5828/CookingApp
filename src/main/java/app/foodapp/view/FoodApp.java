package app.foodapp.view;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FoodApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        primaryStage.setTitle("App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
         */
        Recipe recipe = new Recipe("622561", null, 0, 0);
        System.out.println(recipe.getSteps());
    }

    public static void main(String[] args) { launch(args); }
}
