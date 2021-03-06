package com.montagut.alber.androidgame.Task;

import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
import com.montagut.alber.androidgame.model.PlayerResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class PlayerTask extends AsyncTask<String, Void, PlayerResponse> {

    private OnPlayerListener listener;
    public void setOnPlayerListener(OnPlayerListener listener){ this.listener = listener; }
    public interface OnPlayerListener {
        void updated(PlayerResponse playerResponse);
    }

    @Override
    protected PlayerResponse doInBackground(String... strings) {
        String gameID = strings[0];
        try
        {
            String url = "http://stucom.flx.cat/game/api/game/"+gameID;
            Log.d("Rai", "doInBackground: " + url);
            InputStream is =  (new URL(url)).openConnection().getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String json = "";
            String line;
            while ((line = reader.readLine()) != null) json += line;
            is.close();
            Log.d("Rai", "doInBackground: " + json);
            Gson gson = new Gson();

            return gson.fromJson(json, PlayerResponse.class);
        } catch (IOException e){
            return null;
        }
    }
    @Override
    protected void onPostExecute(PlayerResponse playerResponse) {
        if(listener != null){
            listener.updated(playerResponse);
        }
    }
}
