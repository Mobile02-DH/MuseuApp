package br.edu.digitalhouse.museuapp.model.floorrequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GalleryResults {

    @SerializedName("totalrecordsperquery")
    @Expose
    private int totalRecordsPerQuery;

    @SerializedName("totalrecords")
    @Expose
    private int totalRecords;

    @SerializedName("pages")
    @Expose
    private int pages;

    @SerializedName("page")
    @Expose
    private int page;

    public GalleryResults(int totalRecordsPerQuery, int totalRecords, int pages, int page) {
        this.totalRecordsPerQuery = totalRecordsPerQuery;
        this.totalRecords = totalRecords;
        this.pages = pages;
        this.page = page;
    }

    public int getTotalRecordsPerQuery() {
        return totalRecordsPerQuery;
    }

    public void setTotalRecordsPerQuery(int totalRecordsPerQuery) {
        this.totalRecordsPerQuery = totalRecordsPerQuery;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
