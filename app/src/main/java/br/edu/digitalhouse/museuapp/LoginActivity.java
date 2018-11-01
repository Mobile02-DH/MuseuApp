package br.edu.digitalhouse.museuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inivcialização das View, linkando com o XML
        Button botaoLogin = findViewById(R.id.button_login);
        TextView textViewRegisterNow = findViewById(R.id.textview_register_now);
        final TextInputEditText email = findViewById(R.id.txt_email);
        final TextInputEditText password = findViewById(R.id.txt_password);

        //Clique no botão de login,onde iremos para a tela de "Home"
        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // criação ca intent que será enviado para proxima tela
                Intent intent = new Intent(v.getContext(), HomeActivity.class);

                // Criação do bunble que guardará as informação
                Bundle bundle = new Bundle();

                // Pega os dados que o usuário digitou
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();

                // validação do dados do usuário
                LoginManager loginManager = new LoginManager();
                String senha = loginManager.getSenhaPorUsuario(emailString);

                if (senha != null && passwordString.equals(senha)) {

                    // Iserindo os dados no bundle para envio
                    bundle.putString("email", emailString);
                    intent.putExtras(bundle);


                    // Iniciamos a chamada para a proxima activity passando a intenção
                    startActivity(intent);
                } else {

                    // Se os dados não forem validos mostramos uma menssagem para o usuário
                    Toast.makeText(LoginActivity.this, "Usuário e/ou senha inválido(s)", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Clique no text "Resgister Now" vamos para a tela de cadastro do usuario
        textViewRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Iniciamos a chamada para a activity de Registro passando uma intenção
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}
