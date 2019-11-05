package com.example.dh_apithemoviedb.repository;

import com.example.dh_apithemoviedb.model.FilmeResult;

import io.reactivex.Observable;

import static com.example.dh_apithemoviedb.data.remote.RetrofitService.getApiService;

public class FilmeRepository {

    public Observable<FilmeResult> getFilmes(String apiKey, int pagina) {
        return getApiService().getAllFilmes(apiKey, pagina);
    }
}
