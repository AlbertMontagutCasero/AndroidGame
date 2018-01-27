package com.montagut.alber.androidgame.model;

import java.util.List;

public class DataPlayer {
    private String id;
    private int score;
    private List<RankingUser> user;

    public DataPlayer(String id, int score, List<RankingUser> user) {
        this.id = id;
        this.score = score;
        this.user = user;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<RankingUser> getUser() {
        return user;
    }

    public void setUser(List<RankingUser> user) {
        this.user = user;
    }
}

/*
{
                "id": "5",
                "user_id": "3",
                "game_id": "1",
                "score": "39480",
                "user": {

                }
 */