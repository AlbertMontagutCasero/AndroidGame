package com.montagut.alber.androidgame.model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataGame {
    private String id;
    private String name;
    private String description;
    @SerializedName("image_path")
    private String avatarImage;
    @SerializedName("ranking")
    private List<RankingUser> rankingUsers;

    public List<RankingUser> getRankingUsers() {
        return rankingUsers;
    }

    public void setRankingUsers(List<RankingUser> rankingUsers) {
        this.rankingUsers = rankingUsers;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAvatarImage() {
        return avatarImage;
    }
    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }
}

/*
            "id": "1",
            "name": "Snake",
            "description": "Snake game",
            "image_path": "http://stucom.flx.cat/imgs/snake_game.png"
        }
 */