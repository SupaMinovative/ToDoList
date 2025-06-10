package com.minovative.todolist;


import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHelper {
    public static final String FILENAME = "todoInfo.dat";

    public static void writeData(ArrayList<String> item, Context context) throws IOException {
        FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        ObjectOutputStream oas = new ObjectOutputStream(fos);
        oas.writeObject(item);
        oas.close();
    }

    public static ArrayList<String> readData(Context context) throws IOException, ClassNotFoundException {

        ArrayList<String> itemList = null;

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemList = (ArrayList<String>) ois.readObject();
            ois.close();
            fis.close();

        } catch (FileNotFoundException e) {
            itemList = new ArrayList<>();
        }

        return  itemList;
    }
}
