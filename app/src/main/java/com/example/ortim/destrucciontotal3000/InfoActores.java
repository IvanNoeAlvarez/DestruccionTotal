package com.example.ortim.destrucciontotal3000;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.ortim.destrucciontotal3000.Adaptador_Actores.ActoresViewHolder.PREF;

public class InfoActores extends AppCompatActivity {
    TextView origen;
    TextView altura;
    TextView parienta;
    TextView hijos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_actores);

        origen = (TextView)findViewById(R.id.ori);
        altura = (TextView)findViewById(R.id.alt);
        parienta = (TextView)findViewById(R.id.par);
        hijos = (TextView)findViewById(R.id.hij);

        SharedPreferences preferences = getSharedPreferences(PREF, Activity.MODE_PRIVATE);

        String origenS = preferences.getString("country","");
        String alturaS = preferences.getString("height","");
        String parientaS = preferences.getString("spouse","");
        String hijosS = preferences.getString("children","");

        origen.setText(origenS);
        altura.setText(alturaS);
        parienta.setText(parientaS);
        hijos.setText(hijosS);


    }
}
