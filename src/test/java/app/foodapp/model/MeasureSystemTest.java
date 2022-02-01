package app.foodapp.model;

import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MeasureSystemTest {

    @Test
    public void testSetMeasureSystem() throws IOException {
        MeasureSystem currentMeasureSystem = MeasureSystem.getMeasureSystem();

        MeasureSystem.setMeasureSystem(MeasureSystem.METRIC);
        assertEquals(MeasureSystem.METRIC, MeasureSystem.getMeasureSystem());
        MeasureSystem.setMeasureSystem(MeasureSystem.US);
        assertEquals(MeasureSystem.US, MeasureSystem.getMeasureSystem());

        MeasureSystem.setMeasureSystem(currentMeasureSystem);
    }
}
