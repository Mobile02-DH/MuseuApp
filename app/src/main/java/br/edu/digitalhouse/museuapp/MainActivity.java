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
    private Intent intent;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visitMuseum = findViewById(R.id.tv_visit);

        image1 = findViewById(R.id.btn_level1);
        image2 = findViewById(R.id.btn_level2);
        image3 = findViewById(R.id.btn_level3);

        intent = new Intent(this, HomeActivity.class);
        bundle = new Bundle();

        visitMuseum.setOnClickListener(view -> {
            bundle.putInt("id", 0);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        image1.setOnClickListener(view -> {
            bundle.putInt("id", 0);
            intent.putExtras(bundle);
            startActivity(intent);

        });

        image2.setOnClickListener(view -> {
            bundle.putInt("id", 1);
            intent.putExtras(bundle);
            startActivity(intent);

        });

        image3.setOnClickListener(view -> {
            bundle.putInt("id", 2);
            intent.putExtras(bundle);
            startActivity(intent);

        });
    }
}
