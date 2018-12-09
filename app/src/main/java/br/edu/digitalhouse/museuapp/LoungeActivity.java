package br.edu.digitalhouse.museuapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.edu.digitalhouse.museuapp.model.galleryrequest.Item;

public class LoungeActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button goGallery;
    private Button editGallery;
    private TextView userEmail;
    private TextView logout;
    private TextView deleteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lounge);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        userEmail = findViewById(R.id.txt_user_email);
        logout = findViewById(R.id.txt_click_logout);
        deleteUser = findViewById(R.id.txt_click_delete);
        goGallery = findViewById(R.id.btn_personal_gallery);
        editGallery = findViewById(R.id.btn_edit_personal);

        firebaseAuth = FirebaseAuth.getInstance();

        userEmail.setText(firebaseAuth.getCurrentUser().getEmail());

        goGallery.setOnClickListener(v -> {
            Intent intent = new Intent(LoungeActivity.this, GalleryActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("number", "Personal Gallery");
            bundle.putString("name", "Your own art exhibition");
            bundle.putString("category", "");
            bundle.putString("description", "A collection of beautiful pieces, gathered during this user's tour on this museum");
            bundle.putBoolean("personal", true);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        editGallery.setOnClickListener(view -> {
            Intent intent = new Intent(LoungeActivity.this, EditActivity.class);
            startActivity(intent);
        });

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
