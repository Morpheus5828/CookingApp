package app.foodapp.controller.dataRequest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainInstructionsRequestTest {
    @Test
    public void testCheckForDataExtraction() {
        MainInstructionsRequest request = new MainInstructionsRequest("622561");
        int statusCode = request.getStatusCode();
        assertEquals(200, statusCode);

    }
}
