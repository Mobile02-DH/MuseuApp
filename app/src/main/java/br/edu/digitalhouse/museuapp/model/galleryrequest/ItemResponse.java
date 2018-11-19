package br.edu.digitalhouse.museuapp.model.galleryrequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemResponse {

    @SerializedName("info")
    @Expose
    private ItemInfo info;

    @SerializedName("records")
    @Expose
    private List<Item> records;

    public ItemResponse(ItemInfo info, List<Item> records) {
        this.info = info;
        this.records = records;
    }

    public ItemInfo getInfo() {
        return info;
    }

    public void setInfo(ItemInfo info) {
        this.info = info;
    }

    public List<Item> getRecords() {
        return records;
    }

    public void setRecords(List<Item> records) {
        this.records = records;
    }
}
