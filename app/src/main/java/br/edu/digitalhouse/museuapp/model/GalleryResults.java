package br.edu.digitalhouse.museuapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GalleryResults {

    @SerializedName("totalrecordsperquery")
    @Expose
    private int TotalRecordsPerQuery;

    @SerializedName("totalrecords")
    @Expose
    private int TotalRecords;

    @SerializedName("pages")
    @Expose
    private int pages;

    @SerializedName("page")
    @Expose
    private int page;

    public GalleryResults(int totalRecordsPerQuery, int totalRecords, int pages, int page) {
        TotalRecordsPerQuery = totalRecordsPerQuery;
        TotalRecords = totalRecords;
        this.pages = pages;
        this.page = page;
    }

    public int getTotalRecordsPerQuery() {
        return TotalRecordsPerQuery;
    }

    public void setTotalRecordsPerQuery(int totalRecordsPerQuery) {
        TotalRecordsPerQuery = totalRecordsPerQuery;
    }

    public int getTotalRecords() {
        return TotalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        TotalRecords = totalRecords;
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
