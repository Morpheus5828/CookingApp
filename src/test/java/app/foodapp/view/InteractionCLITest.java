package app.foodapp.view;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class InteractionCLITest {

    private InteractionCLI instance;

    @Test
    public void askMainChoiceTest() {
        instance = mock(InteractionCLI.class);
        when(instance.askMainChoice()).thenReturn(1);
        int result = instance.askMainChoice();
        assertEquals(result, 1);
    }

    // Test for askIngredient, warning it has scanner instance
}
