package com.montagut.alber.androidgame;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class Ranking extends AppCompatActivity {

    private SwipeRefreshLayout srlGame;
    private RecyclerView rvGame;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        srlGame = findViewById(R.id.srlGame);
        rvGame = findViewById(R.id.rvGame);

       /* srlGame.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateGames();
            }
        });
*/
    }

    public void updateGames(){
        GamesTask task = new GamesTask();
        task.execute();

    }

}
