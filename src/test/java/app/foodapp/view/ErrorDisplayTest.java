package app.foodapp.view;

import app.foodapp.view.alert.AlertFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorDisplayTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    void givenSystemOutRedirection_whenInvokePrintln_thenOutputCaptorSuccess() {
        AlertFound.connexionFailed();
        assertEquals("Something wrong with your internet connexion, please try again", outputStreamCaptor.toString().trim());
    }
}
