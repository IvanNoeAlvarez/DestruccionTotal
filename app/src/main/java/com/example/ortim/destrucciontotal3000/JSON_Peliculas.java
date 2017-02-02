package com.example.ortim.destrucciontotal3000;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ortim on 25/01/2017.
 */

public class JSON_Peliculas extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Peliculas>peliculasL;
    LinearLayoutManager layout;
    String JSON;
    Adaptador_Peliculas adap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        peliculasL = new ArrayList<Peliculas>();
        rv = (RecyclerView)findViewById(R.id.recyclerV);
        layout = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layout);
        rv.setHasFixedSize(true);

        boolean conexion = Conexion();
        if (conexion){
         new conectaAsyncTask().execute("http://jsonparsing.parseapp.com/jsonData/moviesData.txt");
        }


    }

    private boolean Conexion() {
        ConnectivityManager conn = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = conn.getActiveNetworkInfo();

        if (network != null && network.isConnected()){
                new conectaAsyncTask().execute("http://jsonparsing.parseapp.com/jsonData/moviesData.txt");
        }
        return true;
    }
    public String conexionHTTP (String urlPag){
        URL url;
        String respuesta = null;

        try {
            url = new URL(urlPag);
            HttpURLConnection conexion = (HttpURLConnection)url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.setDoInput(true);
            conexion.connect();

            InputStream is = new BufferedInputStream(conexion.getInputStream());
            respuesta = conversionString(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return respuesta;
    }

    private String conversionString(InputStream is) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();

        reader = new BufferedReader(new InputStreamReader(is));
        String texto;

        try {
            while ((texto = reader.readLine())!=null){
                sb.append(texto).append('\n');

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return  sb.toString();

    }
    public void parseaJSON (String doc){
        if (doc != null) {
            Log.d("asdf","doc -> "+doc);
            try {
                JSONObject jsonO = new JSONObject(doc);
                JSONArray jsonA = jsonO.getJSONArray("movies");

                for (int i = 0; i < jsonA.length(); i++) {
                    JSONObject jO = jsonA.getJSONObject(i);
                    Log.d("asdf"," ¿i?"+i);
                    String nombre = jO.getString("movie");
                    int anyo = jO.getInt("year");
                    Double rating = jO.getDouble("rating");
                    String duration = jO.getString("duration");
                    String director = jO.getString("director");
                    String imgSource = jO.getString("image");
                    Bitmap imagen = Conexion_Imagen.BitmapFromString(imgSource);
                    String story = jO.getString("story");


                    Peliculas pelicula = new Peliculas();
                    pelicula.setNombre(nombre);
                    pelicula.setAnyo(anyo);
                    pelicula.setRating(rating);
                    pelicula.setDuration(duration);
                    pelicula.setDirector(director);
                    pelicula.setImg(imagen);
                    pelicula.setStory(story);
                    peliculasL.add(pelicula);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
           Log.d("asdf","que mierda tira -> "+doc);

        }

    }

    public class conectaAsyncTask extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urls) {
            JSON = conexionHTTP(urls[0]);
            long tiempo = System.currentTimeMillis();
            parseaJSON(JSON);
            long tiempoF = System.currentTimeMillis();

            return ((tiempoF - tiempo)+" ms");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            adap = new Adaptador_Peliculas(peliculasL,getApplicationContext());
            rv.setAdapter(adap);
        }
    }
}
