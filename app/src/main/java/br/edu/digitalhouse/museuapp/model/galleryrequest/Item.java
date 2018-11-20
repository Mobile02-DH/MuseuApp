package br.edu.digitalhouse.museuapp.model.galleryrequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {

    //list of Artists (Claude Monet)
    @SerializedName("people")
    @Expose
    private List<ItemPeople> people;

    //original date of item (1877)
    @SerializedName("dated")
    @Expose
    private String dated;

    //list of item images (URLs)
    @SerializedName("images")
    @Expose
    private List<ItemImage> images;

    //ID (228649)
    @SerializedName("objectid")
    @Expose
    private int objectId;

    //where it came from (French)
    @SerializedName("culture")
    @Expose
    private String culture;

    //name of the item (The Gare Saint-Lazare: Arrival of a Train)
    @SerializedName("title")
    @Expose
    private String title;

    //category of the item (European and American Art)
    @SerializedName("division")
    @Expose
    private String division;

    //description of the item (This is the largest in Monet’s series of twelve paintings of...)
    @SerializedName("labeltext")
    @Expose
    private String labelText;

    //physical description of the item (Oil on canvas)
    @SerializedName("medium")
    @Expose
    private String medium;

    //what the item is (Paintings)
    @SerializedName("classification")
    @Expose
    private String classification;

    public Item(List<ItemPeople> people, String dated, List<ItemImage> images, int objectId, String culture, String title, String division, String labelText, String medium, String classification) {
        this.people = people;
        this.dated = dated;
        this.images = images;
        this.objectId = objectId;
        this.culture = culture;
        this.title = title;
        this.division = division;
        this.labelText = labelText;
        this.medium = medium;
        this.classification = classification;
    }

    public List<ItemPeople> getPeople() {
        return people;
    }

    public void setPeople(List<ItemPeople> people) {
        this.people = people;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public List<ItemImage> getImages() {
        return images;
    }

    public void setImages(List<ItemImage> images) {
        this.images = images;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
