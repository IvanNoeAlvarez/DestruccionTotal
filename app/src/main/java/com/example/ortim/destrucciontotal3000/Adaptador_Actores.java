package com.example.ortim.destrucciontotal3000;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static com.example.ortim.destrucciontotal3000.Adaptador_Actores.ActoresViewHolder.PREF;

/**
 * Created by ortim on 24/01/2017.
 */

public class Adaptador_Actores extends RecyclerView.Adapter<Adaptador_Actores.ActoresViewHolder> {
    private List<Actores> actorL;
    Context context;
    LinearLayout linear;

    public Adaptador_Actores(List<Actores> actorL, Context context) {
        this.actorL = actorL;
        this.context = context;
    }

    public class ActoresViewHolder extends RecyclerView.ViewHolder {
        public  TextView nombre;
        public TextView desc;
        public TextView dob;
        public ImageView img;
        public static final String PREF = "preferencias";
        final int pantalla1 =1;


        public ActoresViewHolder(View v) {
            super(v);
            nombre = (TextView)v.findViewById(R.id.name);
            desc = (TextView)v.findViewById(R.id.desc);
            dob = (TextView)v.findViewById(R.id.dob);
            img = (ImageView)v.findViewById(R.id.img);


            linear = (LinearLayout)v.findViewById(R.id.linear);
        }
    }

    @Override
    public ActoresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_actores,parent,false);
        return new ActoresViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ActoresViewHolder holder, final int position) {

        holder.nombre.setText(this.actorL.get(position).getName());
        holder.dob.setText(this.actorL.get(position).getDob());
        holder.desc.setText(this.actorL.get(position).getDescription());
        holder.img.setImageBitmap(actorL.get(position).getImagen());

        // accion sobree el linearLayout para que el sharedPreference actue
        linear.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        SharedPreferences preferences = context.getSharedPreferences(PREF, Activity.MODE_PRIVATE);

        // instanciar editor que podra recoger los datos
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("country",actorL.get(position).getCountry());
        editor.putString("height",actorL.get(position).getHeight());
        editor.putString("spouse",actorL.get(position).getSpouse());
        editor.putString("children",actorL.get(position).getChildren());
        editor.commit();

        Intent i = new Intent(context.getApplicationContext(),InfoActores.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(i);
    }
});


    }

    @Override
    public int getItemCount() {
        return actorL.size();
    }


}
