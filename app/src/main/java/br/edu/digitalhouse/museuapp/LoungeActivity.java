package br.edu.digitalhouse.museuapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LoungeActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextView userEmail;
    private TextView logout;
    private TextView deleteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lounge);

        userEmail = findViewById(R.id.txt_user_email);
        logout = findViewById(R.id.txt_click_logout);
        deleteUser = findViewById(R.id.txt_click_delete);

        firebaseAuth = FirebaseAuth.getInstance();
        userEmail.setText(firebaseAuth.getCurrentUser().getEmail());

        logout.setOnClickListener(v -> {
            firebaseAuth.signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        deleteUser.setOnClickListener(v -> {
            firebaseAuth.getCurrentUser().delete();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

    }
}
