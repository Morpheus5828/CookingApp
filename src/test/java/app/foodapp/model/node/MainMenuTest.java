package app.foodapp.model.node;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MainMenuTest {
    private final PrintStream standardOut = System.out;
    private MainMenu menu;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void askFirstUserChoicesTest() {
        menu = new MainMenu();
        String result =
                "1. Get recipe by ingredients" + "\n" +
                "\t 2. Favorite list" + "\n" +
                "\t 3. System Measure" + "\n" +
                "\t 4. Close CookingApp";
        System.out.println(menu.askFirstChoices());
        assertEquals(result, outputStreamCaptor.toString().trim());
    }

    @Test
    public void askChoiceNumberTest() {
        menu = new MainMenu();
        String result = "--> Tap number:";
        System.out.println(menu.askChoiceNumber());
        assertEquals(result, outputStreamCaptor.toString().trim());
    }

    @Test
    public void getChoiceNumberTest() {
        menu = Mockito.mock(MainMenu.class);
        Mockito.when(menu.getChoiceNumber()).thenReturn(1);
        assertEquals(menu.getChoiceNumber(), 1);
    }

    @Test
    public void changeCurrentNodeTest() {
        menu = new MainMenu();
        menu.setChoice(1);
        assertEquals("MAIN_MENU", Pane.currentNode);
        menu.changeCurrentNode();
        assertEquals("GET_RECIPE_BY_INGREDIENT", Pane.currentNode);
        menu.setChoice(2);
        menu.changeCurrentNode();
        assertEquals("FAVORITE", Pane.currentNode);
        menu.setChoice(3);
        menu.changeCurrentNode();
        assertEquals("MEASURE_SYSTEM", Pane.currentNode);
        menu.setChoice(4);

        //assertEquals("\n" + "⚠ Node doesn't exist !" + "\n", outputStreamCaptor.toString().trim());


    }



}

