package com.example.s03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_TITLE = "MOVIE_TITLE";
    public static final String EXTRA_MOVIE_NUMBER = "MOVIE_NR";
    public static final String EXTRA_MOVIE_RATING = "MOVIE_RATING";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_movie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle extras = getIntent().getExtras(); //because the activity was started by an intent, we can use getIntent().getExtras() to extract the bundle
        int movieNumber = extras != null ? extras.getInt(EXTRA_MOVIE_NUMBER, 0):0;

        TextView tvMovieNumber = findViewById(R.id.tvMovieNumber);
        tvMovieNumber.setText(String.valueOf(movieNumber));

        //the plain texts
        EditText etMovieTitle= findViewById(R.id.etMovieTitle);
        EditText etMovieRating = findViewById(R.id.etRating);

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            //get input from the EditText
            String title = etMovieTitle.getText().toString().trim();
            String rating = etMovieRating.getText().toString().trim();

            if(title.isEmpty() || rating.isEmpty())
                return;

            int ratingNumber = Integer.parseInt(rating);

            if(ratingNumber > 10 || ratingNumber < 1){
                //set error
                etMovieRating.setError("Invalid value");
                return;
            }

            Intent data = new Intent();
            //set the data to the intent, but not in a bundle, directly in the intent
            data.putExtra(EXTRA_MOVIE_TITLE, title);
            data.putExtra(EXTRA_MOVIE_RATING, ratingNumber);

            setResult(RESULT_OK, data); //set the result of the intent
            finish();

        });

        findViewById(R.id.btnCancel).setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}