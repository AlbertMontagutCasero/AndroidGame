package com.montagut.alber.androidgame;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.montagut.alber.androidgame.model.GameResponse;

public class GameAddapter extends RecyclerView.Adapter<GameAddapter.ViewHolder>{

    private GameResponse gameResponse;
    public GameAddapter(GameResponse gameResponse){
        super();
        this.gameResponse = gameResponse;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
