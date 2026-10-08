package com.example.dam_lab;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";
    private int counter = 0;

    private TextView tvCounter; //initialize an object of TextView, so we can change the text view's property in code
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

        Log.i(TAG, "from onCreate");

        //toasts - little pop-ups
        Toast.makeText(this, "DAM LAB", Toast.LENGTH_SHORT).show(); //Toast.makeText(context, string, duration)
        Toast.makeText(getApplicationContext(), getString(R.string.greeting), Toast.LENGTH_SHORT).show(); //getApplicationContext() = this in this context

        tvCounter = findViewById(R.id.tvCounter); //find the tvCounter by it's id


        Button btnIncrement = findViewById(R.id.btnIncrement);
        btnIncrement.setOnClickListener(v -> {
            counter++;
            updateCounter();
        })
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "from onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "from onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "from onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "from onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "from onDestroy");
    }
}