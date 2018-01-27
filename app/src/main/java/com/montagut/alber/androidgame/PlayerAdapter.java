package com.montagut.alber.androidgame;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.montagut.alber.androidgame.model.RankingUser;
import com.montagut.alber.androidgame.model.PlayerResponse;
import com.montagut.alber.androidgame.model.User;
import com.squareup.picasso.Picasso;

class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    private PlayerResponse playerResponse;
    public PlayerAdapter(PlayerResponse playerResponse){
        super();
        this.playerResponse = playerResponse;
    }

    /**
     * this allow us to fill the information of the row layout
     */
    class ViewHolder extends RecyclerView.ViewHolder
    {
        RankingUser player;
        ImageView ivPlayerImage;
        TextView tvUsername , tvFirstName, tvLastName, tvScore, tvEmail;

        public ViewHolder(View itemView)
        {
            super(itemView);
            ivPlayerImage = itemView.findViewById(R.id.ivPlayerImage);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            tvEmail = itemView.findViewById(R.id.tvEmail);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if (playerResponse == null) return;
                    if (listener != null) listener.itemClicked(view, player.getUser());
                }
            });
        }
    }


    private PlayerAdapter.OnItemClickListener listener;
    public void setOnItemClickListener(PlayerAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    interface OnItemClickListener {
        void itemClicked(View view, User player);
    }



    @Override
    public PlayerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_item, parent, false);
        return new PlayerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlayerAdapter.ViewHolder holder, int position) {
        if (playerResponse == null) return;
        // i do this because i need to set up in the holder the game that will be
        // returned to the view on click
        holder.player =  playerResponse.getData().getRankingUsers().get(position);
        Context context = holder.ivPlayerImage.getContext();

        Log.d("Rai", "onBindViewHolder: " + holder.player.getUser().getFirstName());
        // use the holder for fill the row image on the recycle view
        // with image, game tittle & game description
        Picasso.with(context).load(holder.player.getUser().getAvatarPath()).into(holder.ivPlayerImage);
        holder.tvUsername.setText(holder.player.getUser().getUserName());
        holder.tvFirstName.setText(holder.player.getUser().getFirstName());
        holder.tvLastName.setText(holder.player.getUser().getLastName());
        holder.tvScore.setText(String.valueOf(holder.player.getScore()));
        holder.tvEmail.setText(holder.player.getUser().getEmail());


    }

    @Override
    public int getItemCount() {
        return playerResponse.getData().getRankingUsers().size();
    }
}
