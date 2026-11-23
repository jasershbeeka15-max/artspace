package com.example.artspace;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_CURRENT_INDEX = "current_index";

    private TextView tvCounter, tvTitle, tvArtistYear;
    private ImageView ivArtwork;
    private Button btnPrevious, btnNext;

    private int currentIndex = 0;

    private final int[] artworkImages = {
            R.drawable.art1,
            R.drawable.art2,
            R.drawable.art3,
            R.drawable.art4,
            R.drawable.art5,
            R.drawable.art6
    };

    private final String[] artworkTitles = {
            "Deep Field Stellar Deaths",
            "Cosmic Pillars of Light",
            "Pillars of Creation",
            "Spiral Galaxy Cluster",
            "Nebula Star Nursery",
            "Distant Quasar Horizon"
    };

    private final String[] artworkArtists = {
            "NASA / James Webb",
            "NASA / Hubble",
            "NASA / Webb",
            "ESA / Hubble",
            "NASA / Spitzer",
            "ESA / Webb"
    };

    private final String[] artworkYears = {
            "2022",
            "2021",
            "2022",
            "2020",
            "2019",
            "2023"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = findViewById(R.id.tvCounter);
        ivArtwork = findViewById(R.id.ivArtwork);
        tvTitle = findViewById(R.id.tvTitle);
        tvArtistYear = findViewById(R.id.tvArtistYear);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX, 0);
        }

        updateArtworkUI();

        btnNext.setOnClickListener(v -> {
            if (currentIndex < artworkImages.length - 1) {
                currentIndex++;
                updateArtworkUI();
            }
        });

        btnPrevious.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateArtworkUI();
            }
        });

        ivArtwork.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ArtworkDetailActivity.class);
            intent.putExtra("EXTRA_TITLE", artworkTitles[currentIndex]);
            intent.putExtra("EXTRA_ARTIST", artworkArtists[currentIndex]);
            intent.putExtra("EXTRA_YEAR", artworkYears[currentIndex]);
            startActivity(intent);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX, 0);
        updateArtworkUI();
    }

    private void updateArtworkUI() {
        tvCounter.setText((currentIndex + 1) + " / " + artworkImages.length);
        ivArtwork.setImageResource(artworkImages[currentIndex]);
        tvTitle.setText(artworkTitles[currentIndex]);
        tvArtistYear.setText(artworkArtists[currentIndex] + " (" + artworkYears[currentIndex] + ")");

        btnPrevious.setEnabled(currentIndex > 0);
        btnNext.setEnabled(currentIndex < artworkImages.length - 1);
    }
}