package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import app.foodapp.model.dataManipulation.recipe.RecipeInformation;
import app.foodapp.model.node.Favorite;
import com.google.gson.JsonArray;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML private TextField searchByIngredient;
    //@FXML private Text mainDisplay;
    //@FXML private AnchorPane buttonsAnchorPane;
    @FXML private AnchorPane ingredientsAnchorPane;
    @FXML private VBox recipeDisplay;


    private ArrayList<Button> ingredientButtons = new ArrayList<Button>();
    private ArrayList<String> strings = new ArrayList<String>();
    private RecipeInformation recipeInformation;
    private ArrayList<Button> favoritesButtons = new ArrayList<Button>();
    protected Favorite favorites = new Favorite();
    private ArrayList<Button> detailsButtons = new ArrayList<Button>();
    protected Recipe recipeSelectedForDetails;
    private final ArrayList<HBox> recipeBoxDisplayList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void goToFavorites(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/favorites.fxml"));
        Parent root = loader.load();
        FavoriteController favoriteController = loader.getController();
        favoriteController.showFavorites();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/app/foodapp/view/favorites.css").toExternalForm();

        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void addIngredientToSearch(KeyEvent keyEvent) {
        searchByIngredient.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    createIngredientsButtons();
                    isSearchLunched = false;
                    searchByIngredient.clear();
                }
            }
        });
    }

    private boolean isSearchLunched = false;

    public void displayApiInformations(ActionEvent actionEvent) {
        //buttonsAnchorPane.getChildren().clear();
        recipeDisplay.getChildren().clear();
        favoritesButtons.clear();
        detailsButtons.clear();
        recipeBoxDisplayList.clear();
        recipeInformation = new RecipeInformation(strings);

        //mainDisplay.setText(recipeInformation.displayGUI());

        for (Recipe recipe : recipeInformation.listOfRecipe) {
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


            removeFromFavoriteButton.addEventFilter(MouseEvent.MOUSE_ENTERED, setBrokenHeartImage(removeFromFavoriteImage));
            removeFromFavoriteButton.addEventFilter(MouseEvent.MOUSE_EXITED, setFullHeartImage(removeFromFavoriteImage));
            recipeBoxDisplay.addEventFilter(MouseEvent.MOUSE_CLICKED, getRecipeDetails(recipe));
            recipeBoxDisplay.addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEnteredRecipeBoxDisplay(recipeBoxDisplay));
            recipeBoxDisplay.addEventFilter(MouseEvent.MOUSE_EXITED, mouseExitedRecipeBoxDisplay(recipeBoxDisplay));

            recipeBoxDisplay.getChildren().add(title);
            recipeBoxDisplay.getChildren().add(cookingTime);
            recipeBoxDisplay.getChildren().add(servings);
            recipeBoxDisplay.getChildren().add(removeFromFavoriteButton);
            recipeDisplay.getChildren().add(recipeBoxDisplay);
        }

        //createFavoriteButtons();
        //createDetailsButtons();
        isSearchLunched = true;
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

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("/app/foodapp/view/details.css").toExternalForm();

            scene.getStylesheets().add(css);
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
                Label title = (Label) recipeBoxDisplay.getChildren().get(0);
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
                Label title = (Label) recipeBoxDisplay.getChildren().get(0);
                Label cookingTime = (Label) recipeBoxDisplay.getChildren().get(1);
                Label servings = (Label) recipeBoxDisplay.getChildren().get(2);

                recipeBoxDisplay.getStyleClass().remove("recipe-content-hover");
                cookingTime.getStyleClass().remove("recipe-information-hover");
                servings.getStyleClass().remove("recipe-information-hover");
            }
        };
    }

        private void createFavoriteButtons(){
        int positionY = 20;
        for(int index = 0 ; index != recipeInformation.listOfRecipe.size() ; index++){
            Button newFavoriteButton = new Button();
            newFavoriteButton.setPrefSize(50, 50);
            newFavoriteButton.setText("<3");
            newFavoriteButton.setLayoutX(0);
            newFavoriteButton.setLayoutY(positionY);
            //buttonsAnchorPane.getChildren().add(newFavoriteButton);
            favoritesButtons.add(newFavoriteButton);
            newFavoriteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    favorites.addToFavorite(recipeInformation.listOfRecipe.get(favoritesButtons.indexOf(newFavoriteButton)));
                    newFavoriteButton.setText("Added");
                }
            });
            positionY += 175;
        }
    }

    private int positionX = 898;
    private int positionY = 59;

    private void createIngredientsButtons(){
        if(ingredientButtons.size() < 10) {
            Button newIngredientButton = new Button();
            newIngredientButton.setText(searchByIngredient.getText() + " x");
            newIngredientButton.setPrefSize(75, 25);
            if (ingredientButtons.size() == 5) {
                positionX = 898;
                positionY -= (10 + newIngredientButton.getPrefHeight());
            }
            ingredientButtons.add(newIngredientButton);
            newIngredientButton.setLayoutX(positionX);
            newIngredientButton.setLayoutY(positionY);
            ingredientsAnchorPane.getChildren().add(newIngredientButton);
            strings.add(ingredientButtons.indexOf(newIngredientButton), searchByIngredient.getText());
            newIngredientButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    strings.remove(ingredientButtons.indexOf(newIngredientButton));
                    ingredientButtons.remove(ingredientButtons.indexOf(newIngredientButton));
                    ingredientsAnchorPane.getChildren().remove(newIngredientButton);
                    positionX += (20 + newIngredientButton.getPrefWidth());
                    if(isSearchLunched) displayApiInformations(event);
                }
            });
            positionX -= (20 + newIngredientButton.getPrefWidth());
        }
        else{
            throw new ArrayIndexOutOfBoundsException("Can't add more ingredients");
        }
    }

    private void goToDetails(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/details.fxml"));
        Parent root = loader.load();
        DetailsController detailsController = loader.getController();
        detailsController.showDetails(recipeSelectedForDetails);

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/app/foodapp/view/details.css").toExternalForm();

        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    private void createDetailsButtons(){
        int positionY = 20;
        for(int index = 0 ; index != recipeInformation.listOfRecipe.size() ; index++){
            Button newDetailsButton = new Button();
            newDetailsButton.setPrefSize(50, 50);
            newDetailsButton.setText("Details");
            newDetailsButton.setLayoutX(80);
            newDetailsButton.setLayoutY(positionY);
            //buttonsAnchorPane.getChildren().add(newDetailsButton);
            detailsButtons.add(newDetailsButton);
            newDetailsButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    recipeSelectedForDetails = recipeInformation.listOfRecipe.get(detailsButtons.indexOf(newDetailsButton));
                    try {
                        goToDetails(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            positionY += 175;
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
}