package com.montagut.alber.androidgame.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GameResponse {

    @SerializedName("data")
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