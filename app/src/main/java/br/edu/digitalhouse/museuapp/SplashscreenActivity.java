package br.edu.digitalhouse.museuapp;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        printKeyHash();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                showHome(null);
            }
        }, 3000);

    }

    private void showHome(View view) {
        startActivity(new Intent(SplashscreenActivity.this, MainActivity.class));
        finish();
    }

    private void printKeyHash(){
        try{
            PackageInfo info = getPackageManager().getPackageInfo("br.edu.digitalhouse.museuapp", PackageManager.GET_SIGNATURES);
            for (Signature signature:info.signatures){

                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }
}