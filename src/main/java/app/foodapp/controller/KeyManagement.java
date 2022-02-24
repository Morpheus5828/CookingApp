package app.foodapp.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class KeyManagement {
    private static int keyIndex = 0;
    private boolean isKeyBeforeValid = true;

    public static String getNextKey() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/dataBase/keys.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            for (int index = 0; index < keyIndex; index++) {
                bufferedReader.readLine();
            }
            keyIndex++;
            String key = bufferedReader.readLine();
            bufferedReader.close();

            if (key == null) {
                bufferedReader = new BufferedReader(fileReader);
                keyIndex = 0;
                return bufferedReader.readLine();
            }
            return key;

        } catch (IOException e) {e.printStackTrace();}
        return null;
    }
}
