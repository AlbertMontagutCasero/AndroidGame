package com.montagut.alber.androidgame.model;

public class PlayerResponse {

    private DataGame data;
    public DataGame getData() {
        return data;
    }
    public void setData(DataGame data) {
        this.data = data;
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