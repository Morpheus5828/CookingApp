package app.foodapp.controller.checkInternetConnexion;

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

    // Test when pc is connected to internet
    /*@Test
    void givenSystemOutRedirection_whenInvokePrintln_thenOutputCaptorSuccess() throws IOException {
        InternetConnexion.checkStatus();
        assertEquals("Internet is connected", outputStreamCaptor.toString().trim());
    }*/

    // Test when pc is not connected to internet
    /*@Test
    void testFailedConnection() throws IOException {
        InternetConnexion.checkStatus();
        assertEquals("You are not connected to internet " + "\n" +
                "Please, try again", outputStreamCaptor.toString().trim());
    }*/

}
