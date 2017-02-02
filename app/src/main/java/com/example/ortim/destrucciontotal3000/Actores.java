package com.example.ortim.destrucciontotal3000;

import android.graphics.Bitmap;

/**
 * Created by ortim on 24/01/2017.
 */

public class Actores {
    String name;
    String description;
    String dob;
    String country;
    String height;
    String spouse;
    String children;


    //Declararla como Bitmap ¡Importante!
    String image;
    Bitmap Imagen;

    public Actores() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public Bitmap getImagen() {
        return Imagen;
    }

    public void setImagen(Bitmap image) {
        Imagen = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
