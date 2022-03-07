package app.foodapp.controller;

import app.foodapp.model.dataManipulation.MeasureSystem;
import app.foodapp.model.recipe.Recipe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class ProfileController extends MainController {
    @FXML private Label usernameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label measureSystemLabel;

    public static String USERNAME;
    public static String PASSWORD;

    public void setProfile() {
        try {
            MeasureSystem measureSystem = MeasureSystem.getMeasureSystem();
            if (measureSystem == MeasureSystem.US)
                measureSystemLabel.setText("US");
            else
                measureSystemLabel.setText("METRIC");
        }
        catch (IOException e) {
            measureSystemLabel.setText("NO SYSTEM FOUND");
        } finally {
            usernameLabel.setText(USERNAME);
            passwordLabel.setText(PASSWORD);
        }
    }

    public void setMeasureSystemToUs() {
        MeasureSystem.setMeasureSystem(MeasureSystem.US);
        measureSystemLabel.setText("US");
    }

    public void setMeasureSystemToMetric() {
        MeasureSystem.setMeasureSystem(MeasureSystem.METRIC);
        measureSystemLabel.setText("METRIC");
    }

    @Override
    public EventHandler<ActionEvent> removeRecipeFromFavorites(Button button, Recipe recipe, StackPane stackPane, HBox box) {
        return null;
    }
}
