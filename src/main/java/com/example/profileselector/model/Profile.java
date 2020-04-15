package com.example.profileselector.model;

import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.profileselector.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

@Entity(tableName = "profile_table")
public class Profile {

    public Profile() {
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("gender")
    @Expose
    private String gender;
    @Ignore
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("cell")
    @Expose
    private String cell;

    private String picture;
    private String address;
    private String DisplayName;
    private String dob;
    private int status = 0;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

//    public Location getLocation() {
//        return location;
//    }
//
//    public void setLocation(Location location) {
//        this.location = location;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Dob getDob() {
//        return dob;
//    }
//
//    public void setDob(Dob dob) {
//        this.dob = dob;
//    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Profile(String gender, String picture, String address, String displayName, String dob) {
        this.gender = gender;
        this.picture = picture;
        this.address = address;
        DisplayName = displayName;
        this.dob = dob;
        this.status = 0;
    }

    public Profile jsonParser(JSONObject jsonObject){
        String picture = jsonObject.optJSONObject("picture").optString("large");
        String address = jsonObject.optJSONObject("location").optString("city")+", "+jsonObject.optJSONObject("location").optString("state")+"\n"+jsonObject.optJSONObject("location").optString("country");
        String name = jsonObject.optJSONObject("name").optString("first")+" "+jsonObject.optJSONObject("name").optString("last");
        String dob = jsonObject.optJSONObject("dob").optString("first");
        return new Profile(jsonObject.optString("gender"), picture, address, name, dob);
    }

    // important code for loading image here
    @BindingAdapter({ "avatar" })
    public static void loadImage(ImageView imageView, String imageURL) {
        Picasso.get().load(imageURL).placeholder(ContextCompat.getDrawable(imageView.getContext(), R.drawable.placeholder)).into(imageView);
    }

    public void updateStatus(int value){
        status = value;
    }

}

