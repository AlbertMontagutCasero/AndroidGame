package com.montagut.alber.androidgame;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.montagut.alber.androidgame.model.DataGame;
import com.montagut.alber.androidgame.model.GameResponse;
import com.squareup.picasso.Picasso;

import static android.support.v4.content.ContextCompat.startActivity;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder>{

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivGameImage;
        TextView tvTittleGame , tvDescriptionGame;

        public ViewHolder(View itemView) {
            super(itemView);
            ivGameImage = itemView.findViewById(R.id.ivGameImage);
            tvTittleGame = itemView.findViewById(R.id.tvGameTittle);
            tvDescriptionGame = itemView.findViewById(R.id.tvGameDescription);
        }
    }

    interface OnItemClickListener {
        void itemClicked(View view, DataGame game);
    }

    private GameResponse gameResponse;
    public GameAdapter(GameResponse gameResponse){
        super();
        this.gameResponse = gameResponse;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (gameResponse == null) return;
        DataGame game = gameResponse.getData().get(position);
        Context context = holder.ivGameImage.getContext();

        // fill row image on the recycle view with image, game tittle & game description
        Picasso.with(context).load(game.getAvatarImage()).into(holder.ivGameImage);
        holder.tvTittleGame.setText(game.getName());
        holder.tvDescriptionGame.setText(game.getDescription());
    }

    @Override
    public int getItemCount() {
        if(gameResponse == null) return 0;
        return gameResponse.getData().size();
    }

    interface OnItemClickListener {
        void itemClicked(View view, DataGame game);
    }
    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
