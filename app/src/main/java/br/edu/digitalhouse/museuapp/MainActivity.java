package br.edu.digitalhouse.museuapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView visitMuseum;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visitMuseum = findViewById(R.id.tv_visit);

        image1 = findViewById(R.id.btn_level1);
        image2 = findViewById(R.id.btn_level2);
        image3 = findViewById(R.id.btn_level3);

        visitMuseum.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        });

        image1.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", 0);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });

        image2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", 1);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });

        image3.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", 2);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });

    }
}
