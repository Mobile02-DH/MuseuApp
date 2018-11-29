package br.edu.digitalhouse.museuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import br.edu.digitalhouse.museuapp.Interfaces.ListClickListener;
import br.edu.digitalhouse.museuapp.adapter.ItemImageRecyclerViewAdapter;
import br.edu.digitalhouse.museuapp.model.galleryrequest.Item;
import br.edu.digitalhouse.museuapp.model.galleryrequest.ItemImage;
import br.edu.digitalhouse.museuapp.model.galleryrequest.ItemPeople;

public class ItemActivity extends AppCompatActivity {

    private Item item;
    private List<ItemImage> imageList;
    private RecyclerView recyclerView;
    private ItemImageRecyclerViewAdapter adapter;
    private TextView title;
    private TextView artist;
    private TextView dated;
    private TextView division;
    private TextView culture;
    private TextView classification;
    private TextView medium;
    private TextView description;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Toolbar toolbar = findViewById(R.id.toolbar_item);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Item Details");

        firebaseAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        item = (Item) intent.getSerializableExtra("item");
        imageList = item.getImages();

        adapter = new ItemImageRecyclerViewAdapter(imageList, (view, position) -> {
            Intent intentOut = new Intent(getApplicationContext(), ImageActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("imageUrl", imageList.get(position).getImageUrl());
            intentOut.putExtras(bundle);
            startActivity(intentOut);
        });

        recyclerView = findViewById(R.id.recyclerview_item_image_list);
        /*recyclerView.setHasFixedSize(true);*/
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        title = findViewById(R.id.txt_item_details_title);
        artist = findViewById(R.id.txt_item_details_artist);
        dated = findViewById(R.id.txt_item_details_dated);
        division = findViewById(R.id.txt_item_details_division);
        culture = findViewById(R.id.txt_item_details_culture);
        classification = findViewById(R.id.txt_item_details_classification);
        medium = findViewById(R.id.txt_item_details_medium);
        description = findViewById(R.id.txt_item_details_description);

        try {
            if (item.getPeople().size() == 1){
                artist.setText(item.getPeople().get(0).getName());
            } else {
                StringBuilder people = new StringBuilder();
                for (ItemPeople itemPeople: item.getPeople()){
                    people.append(itemPeople.getName() + "; ");
                }
            }
        } catch (Exception e){
            artist.setText("Unknown Author");
        }

        title.setText(item.getTitle());
        dated.setText("Dated: "+item.getDated());
        division.setText("Division: "+item.getDivision());
        culture.setText("Culture: "+item.getCulture());
        classification.setText("Classification: "+item.getClassification());
        medium.setText("Medium: "+item.getMedium());
        if (item.getLabelText() == null){
            description.setText("Description: No description available.");
        } else {
            description.setText("Description: "+item.getLabelText());
        }


        FloatingActionButton fab = findViewById(R.id.fab_item);
        fab.setOnClickListener(view -> {

            if (firebaseAuth.getCurrentUser() != null){

                mDatabase.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("items").child(((Integer)item.getObjectId()).toString())
                        .setValue(item)
                        .addOnSuccessListener(aVoid -> Snackbar.make(view, "Object added to personal gallery", Snackbar.LENGTH_LONG).show())
                        .addOnFailureListener(e -> Toast.makeText(ItemActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show());

            } else {

                Toast.makeText(ItemActivity.this, "You need to be logged in to add items to your personal gallery", Toast.LENGTH_LONG).show();
            }
        });
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

        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
