package app.foodapp.view;

import app.foodapp.controller.login.SignUpController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FoodApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       try {
           Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/favorites.fxml"));
           stage.setTitle("Cooking App");
           stage.setScene(new Scene(root));
           stage.show();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public static void main(String[] args) { launch(args); }
}
