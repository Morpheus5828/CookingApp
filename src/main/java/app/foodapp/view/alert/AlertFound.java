package app.foodapp.view.alert;

public class AlertFound {


    public static void connexionFailed() {
        System.out.println("\n" + "⚠ Check your internet connection and please try again" + "\n");
    }

    public static void invalidCharacter() {
        System.out.println("\n" + "⚠ Invalid character input, please try again !" + "\n");
    }

    public static void invalidNode() {
        System.out.println("\n" + "⚠ Node doesn't exist !" + "\n");
    }


}
