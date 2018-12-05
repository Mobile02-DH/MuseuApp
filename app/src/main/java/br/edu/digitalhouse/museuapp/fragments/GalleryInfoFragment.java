package br.edu.digitalhouse.museuapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.edu.digitalhouse.museuapp.R;

public class GalleryInfoFragment extends Fragment {

    private TextView name;
    private TextView category;
    private TextView description;

    public GalleryInfoFragment() {
    }

    public static GalleryInfoFragment newInstance (Bundle bundle) {

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

        if (getArguments().getString("name") != null){
            name.setText(getArguments().getString("name"));
            category.setText(getArguments().getString("category"));
        } else {
            name.setText(getArguments().getString("category"));
            category.setText(getArguments().getString("name"));
        }

        if (getArguments().getString("description") != null){
            description.setText(getArguments().getString("description"));
        } else {
            description.setText("No description available.");
        }

        return view;
    }

}
