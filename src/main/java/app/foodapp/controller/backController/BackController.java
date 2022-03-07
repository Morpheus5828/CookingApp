package app.foodapp.controller.backController;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import javafx.event.ActionEvent;

import java.util.ArrayList;

public interface BackController {
    public void setPageIndex(final int pageIndex);
    public void goBack(final ActionEvent actionEvent);
}
