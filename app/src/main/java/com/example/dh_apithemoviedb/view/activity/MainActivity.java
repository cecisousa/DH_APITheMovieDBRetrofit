package com.example.dh_apithemoviedb.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.dh_apithemoviedb.R;
import com.example.dh_apithemoviedb.model.Result;
import com.example.dh_apithemoviedb.view.adapter.FilmeRecyclerViewAdapter;
import com.example.dh_apithemoviedb.view.interfaces.OnClick;
import com.example.dh_apithemoviedb.viewmodel.FilmeViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClick {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private FilmeViewModel viewModel;
    private List<Result> listaResults = new ArrayList<>();
    private FilmeRecyclerViewAdapter adapter;
    private int pagina = 1;
    public static final String API_KEY = "bde8033d3274c91b292a5293c6349052";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setScrollView();

        viewModel.getAllFilmes(API_KEY, pagina);

        viewModel.getListaFilme().observe(this, resultadoLista -> {
            adapter.atualizaLista(resultadoLista);
        });

        viewModel.getLoading().observe(this, loading -> {
            if (loading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    public void initViews() {
        recyclerView = findViewById(R.id.recyclerViewFilmes);
        progressBar = findViewById(R.id.progressBar);
        viewModel = ViewModelProviders.of(this).get(FilmeViewModel.class);
        adapter = new FilmeRecyclerViewAdapter(listaResults, this);
    }


    @Override
    public void click(Result result) {
        Intent intent = new Intent(MainActivity.this, DetalheActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("Result", result);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void setScrollView() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();
                boolean ultimoItem = lastVisible + 5 >= totalItemCount;

                if (totalItemCount > 0 && ultimoItem) {
                    pagina++;
                    viewModel.getAllFilmes(API_KEY, pagina);
                }
            }
        });
    }
}
