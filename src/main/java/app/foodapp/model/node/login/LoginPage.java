package app.foodapp.model.node.login;

import app.foodapp.model.alert.AlertFound;
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

    public LoginPage() {}

    public void checkUserLogin(ActionEvent actionEvent) throws IOException {
        try {
            reader = new BufferedReader(new FileReader("userInformation.txt"));
            boolean isTheSameUsername = false;
            boolean isTheSamePassword = false;
            String line = "";
            while ((line = reader.readLine()) != null) {
                int counter = 0;
                String[] tabOfRow = line.split(",");

                for (String index : tabOfRow) {
                    if (counter != 2) {
                        if (index.equals(usernameEntered.getText()))
                            isTheSameUsername = true;
                        if (index.equals(passwordEntered.getText()))
                            isTheSamePassword = true;
                    }
                    counter += 1;
                }
            }

            if(!isTheSameUsername)
                AlertFound.usernameNotExist();
            if(!isTheSamePassword)
                AlertFound.passwordNotExist();
            if(isTheSamePassword && isTheSameUsername)
                // Open next stage
                loginAccepted();
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

    public String displayWelcomeText() {
        return "\t-> Please enter information to LoginIn:\n";
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

    public void checkUserLogin() {
        try {
            displayWelcomeText();
            setUserNameForCli();
            setPasswordForCli();
            reader = new BufferedReader(new FileReader("userInformation.txt"));
            boolean isTheSameUsername = false;
            boolean isTheSamePassword = false;
            String line = "";
            while ((line = reader.readLine()) != null) {
                int counter = 0;
                String[] tabOfRow = line.split(",");

                for (String index : tabOfRow) {
                    if (counter != 2) {
                        if (index.equals(userNameForCli))
                            isTheSameUsername = true;
                        if (index.equals(passwordForCli))
                            isTheSamePassword = true;
                    }
                    counter += 1;
                }
            }

            if(!isTheSameUsername)
                System.out.println("Username not exist, please create an account\n");
            if(!isTheSamePassword)
                System.out.println("Password doesn't match, please try again\n");
            if(isTheSamePassword && isTheSameUsername)
                System.out.println("Login validated\n");
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }


    // this one is just for unit test
    public void checkUserLogin(String username, String password) throws IOException {
        try {
            reader = new BufferedReader(new FileReader("userInformation.txt"));
            boolean isTheSameUsername = false;
            boolean isTheSamePassword = false;
            String line = "";
            while ((line = reader.readLine()) != null) {
                int counter = 0;
                String[] tabOfRow = line.split(",");

                for (String index : tabOfRow) {
                    if (counter != 2) {
                        if (index.equals(username))
                            isTheSameUsername = true;
                        if (index.equals(password))
                            isTheSamePassword = true;
                    }
                    counter += 1;
                }
            }

            if(!isTheSameUsername)
                System.out.println("Username not exist, please create an account");
            if(!isTheSamePassword)
                System.out.println("Password doesn't match, please try again");
            if(isTheSamePassword && isTheSameUsername)
                System.out.println("Login validated");
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
