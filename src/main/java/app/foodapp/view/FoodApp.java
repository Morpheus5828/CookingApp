package app.foodapp.view;

import app.foodapp.controller.ResearchController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FoodApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
           Parent root = loader.load();
           ResearchController researchController = loader.getController();
           researchController.welcomePage();

           stage.setTitle("Cooking App");
           Scene scene = new Scene(root);

           scene.getStylesheets().add(this.getClass().getResource("/app/foodapp/view/stylesheet/globalStylesheet.css").toExternalForm());
           scene.getStylesheets().add(this.getClass().getResource("/app/foodapp/view/stylesheet/recipeListDisplayStylesheet.css").toExternalForm());
           stage.setScene(scene);
           stage.show();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public static void main(String[] args) { launch(args); }
}
