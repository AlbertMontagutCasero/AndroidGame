package com.montagut.alber.androidgame.model;

public class RankingUser {
    private String id;
    private int score;
    private User user;

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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
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