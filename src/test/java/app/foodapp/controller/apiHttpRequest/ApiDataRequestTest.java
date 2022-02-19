package app.foodapp.controller.apiHttpRequest;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiDataRequestTest extends ApiDataRequest{

    @Test
    public void getApiKey() {
        // Assertion to watch ApiDataRequest legacy correctly functioned
        assertEquals(this.API_KEY, "165550d477004117b084d6a175685e39");
    }
}
