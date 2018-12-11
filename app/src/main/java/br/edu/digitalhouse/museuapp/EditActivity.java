package br.edu.digitalhouse.museuapp;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {

    private TextInputEditText personalName;
    private TextInputEditText personalDescription;
    private Button personalSave;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseAuth.getCurrentUser().getUid());

        personalName = findViewById(R.id.txt_personal_name);
        personalDescription = findViewById(R.id.txt_personal_description);
        personalSave = findViewById(R.id.btn_personal_save);

        personalSave.setOnClickListener(view -> {

            mDatabase.child("PersonalName").setValue(personalName.getText().toString());
            mDatabase.child("PersonalDesc").setValue(personalDescription.getText().toString())
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(EditActivity.this, "Gallery information updated!", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(EditActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        Log.e("SAVE", e.getMessage());
                    });
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
