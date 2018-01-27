package com.montagut.alber.androidgame;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.montagut.alber.androidgame.model.DataGame;
import com.montagut.alber.androidgame.model.GameResponse;

public class Ranking extends AppCompatActivity implements GameTask.OnGameListener, GameAdapter.OnItemClickListener{

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
        GameTask task = new GameTask();
        task.setOnGameListener(this);
        task.execute();

    }

    @Override
    public void updated(GameResponse gameResponse) {
        srlGame.setRefreshing(false);
        GameAdapter adapter = new GameAdapter(gameResponse);
        rvGame.setLayoutManager(new LinearLayoutManager(Ranking.this));
        rvGame.setItemAnimator(new DefaultItemAnimator());
        rvGame.setAdapter(adapter);
    }


    @Override
    public void itemClicked(View view, DataGame game) {
        Intent intent = new Intent(this, RankingPlayersForGame.class);

        Gson gson = new Gson();
        String json = gson.toJson(game);
        Log.d("flx", "JSON = " + json);
        intent.putExtra("set", json);
        startActivity(intent);
    }
}
