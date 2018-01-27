package com.montagut.alber.androidgame;

import android.content.Context;
import android.os.AsyncTask;
import com.google.gson.Gson;
import com.montagut.alber.androidgame.model.GameResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

class GameTask extends AsyncTask<Void , Void, GameResponse>
{

    private OnGameListener listener;
    public void setOnGameListener(OnGameListener listener){
        this.listener = listener;
    }
    public interface OnGameListener {
        void updated(GameResponse gameResponse);
    }


    @Override
    protected GameResponse doInBackground(Void... voids) {
        try {
            String url = "http://stucom.flx.cat/game/api/game";
            InputStream is =  (new URL(url)).openConnection().getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String json = "";
            String line;
                while ((line = reader.readLine()) != null) json += line;
                is.close();

            Gson gson = new Gson();
            return gson.fromJson(json, GameResponse.class);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(GameResponse gameResponse) {
        if(listener != null){
            listener.updated(gameResponse);
        }
    }



}
