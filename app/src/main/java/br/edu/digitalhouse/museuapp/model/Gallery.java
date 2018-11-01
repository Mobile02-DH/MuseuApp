package br.edu.digitalhouse.museuapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gallery {

    @SerializedName("gallerynumber")
    @Expose
    private String galleryNumber;

    @SerializedName("objectcount")
    @Expose
    private int objectCount;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("lastupdate")
    @Expose
    private String lastUpdate;

    @SerializedName("floor")
    @Expose
    private int floor;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("theme")
    @Expose
    private String theme;

    @SerializedName("galleryid")
    @Expose
    private int galleryId;

    public Gallery(String galleryNumber, int objectCount, int id, String lastUpdate, int floor, String name, String theme, int galleryId) {
        this.galleryNumber = galleryNumber;
        this.objectCount = objectCount;
        this.id = id;
        this.lastUpdate = lastUpdate;
        this.floor = floor;
        this.name = name;
        this.theme = theme;
        this.galleryId = galleryId;
    }

    public String getGalleryNumber() {
        return galleryNumber;
    }

    public void setGalleryNumber(String galleryNumber) {
        this.galleryNumber = galleryNumber;
    }

    public int getObjectCount() {
        return objectCount;
    }

    public void setObjectCount(int objectCount) {
        this.objectCount = objectCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }
}
