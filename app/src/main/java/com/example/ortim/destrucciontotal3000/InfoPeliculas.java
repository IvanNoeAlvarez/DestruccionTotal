package com.example.ortim.destrucciontotal3000;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoPeliculas extends AppCompatActivity {
    TextView director;
    TextView duracion;
    TextView story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peliculas_info);
        director = (TextView)findViewById(R.id.director);
        duracion = (TextView)findViewById(R.id.duracion);
        story = (TextView)findViewById(R.id.story);

        String n = getIntent().getStringExtra("story");
        String y = getIntent().getStringExtra("duration");
        String x = getIntent().getStringExtra("director");
        director.setText(n);
        duracion.setText(y);
        story.setText(x);

    }
}
