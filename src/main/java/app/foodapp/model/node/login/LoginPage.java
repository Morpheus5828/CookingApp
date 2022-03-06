package app.foodapp.model.node.login;

import app.foodapp.model.alert.AlertFound;
import app.foodapp.model.node.Pane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
            if (tabOfRow[USERNAME].equals(usernameEntered.getText()) && tabOfRow[PASSWORD].equals(passwordEntered.getText()))
                loginAccepted();
            else AlertFound.loginFieldNotExist();
        }

        catch (NullPointerException e) {
            AlertFound.loginFieldNotExist();
        }
    }

    public void checkUserLoginForCli() throws IOException {
        try {
            reader = new BufferedReader(new FileReader("userInformation.txt"));
            String line;
            String[] tabOfRow = new String[0];
            while((line = reader.readLine()) != null)
                tabOfRow = line.split(",");
            if (tabOfRow[USERNAME].equals(userNameForCli) && tabOfRow[PASSWORD].equals(passwordForCli))
                Pane.loginSuccessfull = true;

            else {
                System.out.println("\n\t⚠ Username or password doesn't exist\n");
                this.launch();
            }
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

    private void loginAccepted() throws IOException {
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        loginStage.setTitle("Cooking App");
        loginStage.setScene(new Scene(root));
        loginStage.show();
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
}
