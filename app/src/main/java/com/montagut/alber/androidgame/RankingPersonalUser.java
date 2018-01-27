package com.montagut.alber.androidgame;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.montagut.alber.androidgame.Task.UserTask;
import com.montagut.alber.androidgame.model.DataGame;
import com.montagut.alber.androidgame.model.User;
import com.montagut.alber.androidgame.model.UserResponse;
import com.squareup.picasso.Picasso;

public class RankingPersonalUser extends AppCompatActivity
        implements UserTask.OnUserListener, UserAdapter.OnItemClickListener
{
    private SwipeRefreshLayout srlUsers;
    private RecyclerView rvUsers;
    private ImageView ivUserImage;
    private TextView tvUsername, tvFirstName, tvLastName, tvEmail;
    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_personal_user);
        srlUsers = findViewById(R.id.srlUsers);
        rvUsers = findViewById(R.id.rvUsers);
        ivUserImage = findViewById(R.id.ivUserImage);
        tvUsername = findViewById(R.id.tvUsername);
        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        tvEmail = findViewById(R.id.tvEmail);

        //get values from the ranking class intent
        Intent intent = getIntent();
        String json = intent.getStringExtra("user");
        Log.d("Rai", "onCreate: " + json);
        Gson gson = new Gson();
        user = gson.fromJson(json, User.class);
        Log.d("Rai", "onCreate: " + user.getUserName());
        tvUsername.setText(user.getUserName());
        tvFirstName.setText(user.getFirstName());
        tvLastName.setText(user.getLastName());
        tvEmail.setText(user.getEmail());
        Picasso.with(this).load(user.getAvatarPath()).into(this.ivUserImage);
        updateUser();

        srlUsers.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateUser();
            }
        });
    }


    public void updateUser(){
        UserTask task = new UserTask();
        task.setOnUserListener(this);
        task.execute(user.getId());

    }

    @Override
    public void updated(UserResponse userResponse) {
        UserAdapter adapter = new UserAdapter(userResponse);
        srlUsers.setRefreshing(false);
        rvUsers.setLayoutManager(new LinearLayoutManager(RankingPersonalUser.this));
        rvUsers.setItemAnimator(new DefaultItemAnimator());
        rvUsers.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    /**
     * Called when item on the recycle view gets clicked
     * @param view
     * @param game
     */
    @Override
    public void itemClicked(View view, DataGame game) {
        //todo mandar a la vista del juego en question
        Intent intent = new Intent(this, RankingPlayersForGameActivity.class);
        Gson gson = new Gson();
        String json = gson.toJson(game);
        intent.putExtra("game", json);
        startActivity(intent);
    }
}
