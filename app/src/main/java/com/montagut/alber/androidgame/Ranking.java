package com.montagut.alber.androidgame;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.montagut.alber.androidgame.model.DataGame;
import com.montagut.alber.androidgame.model.GameResponse;

public class Ranking extends AppCompatActivity implements GamesTask.OnGameListener{

    private SwipeRefreshLayout srlGame;
    private RecyclerView rvGame;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        srlGame = findViewById(R.id.srlGame);
        rvGame = findViewById(R.id.rvGame);
        updateGames();

        srlGame.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateGames();
            }
        });

    }

    public void updateGames(){
        GamesTask task = new GamesTask();
        task.setOnGameListener(this);
        task.execute();

    }

    @Override
    public void updated(GameResponse gameResponse) {
        srlGame.setRefreshing(false);
        GameAddapter adapter = new GameAddapter(gameResponse);
        rvGame.setLayoutManager(new LinearLayoutManager(Ranking.this));
        rvGame.setItemAnimator(new DefaultItemAnimator());
        rvGame.setAdapter(adapter);
    }
}
