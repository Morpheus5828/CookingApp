package app.foodapp.view;

import app.foodapp.controller.checkInternetConnexion.InternetConnexion;
import app.foodapp.model.node.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class InteractionCLI {
    public InteractionCLI() throws IOException {
        if(InternetConnexion.checkStatus())
            new Pane();
    }


}
