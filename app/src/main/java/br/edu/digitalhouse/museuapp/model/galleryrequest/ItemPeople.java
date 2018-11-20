package br.edu.digitalhouse.museuapp.model.galleryrequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemPeople implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;

    public ItemPeople(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
