package br.edu.digitalhouse.museuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.util.Timer;
import java.util.TimerTask;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                showHome(null);
            }
        }, 3000);

    }

    private void showHome(View view) {
        startActivity(new Intent(SplashscreenActivity.this, MainActivity.class));
        finish();
    }
}