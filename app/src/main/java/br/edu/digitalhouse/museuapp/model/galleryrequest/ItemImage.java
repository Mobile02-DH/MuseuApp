package br.edu.digitalhouse.museuapp.model.galleryrequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemImage {

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
