package app.foodapp.controller.login;

import app.foodapp.controller.MainController;
import app.foodapp.controller.ResearchController;
import app.foodapp.model.alert.AlertFound;
import app.foodapp.model.dataManipulation.recipe.Recipe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.*;

public class LoginPage {
    @FXML private TextField usernameEntered;
    @FXML private PasswordField passwordEntered;
    private final BufferedReader reader;
    private boolean isTheSameUsername = false;
    private boolean isTheSamePassword = false;
    private final boolean condition = true;
    private String line = "";

    public LoginPage() throws IOException {
        this.reader = new BufferedReader(new FileReader("userInformation.txt"));
    }
    public void checkUserLogin(ActionEvent actionEvent) throws IOException {
        try {
            while ((line = reader.readLine()) != null) {
                int counter = 0;
                String[] tabOfRow = line.split(",");

                for(String index : tabOfRow) {
                    if(counter != 2) {
                        if (index.equals(usernameEntered.getText()))
                            isTheSameUsername = true;
                        if(index.equals(passwordEntered.getText()))
                            isTheSamePassword = true;
                    }
                    counter += 1;
                }
            }
            checkAttributionProperties();
            if(isTheSamePassword && isTheSameUsername)
                // Open next stage
                loginAccepted(actionEvent);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void creationOfAnAccount(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/register/sign_up.fxml"));
        loginStage.setTitle("Cooking App");
        loginStage.setScene(new Scene(root));
        loginStage.show();
    }

    private void loginAccepted(ActionEvent event) throws IOException {
        /*Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        loginStage.setTitle("Cooking App");
        loginStage.setScene(new Scene(root));
        loginStage.show();*/

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        Parent root = loader.load();
        ResearchController researchController = loader.getController();
        researchController.welcomePage();
        researchController.setRecipeResearch();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(this.getClass().getResource("/app/foodapp/view/stylesheet/globalStylesheet.css").toExternalForm());
        scene.getStylesheets().add(this.getClass().getResource("/app/foodapp/view/stylesheet/recipeListDisplayStylesheet.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    private void checkAttributionProperties() throws IOException {
        if(!isTheSameUsername)
            AlertFound.usernameNotExist();
        if(!isTheSamePassword)
            AlertFound.passwordNotExist();
    }
}
