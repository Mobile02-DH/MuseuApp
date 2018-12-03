package br.edu.digitalhouse.museuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private Button buttonLogin;
    private SignInButton buttonLoginGoogle;
    private TextView register;
    private TextInputEditText txtEmail;
    private TextInputEditText txtPassword;
    private FirebaseAuth firebaseAuth;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Login");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.button_login);
        buttonLoginGoogle = findViewById(R.id.login_google);
        register = findViewById(R.id.textview_register_now);
        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener((view -> {
            String email_value = txtEmail.getText().toString();
            String password_value = txtPassword.getText().toString();

            if (TextUtils.isEmpty(email_value)) {
                Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password_value)) {
                Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.signInWithEmailAndPassword(email_value, password_value).addOnCompleteListener((task -> {
                if (task.isSuccessful()) {
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    Toast.makeText(getApplicationContext(), "Welcome back!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }));
        }));

        register.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(LoginActivity.this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        buttonLoginGoogle.setOnClickListener(view -> signIn());

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), LoungeActivity.class));
            finish();
        }

    }

    private void signIn() {
        Intent signIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                authWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("Googlebase","Google sign in failed", e);
            }
        }
    }

    private void authWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), "Connection failed", Toast.LENGTH_SHORT).show();
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
