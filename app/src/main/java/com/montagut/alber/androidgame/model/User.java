package com.montagut.alber.androidgame.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    private String id;
    @SerializedName("username")
    private String userName;
    @SerializedName("firstname")
    private String firstName;
    @SerializedName("lastname")
    private String lastName;
    @SerializedName("avatar_path")
    private String avatarPath;
    private String email;
    private List<RankingGame> ranking;

    public List<RankingGame> getRanking() {
        return ranking;
    }
    public void setRanking(List<RankingGame> ranking) {
        this.ranking = ranking;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
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