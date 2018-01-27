package com.montagut.alber.androidgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.montagut.alber.androidgame.model.DataGame;
import com.montagut.alber.androidgame.model.GameResponse;
import com.squareup.picasso.Picasso;

import static android.support.v4.content.ContextCompat.startActivity;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder>{


    private GameResponse gameResponse;
    public GameAdapter(GameResponse gameResponse){
        super();
        this.gameResponse = gameResponse;
    }

    /**
     * this allow us to fill the information of the row layout
     */
    class ViewHolder extends RecyclerView.ViewHolder
    {
        DataGame game;
        ImageView ivGameImage;
        TextView tvTittleGame , tvDescriptionGame;

        public ViewHolder(View itemView)
        {
            super(itemView);
            ivGameImage = itemView.findViewById(R.id.ivGameImage);
            tvTittleGame = itemView.findViewById(R.id.tvGameTittle);
            tvDescriptionGame = itemView.findViewById(R.id.tvGameDescription);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if (gameResponse == null) return;
                    Toast.makeText(ivGameImage.getContext(), game.getId(), Toast.LENGTH_LONG).show();
                    if (listener != null) listener.itemClicked(view, game);
                }
            });
        }
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    interface OnItemClickListener {
        void itemClicked(View view, DataGame game);
    }

    /**
     * inflate the correspondent layout with the fills.
     * @param parent
     * @param viewType
     * @return the view holder up to the correspondent view
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (gameResponse == null) return;
        // i do this because i need to set up in the holder the game that will be
        // returned to the view on click
        holder.game =  gameResponse.getData().get(position);
        Context context = holder.ivGameImage.getContext();


        // use the holder for fill the row image on the recycle view
        // with image, game tittle & game description
        Picasso.with(context).load(holder.game.getAvatarImage()).into(holder.ivGameImage);
        holder.tvTittleGame.setText(holder.game.getName());
        holder.tvDescriptionGame.setText(holder.game.getDescription());
    }

    @Override
    public int getItemCount() {
        if(gameResponse == null) return 0;
        return gameResponse.getData().size();
    }

}
