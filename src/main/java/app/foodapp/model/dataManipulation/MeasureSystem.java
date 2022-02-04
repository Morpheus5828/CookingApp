package app.foodapp.model.dataManipulation;

import java.io.*;

public enum MeasureSystem {
    US(0),
    METRIC(1);

    MeasureSystem(int index) {
    }

    /*
    This function returns the current measure system.
     */
    public static MeasureSystem getMeasureSystem() throws IOException {
        FileReader fileReader = new FileReader("save/MeasureSystem.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String content = bufferedReader.readLine();
        bufferedReader.close();
        switch (content) {
            case "0": return US;
            case "1": return METRIC;
            default: throw new IOException("File's content doesn't match any Measure System");
        }
    }

    /*
    This function set the measure system of the ingredient. The system is saved in a file.
     */
    public static void setMeasureSystem (MeasureSystem measureSystem) throws IOException {
        FileWriter fileWriter = new FileWriter("save/MeasureSystem.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        switch (measureSystem) {
            case US:
                bufferedWriter.write("0");
                bufferedWriter.close();
                break;
            case METRIC:
                bufferedWriter.write("1");
                bufferedWriter.close();
                break;
            default:
                bufferedWriter.close();
                throw  new IOException("Measure system provided is not accepted.");
        }
    }
}
