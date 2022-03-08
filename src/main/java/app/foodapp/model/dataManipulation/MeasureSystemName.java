package app.foodapp.model.dataManipulation;

import java.io.*;

public enum MeasureSystemName {
    US(0),
    METRIC(1);

    MeasureSystemName(final int index) {
    }

    public static MeasureSystemName getMeasureSystem() throws IOException {
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

    public static void setMeasureSystem (final MeasureSystemName measureSystem) {
        try {
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MeasureSystemName reverseMeasureSystem() {
        switch (this) {
            case US:
                return METRIC;
            case METRIC:
                return US;
        }
        return null;
    }

    @Override
    public String toString() {
        switch (this) {
            case US: return "us";
            case METRIC: return "metric";
        }
        return "";
    }
}
