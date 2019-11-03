package com.example.dh_apithemoviedb.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dh_apithemoviedb.R;
import com.example.dh_apithemoviedb.model.Result;
import com.squareup.picasso.Picasso;

public class DetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        ImageView imageView = findViewById(R.id.imgDetalhe);

        if (getIntent() != null) {
            Result result = getIntent().getParcelableExtra("Result");
            Toast.makeText(this, "Filme: " + result.getTitle(), Toast.LENGTH_LONG).show();

            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + result.getPosterPath()).into(imageView);

        }
    }
}
