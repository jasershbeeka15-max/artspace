package com.example.artspace;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ArtworkDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_detail);

        TextView tvDetailTitle = findViewById(R.id.tvDetailTitle);
        TextView tvDetailArtist = findViewById(R.id.tvDetailArtist);
        TextView tvDetailYear = findViewById(R.id.tvDetailYear);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnVisitWebsite = findViewById(R.id.btnVisitWebsite);

        Intent intent = getIntent();
        String title = intent.getStringExtra("EXTRA_TITLE");
        String artist = intent.getStringExtra("EXTRA_ARTIST");
        String year = intent.getStringExtra("EXTRA_YEAR");

        tvDetailTitle.setText("Title: " + (title != null ? title : ""));
        tvDetailArtist.setText("Artist: " + (artist != null ? artist : ""));
        tvDetailYear.setText("Year: " + (year != null ? year : ""));

        btnBack.setOnClickListener(v -> finish());

        btnVisitWebsite.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.nasa.gov");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });
    }
}