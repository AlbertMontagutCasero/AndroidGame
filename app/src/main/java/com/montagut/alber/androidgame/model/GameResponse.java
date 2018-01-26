package com.montagut.alber.androidgame.model;

import java.util.List;

public class GameResponse {
    private String error;
    private String message;
    private List<DataGame> data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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