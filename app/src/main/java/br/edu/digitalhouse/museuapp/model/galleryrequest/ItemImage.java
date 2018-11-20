package br.edu.digitalhouse.museuapp.model.galleryrequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemImage implements Serializable {

    @SerializedName("baseimageurl")
    @Expose
    private String ImageUrl;

    public ItemImage(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
