package com.example.ortim.destrucciontotal3000;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ortim on 26/01/2017.
 */

public class Conexion_Imagen {

// clase que a partir de una url devolvera un String
    public static String StringFromWeb(String url) {
        InputStream is=null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();

        reader = new BufferedReader(new InputStreamReader(is));

        String texto;

        try {
            HttpURLConnection conexion = (HttpURLConnection) new URL(url).openConnection();
            conexion.setRequestMethod("GET");
            conexion.setDoInput(true);
            conexion.connect();

            while ((texto = reader.readLine()) != null) {
                builder.append(texto).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return builder.toString();
    }

    // A partir del String devolvera un bitmap
    public static Bitmap BitmapFromString(String builder){

        Bitmap bitmap;
        try{
            URL url = new URL(builder);
            URLConnection conexion = url.openConnection();
            conexion.connect();
            InputStream is = conexion.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bitmap = BitmapFactory.decodeStream(bis);
            bis.close();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }
}