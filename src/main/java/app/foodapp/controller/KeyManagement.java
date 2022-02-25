package app.foodapp.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class KeyManagement {
    private static int keyIndex = 0;
    private static boolean isKeyBeforeValid = true;
    private static boolean waitingForKeyToBeValid = false;

    public static String getKey() {
        String key = null;
        try {
            FileReader fileReader = new FileReader("src/main/resources/dataBase/keys.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (int index = 0; index < keyIndex; index++) {
                bufferedReader.readLine();
            }
            key = bufferedReader.readLine();
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }

    public static String getNextKey() {
        return null;
    }

    private static String findNextKey() {
        String key = null;
        try {
            FileReader fileReader = new FileReader("src/main/resources/dataBase/keys.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            for (int index = 0; index <= keyIndex; index++) {
                bufferedReader.readLine();
            }
            keyIndex++;
            key = bufferedReader.readLine();
            bufferedReader.close();

            if (key == null) {
                keyIndex = 0;
                bufferedReader = new BufferedReader(fileReader);
                key = bufferedReader.readLine();
                bufferedReader.close();
            }

        } catch (IOException e) {e.printStackTrace();}
        return key;
    }

}
