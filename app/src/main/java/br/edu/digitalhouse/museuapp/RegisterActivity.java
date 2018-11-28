package br.edu.digitalhouse.museuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText textImputEmail;
    private TextInputEditText textImputPassword;
    private TextInputEditText textImputRepeatPassword;
    private TextView btnRegistra;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register");

        textImputEmail = findViewById(R.id.txt_email_register);
        textImputPassword = findViewById(R.id.txt_password_register);
        textImputRepeatPassword = findViewById(R.id.txt_password_confirm);
        btnRegistra = findViewById(R.id.btn_register);

        firebaseAuth = FirebaseAuth.getInstance();

        btnRegistra.setOnClickListener(v -> {

            if (textImputEmail.getText().toString().isEmpty() ||
                    textImputPassword.getText().toString().isEmpty() ||
                    textImputRepeatPassword.getText().toString().isEmpty()) {

                Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_LONG).show();

            } else if (!(textImputPassword.getText().toString().equals(textImputRepeatPassword.getText().toString()))) {

                Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();

            } else {

                firebaseAuth.createUserWithEmailAndPassword(textImputEmail.getText().toString(), textImputPassword.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
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
