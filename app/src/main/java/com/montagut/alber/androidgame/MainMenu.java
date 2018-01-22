package com.montagut.alber.androidgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {
    Button play;
    Button ranking;
    Button surprise;
    Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        play = (Button) findViewById(R.id.play);
        ranking = (Button) findViewById(R.id.ranking);
        surprise = (Button) findViewById(R.id.surprise);
        settings = (Button) findViewById(R.id.settings);


        settings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this,Settings.class));
            }
        });


        ranking.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                startActivity(new Intent(MainMenu.this,Ranking.class));
            }
        });


        surprise.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this,Surprise.class));
            }
        });

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this,Play.class));
            }
        });


    }

}
/*
        @Override
        protected void onPause(){

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor ed = prefs.edit();
            String name = textView.getText().toString();
            ed.putString("name", name);
            ed.commit();
            super.onPause();
        }

        @Override
        protected void onResume(){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String name = prefs.getString("name" , "");
            textView.setText(name);
            editText.setText(name);
            super.onResume();
        }

*/


