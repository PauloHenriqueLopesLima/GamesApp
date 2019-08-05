package com.example.gamesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gamesapp.R;
import com.example.gamesapp.adapter.listener.GameListListener;
import com.example.gamesapp.model.Game;
import com.example.gamesapp.model.GameImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    private List<Game> gameList = new ArrayList<>();
    private GameListListener gameListListener;

    public GamesAdapter(GameListListener gameListListener) {
        this.gameListListener = gameListListener;
    }

    public void atualizarGames(List<Game> gameList) {
        this.gameList = gameList;
        notifyDataSetChanged();
    }

    public void adicionarGames() {
        this.gameList.addAll(gameList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game game = gameList.get(position);
        holder.bind(game);
        holder.itemView.setOnClickListener(view -> gameListListener.onGameClick(game));

    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tituloTextView;
        private TextView descricaoTextView;
        private ImageView fotoImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tituloTextView = itemView.findViewById(R.id.titulo_text_view_id);
            descricaoTextView = itemView.findViewById(R.id.descricao_text_view);
            fotoImageView = itemView.findViewById(R.id.game_image_view_id);
        }

        public void bind(Game game) {

            tituloTextView.setText(game.getTitulo());
            descricaoTextView.setText(game.getDescricao());

            Picasso.get()
                    .load(game.getImagem().getOriginal_url())
                    .into(fotoImageView);


        }


    }
}
