package com.montagut.alber.androidgame.model;

import java.util.List;

public class GameResponse {
    private List<DataGame> data;

    public List<DataGame> getData() {
        return data;
    }

    public void setData(List<DataGame> data) {
        this.data = data;
    }
}

/*
    "error": 0,
    "message": "OK",
    "data": [
        {

    ]
 */