package com.example.ortim.destrucciontotal3000;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adaptador_Peliculas extends RecyclerView.Adapter<Adaptador_Peliculas.PeliculasViewHolder> {
    private List<Peliculas> peliculasL;
    Context context;


    public Adaptador_Peliculas(List<Peliculas> peliculasL, Context context) {
        this.peliculasL = peliculasL;
        this.context = context;
    }

    public class PeliculasViewHolder extends  RecyclerView.ViewHolder {
        public TextView nombre;
        public TextView anyo;
        public TextView rating;
        public ImageView img;
        public PeliculasViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            nombre = (TextView)itemView.findViewById(R.id.pelicula);
            anyo = (TextView)itemView.findViewById(R.id.anyo);
            rating = (TextView)itemView.findViewById(R.id.rating);
            img = (ImageView)itemView.findViewById(R.id.img);

        }
    }


    @Override
    public PeliculasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_peliculas,parent,false);

        return new PeliculasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PeliculasViewHolder holder, final int position) {

        holder.nombre.setText(this.peliculasL.get(position).getNombre());
        holder.anyo.setText(""+this.peliculasL.get(position).getAnyo());
        holder.rating.setText(""+this.peliculasL.get(position).getRating());
        holder.img.setImageBitmap(peliculasL.get(position).getImg());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context,InfoPeliculas.class);

                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle b= new Bundle();
                b.putString("story",peliculasL.get(position).getStory());
                b.putString("duration",peliculasL.get(position).getDuration());
                b.putString("director",peliculasL.get(position).getDirector());
                i.putExtras(b);
                context.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return peliculasL.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private String mItem;
        private TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            mTextView = (TextView) view;
        }

        public void setItem(String item) {
            mItem = item;
            mTextView.setText(item);
        }

        @Override
        public void onClick(View view) {
            Log.d("asdf ", "onClick " + getPosition() + " " + mItem);
            
        }
    }
}
