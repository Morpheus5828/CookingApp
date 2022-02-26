package app.foodapp.controller;

import app.foodapp.controller.exception.InvalidKeyException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class KeyManagement {

    public static String getKey() {
        String key = null;
        try {
            FileReader fileReader = new FileReader("src/main/resources/dataBase/keys.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (int index = 0; index < getKeyIndex(); index++) {
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
        String key = null;
        try {
            FileReader fileReader = new FileReader("src/main/resources/dataBase/keys.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            for (int index = 0; index <= getKeyIndex(); index++) {
                bufferedReader.readLine();
            }
            key = bufferedReader.readLine();
            bufferedReader.close();

            if (key == null) {
                bufferedReader = new BufferedReader(fileReader);
                key = bufferedReader.readLine();
                bufferedReader.close();
            }

        } catch (IOException e) {e.printStackTrace();}
        return key;
    }

    private static int getKeyIndex() {
        int keyIndex = 0;
        try {
            FileReader fileReader = new FileReader("src/main/resources/dataBase/keysManagement.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            keyIndex = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {e.printStackTrace();}
        return keyIndex;
    }

}
