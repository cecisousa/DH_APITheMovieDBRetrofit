package com.example.dh_apithemoviedb.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dh_apithemoviedb.R;
import com.example.dh_apithemoviedb.model.Filme;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmeRecyclerViewAdapter extends RecyclerView.Adapter<FilmeRecyclerViewAdapter.ViewHolder> {
    private List<Filme> listaFilmes;

    public FilmeRecyclerViewAdapter (List<Filme> listaFilmes) {
        this.listaFilmes = listaFilmes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Filme filme = listaFilmes.get(position);
        holder.onBind(filme);
    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }

    public void atualizaLista(List<Filme> novaLista) {
        this.listaFilmes.clear();
        this.listaFilmes = novaLista;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagem;
        private TextView texto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagem = itemView.findViewById(R.id.imgFilme);
            texto = itemView.findViewById(R.id.txtTitulo);
        }

        public void onBind (Filme filme) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + filme.getPosterPath()).into(imagem);
            texto.setText(filme.getTitle());
        }
    }
}
