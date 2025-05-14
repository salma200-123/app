package com.example.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("auth", MODE_PRIVATE);
        boolean loggedIn = prefs.getBoolean("loggedIn", false);

        if (loggedIn) {
            startActivity(new Intent(this, com.example.app.QuizActivity.class));
        } else {
            startActivity(new Intent(this, com.example.app.LoginActivity.class));
        }

        finish();
    }
}
