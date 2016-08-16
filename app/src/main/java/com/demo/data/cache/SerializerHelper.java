package com.demo.data.cache;

import android.content.Context;
import android.util.Log;

import com.demo.DemoApp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import timber.log.Timber;

public class SerializerHelper {

    public static void storeObject(Serializable object, String fileName) {
        FileOutputStream fos;
        try {
            fos = DemoApp.getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
        } catch (IOException e) {
            Timber.d(Log.getStackTraceString(e));
        }
    }
    /******************************************************/
    /* New approach*/

    /*****************************************************/

    public static <T> T readObject(String fileName) {
        T result = null;
        try {
            FileInputStream fileInputStream = DemoApp.getContext().openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            //noinspection unchecked
            result = (T) objectInputStream.readObject();
        } catch (Exception e) {
            storeObject(null, fileName);
            Timber.e(e.toString());
        }
        return result;
    }
}

