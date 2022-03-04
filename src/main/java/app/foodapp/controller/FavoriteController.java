package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.FavoriteStamp;
import app.foodapp.model.dataManipulation.recipe.Recipe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FavoriteController implements Initializable {

    @FXML private VBox recipeDisplay;
    @FXML private ImageView leftCornerLogo;


    private final FavoriteStamp favoriteNode = new FavoriteStamp();
    private final ArrayList<Button> removeFromFavoriteButtonList = new ArrayList<>();
    private final ArrayList<HBox> recipeBoxDisplayList = new ArrayList<>();
    private int pageIndex = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void goToMenu(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("/app/foodapp/view/stylesheet.css").toExternalForm();

            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToProfile(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/profile.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getFavoritesRecipes() {
        ArrayList<Recipe> favorites = favoriteNode.getFavorites();
        Image logo = new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/test3.png"));
        leftCornerLogo.setImage(logo);
        for (Recipe recipe : favorites) {
            HBox recipeBoxDisplay = new HBox();
            recipeBoxDisplay.getStyleClass().add("recipe-content");
            recipeBoxDisplayList.add(recipeBoxDisplay);

            Label title = createLabel(recipe.getTitle(), "recipe-title");
            Label cookingTime = createLabel((int) Math.round(recipe.getCookingTime()) + " min", "recipe-cookingTime");
            Label servings = createLabel((int) Math.round(recipe.getServings()) + " servings", "recipe-servings");

            ImageView removeFromFavoriteImage = new ImageView(new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/full-heart.png")));
            removeFromFavoriteImage.setPreserveRatio(true);
            removeFromFavoriteImage.setFitWidth(30);

            Button removeFromFavoriteButton = new Button("", removeFromFavoriteImage);
            removeFromFavoriteButton.getStyleClass().add("button-favorite");
            removeFromFavoriteButtonList.add(removeFromFavoriteButton);

            removeFromFavoriteButton.addEventFilter(MouseEvent.MOUSE_ENTERED, setBrokenHeartImage(removeFromFavoriteImage));
            removeFromFavoriteButton.addEventFilter(MouseEvent.MOUSE_EXITED, setFullHeartImage(removeFromFavoriteImage));
            removeFromFavoriteButton.setOnAction(removeRecipeFromFavorite(removeFromFavoriteButton));
            recipeBoxDisplay.addEventFilter(MouseEvent.MOUSE_CLICKED, getRecipeDetails(recipe));
            recipeBoxDisplay.addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEnteredRecipeBoxDisplay(recipeBoxDisplay));
            recipeBoxDisplay.addEventFilter(MouseEvent.MOUSE_EXITED, mouseExitedRecipeBoxDisplay(recipeBoxDisplay));

            recipeBoxDisplay.getChildren().add(title);
            recipeBoxDisplay.getChildren().add(cookingTime);
            recipeBoxDisplay.getChildren().add(servings);
            recipeBoxDisplay.getChildren().add(removeFromFavoriteButton);
        }
        if (favorites.isEmpty()) emptyFavoriteDisplay();
        else pageDisplay(1);
    }

    public Label createLabel(String content, String styleClass) {
        Label label = new Label(content);
        label.getStyleClass().add(styleClass);
        return label;
    }

    public EventHandler<MouseEvent> setBrokenHeartImage(ImageView imageView) {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                imageView.setImage(new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/broken-heart.png")));
            }
        };
    }

    public EventHandler<MouseEvent> setFullHeartImage(ImageView imageView) {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                imageView.setImage(new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/full-heart.png")));
            }
        };
    }

    public EventHandler<MouseEvent> setKnifeWithoutSauceImage(ImageView imageView) {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                imageView.setImage(new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/knifeWithoutSauce.png")));
            }
        };
    }

    public EventHandler<MouseEvent> setKnifeWithSauceImage(ImageView imageView) {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                imageView.setImage(new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/knifeWithSauce.png")));
            }
        };
    }

    public EventHandler<ActionEvent> removeRecipeFromFavorite(Button removeFromFavoriteButton) {
        return new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                int index = removeFromFavoriteButtonList.indexOf(removeFromFavoriteButton);

                recipeDisplay.getChildren().remove(recipeBoxDisplayList.get(index));
                favoriteNode.removeFromFavorite(favoriteNode.getFavorites().get(index));
                removeFromFavoriteButtonList.remove(index);
                recipeBoxDisplayList.remove(index);

                update();
            }
        };
    }

    public EventHandler<MouseEvent> getRecipeDetails(Recipe recipe) {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                goToRecipeDetails(event, recipe);
            }
        };
    }

    public void goToRecipeDetails(MouseEvent event, Recipe recipe) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/details.fxml"));
            Parent root = loader.load();
            DetailsController detailsController = loader.getController();
            detailsController.showDetails(recipe);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EventHandler<MouseEvent> mouseEnteredRecipeBoxDisplay(HBox recipeBoxDisplay) {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                Label cookingTime = (Label) recipeBoxDisplay.getChildren().get(1);
                Label servings = (Label) recipeBoxDisplay.getChildren().get(2);

                recipeBoxDisplay.getStyleClass().add("recipe-content-hover");
                cookingTime.getStyleClass().add("recipe-information-hover");
                servings.getStyleClass().add("recipe-information-hover");
            }
        };
    }

    public EventHandler<MouseEvent> mouseExitedRecipeBoxDisplay(HBox recipeBoxDisplay) {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                Label cookingTime = (Label) recipeBoxDisplay.getChildren().get(1);
                Label servings = (Label) recipeBoxDisplay.getChildren().get(2);

                recipeBoxDisplay.getStyleClass().remove("recipe-content-hover");
                cookingTime.getStyleClass().remove("recipe-information-hover");
                servings.getStyleClass().remove("recipe-information-hover");
            }
        };
    }

    public EventHandler<ActionEvent> goToPage(int pageIndex) {
        return new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                pageDisplay(pageIndex);
            }
        };
    }

    public void emptyFavoriteDisplay() {
        Label message = new Label("It seems like you don't have any favorite recipe...");
        message.setId("text-empty-favorites");
        recipeDisplay.setAlignment(Pos.TOP_CENTER);
        recipeDisplay.getChildren().add(message);
    }

    public void pageNavigationButtonDisplay(int nbOfElement, HBox lastBox) {
        if (this.pageIndex > 1) {
            ImageView knifeImage = new ImageView(new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/knifeWithoutSAuce.png")));
            knifeImage.setPreserveRatio(true);
            knifeImage.setFitWidth(100);

            Button previousPage = new Button("", knifeImage);
            previousPage.addEventFilter(MouseEvent.MOUSE_ENTERED, setKnifeWithSauceImage(knifeImage));
            previousPage.addEventFilter(MouseEvent.MOUSE_EXITED, setKnifeWithoutSauceImage(knifeImage));
            previousPage.getStyleClass().add("button-pagination");

            lastBox.getChildren().add(previousPage);
            previousPage.setOnAction(goToPage(this.pageIndex-1));
        }

        if (nbOfElement > this.pageIndex * 10) {
            Button nextPage = new Button("nextPage");
            nextPage.getStyleClass().add("button-pagination");
            lastBox.getChildren().add(nextPage);
            nextPage.setOnAction(goToPage(this.pageIndex+1));
        }
    }

    public void pageDisplay(int pageIndex) {
        this.pageIndex = pageIndex;
        this.recipeDisplay.getChildren().clear();
        ArrayList<Recipe> favoritesRecipes = favoriteNode.getFavorites();
        for (int recipeIndex = (this.pageIndex-1)*10; recipeIndex < favoritesRecipes.size() && recipeIndex < pageIndex*10; recipeIndex++) {
            recipeDisplay.getChildren().add(recipeBoxDisplayList.get(recipeIndex));
        }
        HBox lastBox = new HBox();
        lastBox.getStyleClass().add("box-pagination");
        this.recipeDisplay.getChildren().add(lastBox);
        pageNavigationButtonDisplay(favoritesRecipes.size(), lastBox);
    }

    public void update() {
        int nbOfFavoriteRecipe = favoriteNode.getFavorites().size();
        int maxPageIndex = (int) Math.ceil(nbOfFavoriteRecipe / 10.0);

        if (nbOfFavoriteRecipe == 0) {
            emptyFavoriteDisplay();
        } else if (maxPageIndex < this.pageIndex) {
            pageDisplay(this.pageIndex-1);
        } else {
            pageDisplay(this.pageIndex);
        }
    }
}
