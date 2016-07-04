package com.jain.rakshit.instagramtrackerfinal.Rest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Datum extends RealmObject implements Serializable {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("profile_picture")

    @Expose
    private String profilePicture;
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("full_name")
    @Expose
    private String fullName;

    public Datum() {

    }

    public Datum(String username, String id, String profilePicture) {
        this.username = username;
        this.id = id;
        this.profilePicture = profilePicture;
    }

    /**
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The profilePicture
     */
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * @param profilePicture The profile_picture
     */
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName The full_name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}