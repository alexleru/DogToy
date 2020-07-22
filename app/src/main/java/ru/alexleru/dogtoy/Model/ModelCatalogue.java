package ru.alexleru.dogtoy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelCatalogue implements Serializable {
    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("picture")
    @Expose
    public String picture;

    @SerializedName("url")
    @Expose
    public String url;

    @SerializedName("size")
    @Expose
    public String size;

    @SerializedName("branch")
    @Expose
    public String branch;

    @SerializedName("country")
    @Expose
    public String country;

    @SerializedName("weight")
    @Expose
    public String weight;
}
