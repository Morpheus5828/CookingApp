package app.foodapp.controller.apiHttpRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiDataRequestTest {
    @Test
    public void testCheckForDataExtraction() {
        String ingredientExample = "apples,+sugar&number=2";
        ApiDataRequest request = new ApiDataRequest(ingredientExample);
        int statusCode = request.getStatusCode();
        assertEquals(statusCode, 200);

    }
}
