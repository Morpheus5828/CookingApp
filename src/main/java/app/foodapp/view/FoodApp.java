package app.foodapp.view;

import app.foodapp.controller.login.SignUp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FoodApp extends Application {

    @Override
    public void start(Stage loginStage) throws Exception {
       try {
           Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/register/login.fxml"));
           loginStage.setTitle("Cooking App");
           loginStage.setScene(new Scene(root));
           loginStage.show();

       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public static void main(String[] args) { launch(args); }
}
