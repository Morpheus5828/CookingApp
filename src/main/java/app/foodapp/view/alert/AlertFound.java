package app.foodapp.view.alert;

import javafx.scene.control.Alert;

public class AlertFound {
    private static Alert alert = new Alert(Alert.AlertType.INFORMATION);;

    public static void connexionFailed() {
        System.out.println("\n" + "⚠ Check your internet connection and please try again" + "\n");
    }

    public static void invalidCharacter() {
        System.out.println("\n" + "⚠ Invalid character input, please try again !" + "\n");
    }

    public static void invalidNode() {
        System.out.println("\n" + "⚠ Node doesn't exist !" + "\n");
    }

    public static void usernameAlreadyExist() {
        alert.setContentText("Username already exist, please try again");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void usernameNotWrite() {
        alert.setContentText("You forget to enter username, please try again");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void passwordNotWrite() {
        alert.setContentText("You forget to enter password, please try again");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void usernameNotExist() {
        alert.setContentText("Username not exist, please try again");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void passwordNotExist() {
        alert.setContentText("Password doesn't match, please try again");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
