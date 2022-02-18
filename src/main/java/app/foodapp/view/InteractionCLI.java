package app.foodapp.view;

import app.foodapp.model.node.Pane;
import app.foodapp.model.node.Welcome;

import javax.management.InstanceAlreadyExistsException;
import java.net.InetAddress;

public class InteractionCLI {
    public InteractionCLI()  {
       // Check internet connexion

        Pane pane = new Pane();
        pane.display();


    }


}
