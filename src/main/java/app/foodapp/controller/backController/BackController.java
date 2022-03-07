package app.foodapp.controller.backController;

import javafx.event.ActionEvent;


public interface BackController {
    void setPageIndex(final int pageIndex);
    void goBack(final ActionEvent actionEvent);
}
