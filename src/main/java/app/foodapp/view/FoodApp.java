package app.foodapp.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FoodApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       try {
           Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
           stage.setTitle("Cooking App");
           Scene scene = new Scene(root);
           String css = this.getClass().getResource("/app/foodapp/view/globalStylesheet.css").toExternalForm();
           scene.getStylesheets().add(css);
           stage.setScene(scene);
           stage.show();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public static void main(String[] args) { launch(args); }
}
