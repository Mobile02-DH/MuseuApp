package br.edu.digitalhouse.museuapp.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.edu.digitalhouse.museuapp.R;

public class GalleryInfoFragment extends Fragment {

    private TextView name;
    private TextView category;
    private TextView description;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    public GalleryInfoFragment() {
    }

    public static GalleryInfoFragment newInstance(Bundle bundle) {

        GalleryInfoFragment fragment = new GalleryInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery_info, container, false);

        name = view.findViewById(R.id.gallery_name);
        category = view.findViewById(R.id.gallery_category);
        description = view.findViewById(R.id.gallery_description);

        firebaseAuth = FirebaseAuth.getInstance();

        if (getArguments().getBoolean("personal")) {

            name.setText("Loading...");
            description.setText("Loading...");
            category.setText(getArguments().getString("category"));
            setViewPersonal();

        } else {

            setViewNotPersonal();
        }

        return view;
    }

    private void setViewPersonal() {

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseAuth.getCurrentUser().getUid());

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String personalName = ((String) dataSnapshot.child("PersonalName").getValue());
                String personalDesc = ((String) dataSnapshot.child("PersonalDesc").getValue());

                if (personalName == null || personalName.isEmpty()) {
                    name.setText(getArguments().getString("name"));
                } else {
                    name.setText(personalName);
                }

                if (personalDesc == null || personalDesc.isEmpty()) {
                    description.setText(getArguments().getString("description"));
                } else {
                    description.setText(personalDesc);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("DATABASE", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    private void setViewNotPersonal() {
        if (getArguments().getString("name") != null) {
            name.setText(getArguments().getString("name"));
            category.setText(getArguments().getString("category"));
        } else {
            name.setText(getArguments().getString("category"));
            category.setText(getArguments().getString("name"));
        }

        if (getArguments().getString("description") != null) {
            description.setText(getArguments().getString("description"));
        } else {
            description.setText("No description available.");
        }
    }

}
