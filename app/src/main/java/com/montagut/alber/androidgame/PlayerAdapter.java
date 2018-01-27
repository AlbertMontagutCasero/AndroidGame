package com.montagut.alber.androidgame;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.montagut.alber.androidgame.model.DataPlayer;
import com.montagut.alber.androidgame.model.PlayerResponse;

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
        DataPlayer player;
        ImageView ivPlayerImage;
        TextView tvUsername , tvFirstName, tvLastName, tvScore, tvEmail;

        public ViewHolder(View itemView)
        {
            super(itemView);
            ivPlayerImage = itemView.findViewById(R.id.ivPlayerImage);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvScore = itemView.findViewById(R.id.tvUsername);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            tvEmail = itemView.findViewById(R.id.tvEmail);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if (playerResponse == null) return;
                    if (listener != null) listener.itemClicked(view, player);
                }
            });
        }
    }


    private PlayerAdapter.OnItemClickListener listener;
    public void setOnItemClickListener(PlayerAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    interface OnItemClickListener {
        void itemClicked(View view, DataPlayer player);
    }



    @Override
    public PlayerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_item, parent, false);
        return new PlayerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlayerAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
