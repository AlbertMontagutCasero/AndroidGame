package com.montagut.alber.androidgame;

import android.os.AsyncTask;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by alber on 17/01/2018.
 */

class GamesTask extends AsyncTask<Void , Void, Void> { //TODO Preguntar flx AsyncTask || AsyncTask<Void , Void, Void>

    @Override
    protected Void doInBackground(Void... voids) {
        String url = "http://stucom.flx.cat/game/api/"; //TODO preguntar flx sobre lista de game en la api
        Log.d(TAG, "doInBackground: " + url);
        /**
         *  GET game Retorna una llista de jocs
         *  GET game/<id> Retorna la informació d’un joc concret
         */

        return null;
    }


}
