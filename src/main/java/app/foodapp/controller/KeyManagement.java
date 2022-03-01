package app.foodapp.controller;

import app.foodapp.controller.exception.InvalidKeyException;

import java.io.*;

public class KeyManagement {

    public static String getCurrentKey() {
        return getKey(getKeyIndex());
    }

    public static String getKey(int keyIndex) {
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

    public static String getNextKey() throws InvalidKeyException{
        String key = null;
        try {
            FileReader fileReader = new FileReader("src/main/resources/dataBase/keys.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int keyIndex = getKeyIndex();

            for (int index = 0; index <= keyIndex; index++) {
                bufferedReader.readLine();
            }
            increaseKeyIndex();
            key = bufferedReader.readLine();
            bufferedReader.close();

            if (key == null) {
                setKeyIndex(0);
                key = getKey(0);
            }
            limitReach();

        } catch (IOException e) {e.printStackTrace();}
        return key;
    }

    public static int getKeyIndex() {
        int keyIndex = 0;
        try {
            FileReader fileReader = new FileReader("src/main/resources/dataBase/keysManagement.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            keyIndex = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
        } catch (IOException e) {e.printStackTrace();}
        return keyIndex;
    }

    public static int getKeyIndexLoopStart() {
        int keyIndexLoopStart = -1;
        try {
            FileReader fileReader = new FileReader("src/main/resources/dataBase/keysManagement.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();
            keyIndexLoopStart = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
        } catch (IOException e) {e.printStackTrace();}
        return keyIndexLoopStart;
    }

    public static void setKeyIndex(final int keyIndex) {
        try {
            int keyIndexLoopStart = getKeyIndexLoopStart();
            FileWriter fileWriter = new FileWriter("src/main/resources/dataBase/keysManagement.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(String.valueOf(keyIndex));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(keyIndexLoopStart));
            bufferedWriter.close();
        }catch (IOException e) {e.printStackTrace();}
    }

    public static void setKeyIndexLoopStart() {
        changeKeyIndexLoopStart(getKeyIndex());
    }

    public static void resetKeyIndexLoopStart() {
        changeKeyIndexLoopStart(-1);
    }

    public static void changeKeyIndexLoopStart(final int keyIndexLoopStart) {
        try {
            int keyIndex = getKeyIndex();
            FileWriter fileWriter = new FileWriter("src/main/resources/dataBase/keysManagement.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(String.valueOf(keyIndex));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(keyIndexLoopStart));
            bufferedWriter.close();
        }catch (IOException e) {e.printStackTrace();}
    }

    public static void increaseKeyIndex() {
        int keyIndex = getKeyIndex();
        keyIndex++;
        setKeyIndex(keyIndex);
    }

    public static void limitReach() throws InvalidKeyException {
        if (getKeyIndex() == getKeyIndexLoopStart())
            throw new InvalidKeyException("Can't find a valid key.");
    }

}
