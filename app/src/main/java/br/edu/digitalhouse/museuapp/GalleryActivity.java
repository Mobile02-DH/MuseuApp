package br.edu.digitalhouse.museuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import br.edu.digitalhouse.museuapp.fragments.GalleryInfoFragment;
import br.edu.digitalhouse.museuapp.fragments.GalleryItemListFragment;

public class GalleryActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private GalleryInfoFragment galleryInfoFragment;
    private GalleryItemListFragment galleryItemListFragment;
    private FragmentTransaction fragmentTransaction;
    private Bundle bundle;
    private int marker = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Intent intent = getIntent();
        bundle = intent.getExtras();
        if (bundle.getBoolean("personal")){
            getSupportActionBar().setTitle(bundle.getString("number"));
        } else{
            getSupportActionBar().setTitle("Room "+bundle.getString("number"));
        }

        galleryInfoFragment = GalleryInfoFragment.newInstance(bundle);
        galleryItemListFragment = GalleryItemListFragment.newInstance(bundle);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, galleryInfoFragment);
        fragmentTransaction.commit();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (bundle.getBoolean("personal") && marker == 2){
            galleryItemListFragment = GalleryItemListFragment.newInstance(bundle);
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, galleryItemListFragment);
            fragmentTransaction.commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_info:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, galleryInfoFragment);
                    fragmentTransaction.commit();
                    marker = 1;

                    return true;

                case R.id.navigation_objects:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, galleryItemListFragment);
                    fragmentTransaction.commit();
                    marker = 2;

                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_user_home) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            return true;
        }

        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
