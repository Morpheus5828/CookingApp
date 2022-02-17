package app.foodapp.controller.checkInternetConnexion;

import app.foodapp.view.errorDisplay.ErrorDisplay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class InternetConnexionTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    void givenSystemOutRedirection_whenInvokePrintln_thenOutputCaptorSuccess() throws IOException {
        InternetConnexion.checkStatus();
        assertEquals("Internet is connected", outputStreamCaptor.toString().trim());
    }
}
