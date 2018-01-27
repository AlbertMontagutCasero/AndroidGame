package com.montagut.alber.androidgame.Task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.montagut.alber.androidgame.model.UserResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class UserTask extends AsyncTask<String, Void, UserResponse> {

    private OnUserListener listener;
    public void setOnUserListener(OnUserListener listener){ this.listener = listener; }
    public interface OnUserListener {
        void updated(UserResponse userResponse);
    }

    @Override
    protected UserResponse doInBackground(String... strings) {
        String userID = strings[0];
        try
        {
            String url = "http://stucom.flx.cat/game/api/user/"+userID;
            Log.d("Rai", "doInBackground: " + url);
            InputStream is =  (new URL(url)).openConnection().getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String json = "";
            String line;
            while ((line = reader.readLine()) != null) json += line;
            is.close();
            Log.d("Rai", "doInBackground: " + json);
            Gson gson = new Gson();

            return gson.fromJson(json, UserResponse.class);
        } catch (IOException e){
            return null;
        }
    }

    @Override
    protected void onPostExecute(UserResponse userResponse) {
        if (listener != null){
            listener.updated(userResponse);
        }
    }
}
