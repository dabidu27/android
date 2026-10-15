package com.example.s03;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvMovies;
    private static final int REQUEST_ADD_MOVIE = 1;

    //save state
    private static final String KEY_MOVIES = "movies";

    private final ArrayList<String> movies = new ArrayList<>();
    private ArrayAdapter<String> adapter; //ties the listview to the actual Java array movies

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //check if we saved any state
        if(savedInstanceState != null){
            ArrayList<String> saved = savedInstanceState.getStringArrayList(KEY_MOVIES);
            if(saved != null)
                movies.addAll(saved);
        }

        lvMovies = findViewById(R.id.listViewMovie);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movies); //takes context, a layout resource and the array it ties to
        lvMovies.setAdapter(adapter); //set adapter to the listview

        findViewById(R.id.favAdd).setOnClickListener(v -> {
            Intent intent = new Intent(this, AddMovieActivity.class);
            Bundle extras = new Bundle(); //Bundle to send/receive data through the intent
            extras.putInt(AddMovieActivity.EXTRA_MOVIE_NUMBER, movies.size() + 1);

            intent.putExtras(extras);
            startActivityForResult(intent, REQUEST_ADD_MOVIE);
        });

        //implicit intent to launch the default browser of the phone
        findViewById(R.id.btnOpenWeb).setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://ase.ro")));
        });
    }

    //Override for startActivityForResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_ADD_MOVIE && resultCode == RESULT_OK && data != null){
            String title = data.getStringExtra(AddMovieActivity.EXTRA_MOVIE_TITLE);
            int rating = data.getIntExtra(AddMovieActivity.EXTRA_MOVIE_RATING, 0);
            if(title != null) {
                movies.add(title + " " + rating);
                adapter.notifyDataSetChanged();
            }
        }
    }

    //save the instance state to persist the array list
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(KEY_MOVIES, movies);
    }
}