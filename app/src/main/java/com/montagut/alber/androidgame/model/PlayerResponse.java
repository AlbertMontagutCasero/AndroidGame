package com.montagut.alber.androidgame.model;

import java.util.List;

public class PlayerResponse {

    private String error;
    private List<DataPlayer> ranking;


    public PlayerResponse(String error, List<DataPlayer> ranking) {
        this.error = error;
        this.ranking = ranking;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<DataPlayer> getRanking() {
        return ranking;
    }

    public void setRanking(List<DataPlayer> ranking) {
        this.ranking = ranking;
    }

    public List<DataPlayer> getranking() {
        return ranking;
    }

    public void setranking(List<DataPlayer> ranking) {
        this.ranking = ranking;
    }
}

/*

    "error": 0,
    "message": "OK",
    "data": {
        "id": "1",
        "name": "Snake",
        "description": "Snake game",
        "image_path": "http://stucom.flx.cat/imgs/snake_game.png",
        "ranking": [
        ]
    }
 */