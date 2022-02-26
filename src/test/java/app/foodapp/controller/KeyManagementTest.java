package app.foodapp.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KeyManagementTest {

    @Test
    public void testSetKeyIndex() {
        int currentKeyIndex = KeyManagement.getKeyIndex();
        int currentKeyIndexLoopStart = KeyManagement.getKeyIndexLoopStart();

        KeyManagement.setKeyIndex(5);
        assertEquals(5, KeyManagement.getKeyIndex());
        assertEquals(currentKeyIndexLoopStart, KeyManagement.getKeyIndexLoopStart());

        KeyManagement.setKeyIndex(currentKeyIndex);
    }

    @Test
    public void testChangeKeyIndexLoopStart() {
        int currentKeyIndex = KeyManagement.getKeyIndex();
        int currentKeyIndexLoopStart = KeyManagement.getKeyIndexLoopStart();

        KeyManagement.changeKeyIndexLoopStart(7);
        assertEquals(currentKeyIndex, KeyManagement.getKeyIndex());
        assertEquals(7, KeyManagement.getKeyIndexLoopStart());

        KeyManagement.changeKeyIndexLoopStart(currentKeyIndexLoopStart);
    }
}
