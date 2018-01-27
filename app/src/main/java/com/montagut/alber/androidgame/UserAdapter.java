package com.montagut.alber.androidgame;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.montagut.alber.androidgame.model.DataGame;
import com.montagut.alber.androidgame.model.RankingGame;
import com.montagut.alber.androidgame.model.UserResponse;
import com.squareup.picasso.Picasso;

class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>
{
    private UserResponse userResponse;
    public UserAdapter(UserResponse userResponse){
        super();
        this.userResponse = userResponse;
        for (RankingGame game: userResponse.getData().getRanking()) {
            Log.d("Rai", "UserAdapter: " + game.getScore());
        }
    }

        /**
         * this allow us to fill the information of the row layout
         */
    class ViewHolder extends RecyclerView.ViewHolder
    {
        DataGame game;
        ImageView ivGameImage;
        TextView tvTittleGame , tvDescriptionGame, tvScore;

        public ViewHolder(View itemView)
        {
            super(itemView);
            ivGameImage = itemView.findViewById(R.id.ivGameImage);
            tvTittleGame = itemView.findViewById(R.id.tvGameTittle);
            tvDescriptionGame = itemView.findViewById(R.id.tvGameDescription);
            tvScore = itemView.findViewById(R.id.tvScore);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if (userResponse == null) return;
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


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (userResponse == null) return;
        // i do this because i need to set up in the holder the game that will be
        // returned to the view on click
        holder.game =  userResponse.getData().getRanking().get(position).getGame();
        Context context = holder.ivGameImage.getContext();


        // use the holder for fill the row image on the recycle view
        // with image, game tittle & game description
        Picasso.with(context).load(holder.game.getAvatarImage()).into(holder.ivGameImage);
        holder.tvTittleGame.setText(holder.game.getName());
        holder.tvDescriptionGame.setText(holder.game.getDescription());
        holder.tvScore.setText(String.valueOf(userResponse.getData().getRanking().get(position).getScore()));
    }

    @Override
    public int getItemCount() {
        if (userResponse == null) return 0;
        return userResponse.getData().getRanking().size();
    }
}
