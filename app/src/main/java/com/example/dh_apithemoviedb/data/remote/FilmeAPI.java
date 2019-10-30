package com.example.dh_apithemoviedb.data.remote;

import com.example.dh_apithemoviedb.model.FilmeResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FilmeAPI {

    @GET("movie/now_playing")
    Observable<FilmeResult> getAllFilmes(@Query("api_key") String apiKey);

//    Para mais parâmtros:
//    Observable<FilmeResult> getAllFilmes(@Query("api_key") String apiKey,
//                                         @Path("language") String lingua);

}
