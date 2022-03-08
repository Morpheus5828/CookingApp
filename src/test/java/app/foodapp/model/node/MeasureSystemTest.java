package app.foodapp.model.node;

import app.foodapp.model.dataManipulation.MeasureSystemName;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MeasureSystemTest {

    @Test
    public void testSetMeasureSystem() throws IOException {
        MeasureSystemName currentMeasureSystem = MeasureSystemName.getMeasureSystem();

        MeasureSystemName.setMeasureSystem(MeasureSystemName.METRIC);
        assertEquals(MeasureSystemName.METRIC, MeasureSystemName.getMeasureSystem());
        MeasureSystemName.setMeasureSystem(MeasureSystemName.US);
        assertEquals(MeasureSystemName.US, MeasureSystemName.getMeasureSystem());

        MeasureSystemName.setMeasureSystem(currentMeasureSystem);
    }

    @Test
    public void testToString() {
        assertEquals("us", MeasureSystemName.US.toString());
        assertEquals("metric", MeasureSystemName.METRIC.toString());
    }
}
