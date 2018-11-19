package br.edu.digitalhouse.museuapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.edu.digitalhouse.museuapp.R;

public class GalleryInfoFragment extends Fragment {

    private TextView number;
    private TextView name;
    private TextView category;
    private TextView description;

    public GalleryInfoFragment() {
    }

    public static GalleryInfoFragment newInstance (Bundle bundle) {

        Bundle args = bundle;

        GalleryInfoFragment fragment = new GalleryInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery_info, container, false);

        number = view.findViewById(R.id.gallery_number);
        name = view.findViewById(R.id.gallery_name);
        category = view.findViewById(R.id.gallery_category);
        description = view.findViewById(R.id.gallery_description);

        number.setText("Room "+ getArguments().getString("number"));
        name.setText(getArguments().getString("name"));
        category.setText(getArguments().getString("category"));
        description.setText(getArguments().getString("description"));

        return view;
    }

}
