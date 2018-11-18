package br.edu.digitalhouse.museuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout textInputFirstName;
    private TextInputLayout textInputLastName;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textInputFirstName = findViewById(R.id.txt_first_name);
        textInputLastName = findViewById(R.id.txt_last_name);
        textInputEmail = findViewById(R.id.txt_email_register);
        textInputPassword = findViewById(R.id.txt_password_register);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textInputFirstName.getEditText().getText().toString().isEmpty()||
                        textInputLastName.getEditText().getText().toString().isEmpty()||
                        textInputEmail.getEditText().getText().toString().isEmpty()||
                        textInputPassword.getEditText().getText().toString().isEmpty()){

                    Toast.makeText(RegisterActivity.this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();

                } else if (!(textInputEmail.getEditText().getText().toString().contains("@"))){

                    Toast.makeText(RegisterActivity.this, R.string.invalid_email, Toast.LENGTH_SHORT).show();

                } else if (textInputPassword.getEditText().getText().toString().length() < 6){

                    Toast.makeText(RegisterActivity.this, R.string.short_password, Toast.LENGTH_SHORT).show();

                } else{

                    Toast.makeText(RegisterActivity.this, getString(R.string.welcome)+" "+textInputFirstName.getEditText().getText().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                }
            }
        });

    }
}
