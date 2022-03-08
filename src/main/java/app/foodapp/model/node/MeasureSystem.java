package app.foodapp.model.node;

import app.foodapp.model.alert.AlertFound;
import app.foodapp.model.dataManipulation.MeasureSystemName;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MeasureSystem {
    private int choice;
    private MeasureSystemName measureSystem;

    public MeasureSystem() {}

    public void launch() {
        try {
            this.measureSystem = MeasureSystemName.getMeasureSystem();

            System.out.println(askChoice());
            System.out.print(askChoiceNumber());
            this.choice = choiceNumberRecovered();
            changeMeasureSystem();
            Pane.setNextNodeNumber("MAIN_MENU");
        } catch (InputMismatchException e) {
            AlertFound.invalidCharacter();
        } catch (IOException e) {
            AlertFound.openMeasureSystemFileError();
        }
    }

    public String askChoice() {
        return "\nThe current measure system is : " +
                measureSystem.toString() +
                ". What do you want to do ?\n" +
                "\t 1. GO BACK \n" +
                "\t 2. Set to US \n" +
                "\t 3. Set to METRIC \n";
    }

    public String askChoiceNumber() {
        return "\t\t--> Tap number: ";
    }

    public int choiceNumberRecovered() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public void changeMeasureSystem() {
        try {
            switch (this.choice) {
                case 1:
                    return;
                case 2:
                    measureSystem = MeasureSystemName.US;
                    break;
                case 3:
                    measureSystem = MeasureSystemName.METRIC;
                    break;
                default:
                    throw new NoSuchElementException("Choice doesn't exist.");
            }
            MeasureSystemName.setMeasureSystem(measureSystem);
            setMeasureSystemSuccess();
        } catch (NoSuchElementException e) {
            setMeasureSystemFailure();
        }
    }

    public void setMeasureSystemSuccess() {
        System.out.println("\nThe measure system has been changed to : " + measureSystem.toString());
    }

    public void setMeasureSystemFailure() {
        System.out.println("\nThe system choose is not valid.");
    }
}
