package com.montagut.alber.androidgame;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static android.content.ContentValues.TAG;

/**
 * Created by alber on 17/01/2018.
 */

class GamesTask extends AsyncTask<Void , Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String url = "http://stucom.flx.cat/game/api/game";
            InputStream is =  (new URL(url)).openConnection().getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String json = "";
            String line;
            while ((line = reader.readLine()) != null) json += line;
            is.close();

            Log.d("Raikish ", "doInBackground: " + json);
        } catch (IOException e) {
            e.printStackTrace();
        }


        /**
         *  GET game Retorna una llista de jocs
         *  GET game/<id> Retorna la informació d’un joc concret
         */

        return null;
    }


}
