package ru.alexleru.dogtoy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelPrecautionary {
    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("picture")
    @Expose
    public String picture;

    @SerializedName("color")
    @Expose
    public String colors;
}
