package app.foodapp.model.dataManipulation;

import java.io.*;

public enum MeasureSystem {
    US(0),
    METRIC(1);

    MeasureSystem(final int index) {
    }

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

    public static void setMeasureSystem (final MeasureSystem measureSystem) {
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

    @Override
    public String toString() {
        switch (this) {
            case US: return "us";
            case METRIC: return "metric";
        }
        return "";
    }
}
