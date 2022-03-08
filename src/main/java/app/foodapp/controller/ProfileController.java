package app.foodapp.controller;

import app.foodapp.model.dataManipulation.MeasureSystemName;
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
            MeasureSystemName measureSystem = MeasureSystemName.getMeasureSystem();
            if (measureSystem == MeasureSystemName.US)
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
        MeasureSystemName.setMeasureSystem(MeasureSystemName.US);
        measureSystemLabel.setText("US");
    }

    public void setMeasureSystemToMetric() {
        MeasureSystemName.setMeasureSystem(MeasureSystemName.METRIC);
        measureSystemLabel.setText("METRIC");
    }

    @Override
    public EventHandler<ActionEvent> removeRecipeFromFavorites(Button button, Recipe recipe, StackPane stackPane, HBox box) {
        return null;
    }
}
