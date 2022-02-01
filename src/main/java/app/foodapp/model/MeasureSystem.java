package app.foodapp.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public enum MeasureSystem {
    US(0),
    METRIC(1);

    MeasureSystem(int index) {
    }

    /*
    This function returns the current measure system.
     */
    public static MeasureSystem getMeasureSystem() throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader("preferences/MeasureSystem.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String content = bufferedReader.readLine();
        bufferedReader.close();
        switch (content) {
            case "0": return US;
            case "1": return METRIC;
            default: throw new IOException("File's content doesn't match any Measure System");
        }
    }
}
