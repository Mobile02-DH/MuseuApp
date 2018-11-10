package br.edu.digitalhouse.museuapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GalleryResponse {

    @SerializedName("info")
    @Expose
    private GalleryResults info;

    @SerializedName("records")
    @Expose
    private List<Gallery> records;

    public GalleryResponse(GalleryResults info, List<Gallery> records) {
        this.info = info;
        this.records = records;
    }

    public GalleryResults getInfo() {
        return info;
    }

    public void setInfo(GalleryResults info) {
        this.info = info;
    }

    public List<Gallery> getRecords() {
        return records;
    }

    public void setRecords(List<Gallery> records) {
        this.records = records;
    }
}
