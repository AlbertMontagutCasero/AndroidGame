package com.montagut.alber.androidgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.montagut.alber.androidgame.Task.PlayerTask;
import com.montagut.alber.androidgame.model.DataGame;
import com.montagut.alber.androidgame.model.GameResponse;
import com.montagut.alber.androidgame.model.RankingUser;
import com.montagut.alber.androidgame.model.PlayerResponse;
import com.montagut.alber.androidgame.model.User;
import com.squareup.picasso.Picasso;

public class RankingPlayersForGameActivity extends AppCompatActivity
        implements PlayerTask.OnPlayerListener, PlayerAdapter.OnItemClickListener
{

    private SwipeRefreshLayout srlPlayers;
    private RecyclerView rvPlayers;
    private ImageView ivGameImage;
    private TextView tvGameTittle, tvGameDescription;
    private DataGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //find references
        setContentView(R.layout.activity_ranking_players_game);
        srlPlayers = findViewById(R.id.srlPlayers);
        rvPlayers = findViewById(R.id.rvPlayers);
        ivGameImage = findViewById(R.id.ivGameImage);
        tvGameTittle = findViewById(R.id.tvGameTittle);
        tvGameDescription = findViewById(R.id.tvGameDescription);

        //get values from the ranking class intent
        Intent intent = getIntent();
        String json = intent.getStringExtra("game");
        Gson gson = new Gson();
        game = gson.fromJson(json, DataGame.class);
        tvGameDescription.setText(game.getDescription());
        tvGameTittle.setText(game.getName());
        Picasso.with(this).load(game.getAvatarImage()).into(this.ivGameImage);
        updatePlayer();

        srlPlayers.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updatePlayer();
            }
        });
    }

    public void updatePlayer(){
        PlayerTask task = new PlayerTask();
        task.setOnPlayerListener(this);
        task.execute(game.getId());

    }

    @Override
    public void updated(PlayerResponse playerResponse) {
        PlayerAdapter adapter = new PlayerAdapter(playerResponse);
        srlPlayers.setRefreshing(false);
        rvPlayers.setLayoutManager(new LinearLayoutManager(RankingPlayersForGameActivity.this));
        rvPlayers.setItemAnimator(new DefaultItemAnimator());
        rvPlayers.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }


    /**
     * Called when item on the recycle view gets clicked
     * @param view
     * @param player
     */
    @Override
    public void itemClicked(View view, User player) {
        Intent intent = new Intent(this, RankingPersonalUser.class);
        Gson gson = new Gson();
        String json = gson.toJson(player);
        intent.putExtra("user", json);
        startActivity(intent);
    }

}


