package app.foodapp.view;

import app.foodapp.controller.apiHttpRequest.SearchRecipesByIngredients;
import app.foodapp.model.dataManipulation.recipe.RecipeInformation;
import app.foodapp.model.node.Welcome;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class InteractionCLI {
    public InteractionCLI() {
        System.out.println("\nWelcome to CookingApp\n");
        new Welcome();


    }


}
