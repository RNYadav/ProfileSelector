package com.example.profileselector.model;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("results")
    @Expose
    private ArrayList<Profile> results = null;

    public ArrayList<Profile> getResults() {
        return results;
    }

    public void setResults(ArrayList<Profile> results) {
        this.results = results;
    }
}
