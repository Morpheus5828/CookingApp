package app.foodapp.view;

import app.foodapp.controller.checkInternetConnexion.InternetConnexion;
import app.foodapp.model.node.Pane;
import app.foodapp.model.node.login.LoginPage;
import app.foodapp.model.node.login.SignUp;

import java.io.IOException;

public class FoodAppCLI {

    public static void main(String[] args) throws IOException {
        /*if (InternetConnexion.checkStatus())
            new Pane();*/
        SignUp test = new SignUp();
        test.launch();
    }
}
