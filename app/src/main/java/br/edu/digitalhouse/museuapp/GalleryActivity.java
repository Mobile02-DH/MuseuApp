package br.edu.digitalhouse.museuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import br.edu.digitalhouse.museuapp.fragments.GalleryInfoFragment;
import br.edu.digitalhouse.museuapp.fragments.GalleryItemListFragment;

public class GalleryActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private GalleryInfoFragment galleryInfoFragment;
    private GalleryItemListFragment galleryItemListFragment;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Intent intent = getIntent();

        galleryInfoFragment = GalleryInfoFragment.newInstance(intent.getExtras());
        galleryItemListFragment = new GalleryItemListFragment();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, galleryInfoFragment);
        fragmentTransaction.commit();

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

                    return true;

                case R.id.navigation_objects:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, galleryItemListFragment);
                    fragmentTransaction.commit();

                    return true;
            }
            return false;
        }
    };

}
