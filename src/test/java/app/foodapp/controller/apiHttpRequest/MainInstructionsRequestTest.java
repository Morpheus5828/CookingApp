package app.foodapp.controller.apiHttpRequest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainInstructionsRequestTest {
    @Test
    public void testCheckForDataExtraction() {
        String ingredientExample = "apples,+sugar&number=2";
        MainInstructionsRequest request = new MainInstructionsRequest(ingredientExample);
        int statusCode = request.getStatusCode();
        // ths test can fail if user does more 100 request during 24h
        assertEquals(statusCode, 200);

    }
}
