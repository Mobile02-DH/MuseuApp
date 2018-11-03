package br.edu.digitalhouse.museuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout textInputName;
    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputRepeatePassword;
    private Button btnRegisterNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textInputName = findViewById(R.id.text_input_name);
        textInputEmail = findViewById(R.id.text_input_email);
        textInputPassword = findViewById(R.id.text_input_password);
        textInputRepeatePassword = findViewById(R.id.text_input_repeate_password);
        btnRegisterNow = findViewById(R.id.btn_register_now);

        btnRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textInputName.getEditText().getText().toString().isEmpty()||
                        textInputEmail.getEditText().getText().toString().isEmpty()||
                        textInputPassword.getEditText().getText().toString().isEmpty()||
                        textInputRepeatePassword.getEditText().getText().toString().isEmpty()){

                    Toast.makeText(RegisterActivity.this, "Please fill all the required fields", Toast.LENGTH_SHORT).show();

                } else if (!(textInputEmail.getEditText().getText().toString().contains("@"))){

                    Toast.makeText(RegisterActivity.this, "Invalid e-mail address", Toast.LENGTH_SHORT).show();

                } else if (!(textInputPassword.getEditText().getText().toString().equals(
                        textInputRepeatePassword.getEditText().getText().toString()))){

                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();

                } else if (textInputPassword.getEditText().getText().toString().length() < 6){

                    Toast.makeText(RegisterActivity.this, "Password must have at least 6 characters", Toast.LENGTH_SHORT).show();

                } else{

                    Toast.makeText(RegisterActivity.this, "Welcome "+textInputName.getEditText().getText().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                }
            }
        });

    }
}
