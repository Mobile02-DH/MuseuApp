package br.edu.digitalhouse.museuapp.fragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import br.edu.digitalhouse.museuapp.R;

public class MapFragment extends Fragment {

    private ImageView mapView;


    public MapFragment() {
    }

    public static MapFragment newInstance(int floor) {

        Bundle args = new Bundle();
        args.putInt("floor", floor);

        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = view.findViewById(R.id.image_level1_map);
        mapView.setImageDrawable(selectImage(getArguments().getInt("floor")));

        return view;
    }

    private Drawable selectImage(int floor) {
        switch (floor) {
            case 1:
                return getContext().getResources().getDrawable(R.drawable.level1_map);

            case 2:
                return getContext().getResources().getDrawable(R.drawable.level2_map);

            case 3:
                return getContext().getResources().getDrawable(R.drawable.level3_map);

        }
        return null;
    }

}
