package com.montagut.alber.androidgame.model;


import com.google.gson.annotations.SerializedName;

public class RankingUser {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    @SerializedName("avatar_path")
    private String avatarPath;


    public RankingUser(String id, String username, String firstName, String lastName, String email, String avatarPath) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.avatarPath = avatarPath;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAvatarPath() {
        return avatarPath;
    }
    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}

/*

                    "id": "3",
                    "username": "cristian",
                    "firstname": "Cristian",
                    "lastname": "Catal\u00e1n",
                    "email": "cristian.catalan@stucom.com",
                    "avatar_path": "http://stucom.flx.cat/avatars/7.jpg"
 */