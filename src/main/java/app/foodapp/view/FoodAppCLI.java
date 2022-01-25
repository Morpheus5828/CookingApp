package app.foodapp.view;

import java.util.Scanner;

public class FoodAppCLI {
    public static void main(String[] args) {
        System.out.println("Welcome to the weather app");

        System.out.println("You requested command '" + args[0] + "' with parameter '" + args[1] + "'");

        System.out.println("Input your command: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sorry, I can't do anything yet ! (Read: " + scanner.nextLine() +")");
        scanner.close();
    }
}
