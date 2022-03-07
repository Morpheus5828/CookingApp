package app.foodapp.model.node.login;

import app.foodapp.model.alert.AlertFound;
import app.foodapp.model.node.Favorite;
import app.foodapp.model.node.Pane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class SignUp {
    @FXML private ComboBox regimeChoice;
    @FXML private TextField username;
    @FXML private PasswordField password;
    private String userNameForCli;
    private String passwordForCli;
    private String recipeId = null;
    private BufferedReader reader;
    private final int USERNAME = 0;
    private String regime = "None";

    public SignUp() {}

    public void isCheck(javafx.event.ActionEvent actionEvent) {
        if(regimeChoice.isDisable()) {
            regimeChoice.setDisable(false);
            regimeChoice.setOpacity(1);
            addRegimeString();

        } else {
            regimeChoice.setDisable(true);
            regimeChoice.setOpacity(0.25);
        }
    }

    private void addRegimeString() {
        ObservableList<String> list = FXCollections.observableArrayList(
                "vegetarian",
                "vegan"
        );
        regimeChoice.setItems(list);
    }

    public void regimeSelected(javafx.event.ActionEvent actionEvent) {
        String regimeChoice = this.regimeChoice.getSelectionModel().getSelectedItem().toString();
        this.regime = regimeChoice;
    }

    public boolean createAnAccount() throws IOException {
        String content = username.getText() + "," + password.getText() + "," + regime +",\n";
        if(userAlreadyExist(content)) {
            AlertFound.usernameAlreadyExist();
            return false;
        }

        if(username.getText().isEmpty()) {
            AlertFound.usernameFieldEmpty();
            return false;
        }

        if(password.getText().isEmpty()) {
            AlertFound.passwordFieldEmpty();
            return false;
        }

        else {
            File file = new File("userInformation.txt");
            if(!file.exists())
                file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            fw.append(content);
            fw.close();
            launchCookingApp();
            Favorite.username = username.getText();
            return true;
        }
    }

    public Boolean userAlreadyExist(String content) throws IOException {
        try {
            reader = new BufferedReader(new FileReader("userInformation.txt"));
            String line;
            String[] tabOfRow = new String[0];
            while((line = reader.readLine()) != null)
                tabOfRow = line.split(",");
            if (tabOfRow[USERNAME].equals(userNameForCli))
                return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void launchCookingApp() throws IOException {
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        loginStage.setTitle("Cooking App");
        loginStage.setScene(new Scene(root));
        loginStage.show();
    }

    // method just for Cli run
    public void launch() throws IOException {
        System.out.println(displayWelcomeText());
        setUserNameForCli();
        setPasswordForCli();
        setRegime();
        userRegister(userNameForCli, passwordForCli, regime);
    }

    public String displayWelcomeText() {
        return "\t-> Please enter information to SignUp:\n";
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

    public void setRegime() {
        System.out.print(
                "Regime:\n" +
                "\t1. Vegan \n" +
                "\t2. Vegetarian \n" +
                "\t3. No regime \n" +
                "\t -> Your choice: "
        );
        Scanner sc = new Scanner(System.in);
        switch (sc.nextInt()) {
            case 1 -> regime = "Vegan";
            case 2 -> regime = "Vegetarian";
            case 3 -> regime = "None";
            default -> {
                AlertFound.invalidCharacter();
                setRegime();
            }
        }
    }

    public void userRegister(String userNameForCli, String passwordForCLi, String userRegime) throws IOException {
        try {
            String content = userNameForCli + "," + passwordForCLi + "," + userRegime + ",\n";
            if (userAlreadyExist(content)) {
                System.out.println("\n\t⚠ User already exist please choose an other one\n");
                this.launch();
            }

            else {
                File file = new File("userInformation.txt");
                if(!file.exists())
                    file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                fw.append(content);
                createFavoriteFile(userNameForCli);
                System.out.println("\nUser has been add successfully\n");
                fw.close();
                Favorite.username = userNameForCli;
                Pane.loginSuccessfull = true;
            }


        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    public void createFavoriteFile(String username) throws IOException {
        File file = new File("favorite.txt");
        if(!file.exists())
            file.createNewFile();
        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        Map<String, String> favorite = new HashMap<>();
        favorite.put(username, "");
        fw.append(favorite.toString() + ",\n");
        fw.close();
    }
}
