package app.foodapp.model.node.login;

import app.foodapp.controller.ProfileController;
import app.foodapp.controller.ResearchController;
import app.foodapp.model.alert.AlertFound;
import app.foodapp.model.node.Pane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public final class LoginPage {
    @FXML private TextField usernameEntered;
    @FXML private PasswordField passwordEntered;
    private BufferedReader reader;
    private String userNameForCli;
    private String passwordForCli;
    private final int USERNAME = 0;
    private final int PASSWORD = 1;

    public LoginPage() {}

    @FXML
    public void checkUserLogin(ActionEvent actionEvent) throws IOException {
        try {
            reader = new BufferedReader(new FileReader("userInformation.txt"));
            String line;
            String[] tabOfRow = new String[0];
            while((line = reader.readLine()) != null)
                tabOfRow = line.split(",");
            if (tabOfRow[USERNAME].equals(usernameEntered.getText()) && tabOfRow[PASSWORD].equals(passwordEntered.getText())) {
                loginAccepted(actionEvent);
                ProfileController.USERNAME = usernameEntered.getText();
                ProfileController.PASSWORD = passwordEntered.getText();
                //Favorite.username = usernameEntered.getText();
            }

            else AlertFound.loginFieldNotExist();
        }

        catch (NullPointerException e) {
            AlertFound.loginFieldNotExist();
        }
    }


    @FXML
    private void creationOfAnAccount(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/register/sign_up.fxml"));
        Stage sgnUpStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        sgnUpStage.setScene(scene);
        sgnUpStage.show();

    }

    private void loginAccepted(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
            Parent root = loader.load();
            ResearchController researchController = loader.getController();
            researchController.welcomePage();
            researchController.setRecipeResearch();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            scene.getStylesheets().add(this.getClass().getResource("/app/foodapp/view/stylesheet/globalStylesheet.css").toExternalForm());
            scene.getStylesheets().add(this.getClass().getResource("/app/foodapp/view/stylesheet/recipeListDisplayStylesheet.css").toExternalForm());

            stage.setScene(scene);
            stage.setX(0);
            stage.setY(0);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // method just for CLI
    public void launch() throws IOException {
        System.out.println(displayWelcomeText());
        setUserNameForCli();
        setPasswordForCli();
        checkUserLoginForCli();
    }

    public String displayWelcomeText() {
        return "\n\t-> Please enter information to LoginIn:\n";
    }

    public void setUserNameForCli() {
        System.out.print("Username: ");
        Scanner sc = new Scanner(System.in);
        this.userNameForCli = sc.next();
    }

    public void setPasswordForCli() {
        System.out.print("Password: ");
        Scanner sc = new Scanner(System.in);
        this.passwordForCli = sc.next();
    }

    public void checkUserLoginForCli() throws IOException {
        try {
            reader = new BufferedReader(new FileReader("userInformation.txt"));
            String line;
            String[] tabOfRow = new String[0];
            while((line = reader.readLine()) != null)
                tabOfRow = line.split(",");
            if (tabOfRow[USERNAME].equals(userNameForCli) && tabOfRow[PASSWORD].equals(passwordForCli)) {
                Pane.loginSuccessfull = true;
                //Favorite.username = userNameForCli;
            }

            else {
                System.out.println("\n\t⚠ Username or password doesn't exist\n");
                this.launch();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
