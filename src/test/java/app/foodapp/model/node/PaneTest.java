package app.foodapp.model.node;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class PaneTest {

    @Test
    public void choiceTest() {
        Pane.setNextNodeNumber("MAIN_MENU");
        assertEquals(Pane.currentNode, "MAIN_MENU");
        Pane.setNextNodeNumber("GET_RECIPE_BY_INGREDIENT");
        assertEquals(Pane.currentNode, "GET_RECIPE_BY_INGREDIENT");
        Pane.setNextNodeNumber("FAVORITE");
        assertEquals(Pane.currentNode, "FAVORITE");
        Pane.setNextNodeNumber("RECIPE_DETAILS");
        assertEquals(Pane.currentNode, "RECIPE_DETAILS");
        Pane.setNextNodeNumber("MEASURE_SYSTEM");
        assertEquals(Pane.currentNode, "MEASURE_SYSTEM");
        Pane.checkStatusCode = false;
    }

    @Test //TODO A finir
    public void backTest() {
        Pane.setNextNodeNumber("MAIN_MENU");
        Pane.back();
        assertEquals("MAIN_MENU", Pane.currentNode);

        Pane.setNextNodeNumber("GET_RECIPE_BY_INGREDIENT");
        Pane.back();
        assertEquals("MAIN_MENU", Pane.currentNode);

        Pane.setNextNodeNumber("FAVORITE");
        Pane.back();
        assertEquals("MAIN_MENU", Pane.currentNode);

        Pane.setNextNodeNumber("MEASURE_SYSTEM");
        Pane.back();
        assertEquals("MAIN_MENU", Pane.currentNode);

        Pane.setNextNodeNumber("RECIPE_DETAILS");
        Pane.back();
        assertEquals("MAIN_MENU", Pane.currentNode);

        Pane.setNextNodeNumber("MEASURE_SYSTEM");
        Pane.back();
        assertEquals("MAIN_MENU", Pane.currentNode);

        Pane.checkStatusCode = false;
    }
}
