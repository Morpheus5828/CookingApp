package app.foodapp.view;

import app.foodapp.controller.checkInternetConnexion.InternetConnexion;
import app.foodapp.model.node.Pane;
import app.foodapp.model.node.login.LoginPage;

import java.io.IOException;

public class FoodAppCLI {

    public static void main(String[] args) throws IOException {
        /*if (InternetConnexion.checkStatus())
            new Pane();*/
        LoginPage test = new LoginPage();
        test.launch();
    }
}
