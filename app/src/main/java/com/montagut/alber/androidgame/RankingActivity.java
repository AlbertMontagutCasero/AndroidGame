package com.montagut.alber.androidgame;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.montagut.alber.androidgame.Task.GameTask;
import com.montagut.alber.androidgame.model.DataGame;
import com.montagut.alber.androidgame.model.GameResponse;

public class RankingActivity extends AppCompatActivity
        implements GameTask.OnGameListener, GameAdapter.OnItemClickListener
{
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
        GameAdapter adapter = new GameAdapter(gameResponse);
        srlGame.setRefreshing(false);
        rvGame.setLayoutManager(new LinearLayoutManager(RankingActivity.this));
        rvGame.setItemAnimator(new DefaultItemAnimator());
        rvGame.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    /**
     * Called when item on the recycle view gets clicked
     * @param view
     * @param game
     */
    @Override
    public void itemClicked(View view, DataGame game) {
        Intent intent = new Intent(this, RankingPlayersForGameActivity.class);
        Gson gson = new Gson();
        String json = gson.toJson(game);
        intent.putExtra("game", json);

       /* the next code is another way to do the same
        * intent.putExtra("id", game.getId());
        * intent.putExtra("image", game.getAvatarImage());
        * intent.putExtra("tittle", game.getName());
        * intent.putExtra("description", game.getDescription());
        */
        startActivity(intent);
    }

}
