package com.example.ortim.destrucciontotal3000;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;

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

public class JSON_Actores extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Actores> actorL;
    LinearLayoutManager layout;
    String JSON;
    Adaptador_Actores adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);


        actorL = new ArrayList<Actores>();
        rv = (RecyclerView) findViewById(R.id.recyclerV);
        layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layout);
        rv.setHasFixedSize(true);

        if (!Conexion()) {
            // new conectaURLAsync().execute("http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors");
            Toast.makeText(this, "Ha habido un problema con la conexion", Toast.LENGTH_SHORT).show();
        }


    }

    // clase donde se llama a la conexion al servicio web, pasandole por AsyncTask la url
    private boolean Conexion() {
        ConnectivityManager conn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkI = conn.getActiveNetworkInfo();

        if (networkI != null && networkI.isConnected()) {
            new conectaURLAsync().execute("http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors");
            return true;
        } else
            return false;
    }

    // clase donde se abre la conexion y se recogen los datos
    public String HTTPconex(String urlPagina) {
        URL url;
        String respuesta = null;

        try {
            url = new URL(urlPagina);

            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
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

    // clase donde se interpretara la inf recogida convirtiendola a String
    private String conversionString(InputStream is) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();

        reader = new BufferedReader(new InputStreamReader(is));

        String texto;
        try {
            while ((texto = reader.readLine()) != null) {
                sb.append(texto).append('\n');
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
        return sb.toString();
    }

    public void parseJSON(String doc) {
        if (doc != null) {

            try {
                Gson gson = new Gson();
                JSONObject actorO = new JSONObject(doc);
                JSONArray actorA = actorO.getJSONArray("actors");
                for (int i = 0; i < actorA.length(); i++) {
                    Actores actor = gson.fromJson(actorA.getJSONObject(i).toString(), Actores.class);

                    //JSONObject jO = actorA.getJSONObject(i);
                    /*String nombre = jO.getString("name");
                    String desc = jO.getString("description");
                    String dob = jO.getString("dob");
                    String ciudad = jO.getString("country");
                    String height = jO.getString("height");
                    String spouse = jO.getString("spouse");
                    String hijos = jO.getString("children");*/
                    //String imgSource = jO.getString("image");
                    //Bitmap imagen = Conexion_Imagen.BitmapFromString(imgSource);
                    /*
                    Actores actor = new Actores();
                    actor.setName(nombre);
                    actor.setDescription(desc);
                    actor.setDob(dob);
                    actor.setSpouse(spouse);
                    actor.setCountry(ciudad);
                    actor.setHeight(height);
                    actor.setChildren(hijos);*/

                    actor.setImagen(Conexion_Imagen.BitmapFromString(actor.getImage()));


                    actorL.add(actor);


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    public class conectaURLAsync extends AsyncTask<String, Void, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(JSON_Actores.this, "", "Descargando...");
        }

        @Override
        protected String doInBackground(String... urls) {

            JSON = HTTPconex(urls[0]);
            long tiempo = System.currentTimeMillis();
            parseJSON(JSON);
            long tiempoF = System.currentTimeMillis();

            return ((tiempoF - tiempo) + " ms");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            adap = new Adaptador_Actores(actorL, getApplicationContext());
            rv.setAdapter(adap);
            pd.hide();

        }
    }
}
