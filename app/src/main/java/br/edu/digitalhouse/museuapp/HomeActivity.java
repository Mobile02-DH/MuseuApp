package br.edu.digitalhouse.museuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.edu.digitalhouse.museuapp.adapter.FloorMapPageAdapter;
import br.edu.digitalhouse.museuapp.adapter.FloorListPageAdapter;
import br.edu.digitalhouse.museuapp.fragments.FloorFragment;
import br.edu.digitalhouse.museuapp.fragments.MapFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private FloorListPageAdapter floorListPageAdapter;
    private FloorMapPageAdapter floorMapPageAdapter;
    private LinearLayout menuHeader;
    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Galleries");

        setFabListenerWhenOnListDisplay();
        configureDrawerLayout();
        configureViewPager();
        setLoginClickListener(menuHeader);

        if (getIntent().getExtras() != null) {
            onNavigationItemSelected(getIntent().getExtras().getInt("id"));
        }
    }

    private void setLoginClickListener(View view){
        view.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), LoginActivity.class)));
    }

    private void configureViewPager() {

        floorListPageAdapter =  new FloorListPageAdapter(getSupportFragmentManager(), getDetailsFragmentList());
        floorMapPageAdapter = new FloorMapPageAdapter(getSupportFragmentManager(), getMapsFragmentList());
        viewPager.setAdapter(floorListPageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.setOffscreenPageLimit(floorListPageAdapter.getCount());
    }

    private void configureDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setFabListenerWhenOnListDisplay() {
        fab.setOnClickListener(view -> {
            int tab = viewPager.getCurrentItem();
            viewPager.setAdapter(floorMapPageAdapter);
            viewPager.setCurrentItem(tab);
            viewPager.setOffscreenPageLimit(floorMapPageAdapter.getCount());
            setFabListenerWhenOnMapDisplay();
        });
    }

    private void setFabListenerWhenOnMapDisplay(){
        fab.setOnClickListener(view -> {
            int tab = viewPager.getCurrentItem();
            viewPager.setAdapter(floorListPageAdapter);
            viewPager.setCurrentItem(tab);
            setFabListenerWhenOnListDisplay();
        });
    }

    private void initViews(){
        toolbar = findViewById(R.id.toolbar_item);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.container);
        fab = findViewById(R.id.fab_item);

        headerView = navigationView.getHeaderView(0);
        menuHeader = headerView.findViewById(R.id.drawer_menu_header);
    }

    private List<Fragment> getDetailsFragmentList() {
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(FloorFragment.newInstance(1));
        fragments.add(FloorFragment.newInstance(2));
        fragments.add(FloorFragment.newInstance(3));


        return fragments;
    }

    private List<Fragment> getMapsFragmentList() {
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(MapFragment.newInstance(1));
        fragments.add(MapFragment.newInstance(2));
        fragments.add(MapFragment.newInstance(3));

        return fragments;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_floor_1) {
            viewPager.setCurrentItem(0);

        } else if (id == R.id.nav_floor_2) {
            viewPager.setCurrentItem(1);

        } else if (id == R.id.nav_floor_3) {
            viewPager.setCurrentItem(2);

        } else if (id == R.id.nav_myGallery) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean onNavigationItemSelected(int id) {

        if (id == 0) {
            viewPager.setCurrentItem(0);

        } else if (id == 1) {
            viewPager.setCurrentItem(1);

        } else if (id == 2) {
            viewPager.setCurrentItem(2);

        } else if (id == R.id.nav_myGallery) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
