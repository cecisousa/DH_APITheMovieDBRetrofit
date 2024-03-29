package com.example.dh_apithemoviedb.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dh_apithemoviedb.R;
import com.example.dh_apithemoviedb.model.Result;
import com.example.dh_apithemoviedb.view.interfaces.OnClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmeRecyclerViewAdapter extends RecyclerView.Adapter<FilmeRecyclerViewAdapter.ViewHolder> {
    private List<Result> listaResults;
    private OnClick listener;

    public FilmeRecyclerViewAdapter (List<Result> listaResults, OnClick listener) {
        this.listaResults = listaResults;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Result result = listaResults.get(position);
        holder.onBind(result);
        holder.itemView.setOnClickListener(v -> listener.click(result));
    }

    @Override
    public int getItemCount() {
        return listaResults.size();
    }

    public void atualizaLista(List<Result> novaLista) {
//        this.listaResults.clear();
//        this.listaResults = novaLista;
//        notifyDataSetChanged();
        if (this.listaResults.isEmpty()) {
            this.listaResults = novaLista;
        } else {
            this.listaResults.addAll(novaLista);
        }
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

        public void onBind (Result result) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + result.getPosterPath()).into(imagem);
            texto.setText(result.getTitle());
        }
    }
}
