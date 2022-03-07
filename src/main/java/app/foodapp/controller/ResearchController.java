package app.foodapp.controller;

import app.foodapp.controller.backController.BackController;
import app.foodapp.controller.backController.BackToMenu;
import app.foodapp.model.dataManipulation.recipe.Recipe;
import app.foodapp.model.dataManipulation.recipe.RecipeInformation;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class ResearchController extends recipeListController {

    @FXML private HBox recipeResearch;
    @FXML private VBox recipeDisplay;
    @FXML private ImageView leftCornerLogo;
    @FXML private AnchorPane rootPane;

    private ArrayList<String> ingredients = new ArrayList<>();
    private RecipeInformation recipeInformation;
    private VBox ingredientsAddedDisplay = new VBox();
    private boolean isSearchLunched = false;
    private TextField searchByIngredient = null;
    private ImageView pop = new ImageView();
    private Button displayIngredientsAddedButton = null;
    private double mouseXPosition = 0;
    private double mouseYPosition = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void welcomePage() {
        setImage(leftCornerLogo, new Image("/app/foodapp/view/pictures/logo/logoApp.png"));

        Label title = new Label("Welcome to COOKING APP !\n");
        title.setId("text-welcome");
        Label message = new Label("Let's start and search a recipe by using the text field in the top right corner !");
        message.setId("text-message");
        ImageView image = new ImageView(new Image("/app/foodapp/view/pictures/logo/logoApp.png"));
        image.setPreserveRatio(true);
        image.setFitWidth(400);

        recipeDisplay.setAlignment(Pos.TOP_CENTER);
        recipeDisplay.getChildren().add(title);
        recipeDisplay.getChildren().add(image);
        recipeDisplay.getChildren().add(message);
    }

    public void setRecipeResearch() {
        ingredientsAddedDisplay.setId("vbox-ingredientsAdded");
        recipeResearch.setAlignment(Pos.BOTTOM_CENTER);
        ImageView ingredientsAddedImage = new ImageView(new Image("/app/foodapp/view/pictures/researchRecipe/ingredientsAddedButton.png"));
        ingredientsAddedImage.setPreserveRatio(true);
        ingredientsAddedImage.setFitWidth(25);

        displayIngredientsAddedButton = new Button("", ingredientsAddedImage);
        Tooltip.install(displayIngredientsAddedButton, new Tooltip("Display ingredients added"));
        displayIngredientsAddedButton.setId("button-ingredientsAdded");

        VBox ingredientsAdded = new VBox();
        ingredientsAdded.setId("vbox-ingredientsAdded");
        displayIngredientsAddedButton.addEventFilter(MouseEvent.MOUSE_ENTERED, setMousePosition());
        displayIngredientsAddedButton.setOnAction(manageButtonDisplayIngredientsAdded());

        TextField searchByIngredient = new TextField();
        this.searchByIngredient = searchByIngredient;
        searchByIngredient.setPromptText("Add an ingredient");
        searchByIngredient.setId("textField");
        searchByIngredient.setOnKeyPressed(addIngredientToSearchTextField());

        ImageView addIngredientImage = new ImageView(new Image("/app/foodapp/view/pictures/researchRecipe/plusButton.png"));
        addIngredientImage.setPreserveRatio(true);
        addIngredientImage.setFitWidth(25);

        Button addIngredientButton = new Button("", addIngredientImage);
        addIngredientButton.setCursor(Cursor.HAND);
        Tooltip.install(addIngredientButton, new Tooltip("Add ingredient to research"));
        addIngredientButton.getStyleClass().add("button-research");
        addIngredientButton.setOnAction(addIngredientToSearchButton());
        addIngredientButton.addEventFilter(MouseEvent.MOUSE_ENTERED, setMousePosition());

        ImageView searchImage = new ImageView(new Image("/app/foodapp/view/pictures/researchRecipe/researchButton.png"));
        searchImage.setPreserveRatio(true);
        searchImage.setFitWidth(25);

        Button searchButton = new Button("", searchImage);
        searchButton.setCursor(Cursor.HAND);
        Tooltip.install(searchButton, new Tooltip("Make a research"));
        searchButton.getStyleClass().add("button-research");
        searchButton.setOnAction(getApiInformation());
        searchButton.addEventFilter(MouseEvent.MOUSE_ENTERED, setMousePosition());

        StackPane stackpane = new StackPane(searchByIngredient);
        displayIngredientsAddedButton.setLayoutX(1070);
        displayIngredientsAddedButton.setLayoutY(60);
        rootPane.getChildren().add(displayIngredientsAddedButton);
        this.recipeResearch.getChildren().add(stackpane);
        this.recipeResearch.getChildren().add(new StackPane(addIngredientButton));
        this.recipeResearch.getChildren().add(new StackPane(searchButton));
    }

    public EventHandler<ActionEvent> addIngredientToSearchButton() {
        return event -> addIngredient(this.searchByIngredient.getText());
    }

    public EventHandler<KeyEvent> addIngredientToSearchTextField () {
        return event -> {
                if(event.getCode() == KeyCode.ENTER){
                    addIngredient(this.searchByIngredient.getText());
                    this.searchByIngredient.clear();
                }
        };
    }

    public void addIngredient(final String ingredient) {
        if (this.ingredients.size() < 10) {
            if (!this.ingredients.contains(ingredient)) {
                if (ingredient != "") {
                    if (this.ingredients.size() == 0) displayIngredientsAddedButton.setCursor(Cursor.HAND);

                    ingredientsAddedDisplay.getChildren().clear();
                    this.isSearchLunched = false;
                    this.ingredients.add(ingredient);
                    popNumberOFIngredientsAdded(this.ingredients.size());
                    clearSearchByIngredient();

                    if (this.rootPane.getChildren().contains(this.ingredientsAddedDisplay)) {
                        removeDisplayIngredientsAdded();
                        displayIngredientsAdded();
                    }
                } else {
                    displayError("You should write an ingredient before adding it", 1000, 30, 1);
                }
            } else {
                displayError("You can't add twice the same element",1000, 30, 1);
            }
        } else {
            displayError("You can't add more ingredients because you have reach the maximum of ten elements",800, 30, 2);
        }
    }

    public EventHandler<ActionEvent> manageButtonDisplayIngredientsAdded() {
        return event -> {
            if (this.ingredients.size() != 0) {
                if (!this.rootPane.getChildren().contains(this.ingredientsAddedDisplay)) {
                    displayIngredientsAdded();
                } else {
                    removeDisplayIngredientsAdded();
                }
            }
        };
    }

    public EventHandler<MouseEvent> setMousePosition() {
        return event -> {
            updateMousePosition(event.getX(), event.getY());
        };
    }

    public void updateMousePosition(final double mouseXPosition,final double mouseYPosition) {
        this.mouseXPosition = mouseXPosition;
        this.mouseYPosition = mouseYPosition;
    }

    public void displayIngredientsAdded() {
        this.ingredientsAddedDisplay.getChildren().clear();
        for (String ingredient : this.ingredients) {
            Label ingredientAdded = new Label(ingredient);
            ingredientAdded.getStyleClass().add("label-ingredientAdded");

            HBox ingredientAddedBox = new HBox();
            ingredientAddedBox.addEventFilter(MouseEvent.MOUSE_CLICKED, removeIngredientAdded(ingredient));
            ingredientAddedBox.setCursor(Cursor.HAND);
            ingredientAddedBox.getChildren().add(ingredientAdded);
            Tooltip.install(ingredientAddedBox, new Tooltip("Remove ingredient from research"));
            ingredientAddedBox.getStyleClass().add("box-ingredientAdded");

            ingredientsAddedDisplay.setLayoutX(1070);
            ingredientsAddedDisplay.setLayoutY(86);
            ingredientsAddedDisplay.getChildren().add(ingredientAddedBox);
        }
        rootPane.getChildren().add(ingredientsAddedDisplay);
    }

    public void removeDisplayIngredientsAdded() {
        rootPane.getChildren().remove(ingredientsAddedDisplay);
    }

    public EventHandler<MouseEvent> removeIngredientAdded(String ingredient) {
        return event -> {
            this.ingredients.remove(ingredient);
            popNumberOFIngredientsAdded(this.ingredients.size());
            removeDisplayIngredientsAdded();
            if (this.ingredients.size() != 0) displayIngredientsAdded();
            else displayIngredientsAddedButton.setCursor(Cursor.DEFAULT);

            if (this.isSearchLunched) displayRecipes();
        };
    }

    public void displayRecipes() {
        if (this.rootPane.getChildren().contains(this.ingredientsAddedDisplay)) removeDisplayIngredientsAdded();

        if (this.ingredients.size() != 0) {
            recipeDisplay.getChildren().clear();
            recipeBoxDisplayList.clear();
            favoritesButtonList.clear();
            isSearchLunched = true;

            recipeInformation = new RecipeInformation(this.ingredients);
            List<Recipe> recipeList = recipeInformation.listOfRecipe;
            this.backController = new BackToMenu((ArrayList<Recipe>) recipeList, this.ingredients, this.pageIndex);
            setRecipeList(recipeList, "#buttonMenu", "Research");
            pageDisplay(pageIndex, this.recipeDisplay, recipeList);

        } else {
            this.searchByIngredient.clear();
            if (!this.isSearchLunched) displayError("You should add an ingredient before making a research", 950, 30, 1);

        }
    }

    public void makeResearch(final ArrayList<Recipe> recipeList, final int pageIndex, final ArrayList<String> ingredients, final BackController backController) {
        isSearchLunched = true;

        for (String ingredient : ingredients) {
            addIngredient(ingredient);
        }
        this.backController = backController;
        setRecipeList(recipeList, "#buttonMenu", "Research");
        pageDisplay(pageIndex, this.recipeDisplay, recipeList);
    }

    public EventHandler<ActionEvent> getApiInformation() {
        return event -> displayRecipes();
    }

    @Override
    public EventHandler<ActionEvent> removeRecipeFromFavorites(final Button button, final Recipe recipe, final StackPane stackPane, final HBox box) {
        return event -> {
            ParallelTransition animation = removeRecipeFromFavoritesAnimation(button, recipe, stackPane, box);
            animation.setOnFinished(event1 -> {
                favoriteNode.removeFromFavorite(recipe);
                manageFavoriteButton(button, recipe, stackPane, box);
            });
            animation.play();
        };
    }

    public void clearSearchByIngredient() {
        this.searchByIngredient.clear();
    }

    public void displayError(final String message, final double xRelativePosition,  final double yRelativePosition, final int duration) {
        Label error = new Label(message);
        error.getStyleClass().add("errorMessage");

        this.rootPane.getChildren().add(error);
        error.setLayoutX(this.mouseXPosition+xRelativePosition+2);
        error.setLayoutY(this.mouseYPosition+yRelativePosition-2);

        FadeTransition fading = new FadeTransition(Duration.millis(1000), error);
        fading.setFromValue(1);
        fading.setToValue(0);
        fading.setOnFinished(event1 -> {
            this.rootPane.getChildren().remove(error);
            clearSearchByIngredient();
        });

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event2 -> fading.pause()),
                new KeyFrame(Duration.seconds(duration), event2 -> fading.play()));
        timeline.play();
    }

    public void popNumberOFIngredientsAdded(final int nbOfElement) {
        if (this.rootPane.getChildren().contains(pop)) {
            this.rootPane.getChildren().remove(pop);
        }

        if (nbOfElement != 0) {
            Image image = new Image("/app/foodapp/view/pictures/nbOfElements/oneElement.png");
            switch (nbOfElement) {
                case 2: image = new Image("/app/foodapp/view/pictures/nbOfElements/twoElements.png"); break;
                case 3: image = new Image("/app/foodapp/view/pictures/nbOfElements/threeElements.png"); break;
                case 4: image = new Image("/app/foodapp/view/pictures/nbOfElements/fourElements.png"); break;
                case 5: image = new Image("/app/foodapp/view/pictures/nbOfElements/fiveElements.png"); break;
                case 6: image = new Image("/app/foodapp/view/pictures/nbOfElements/sixElements.png"); break;
                case 7: image = new Image("/app/foodapp/view/pictures/nbOfElements/sevenElements.png"); break;
                case 8: image = new Image("/app/foodapp/view/pictures/nbOfElements/eightElements.png"); break;
                case 9: image = new Image("/app/foodapp/view/pictures/nbOfElements/nineElements.png"); break;
                case 10: image = new Image("/app/foodapp/view/pictures/nbOfElements/maxElements.png"); break;
            }
            pop.setImage(image);
            pop.setPreserveRatio(true);
            pop.setFitWidth(15);
            pop.setLayoutX(1095);
            pop.setLayoutY(60);
            this.rootPane.getChildren().add(pop);
        }
    }
}