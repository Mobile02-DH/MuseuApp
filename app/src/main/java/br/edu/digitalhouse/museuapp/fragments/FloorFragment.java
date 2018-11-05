package br.edu.digitalhouse.museuapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.edu.digitalhouse.museuapp.R;
import br.edu.digitalhouse.museuapp.adapter.FloorRecyclerViewAdapter;
import br.edu.digitalhouse.museuapp.model.Gallery;

public class FloorFragment extends Fragment {

    private List<Gallery> galleries = new ArrayList<>();
    private RecyclerView recyclerView;
    private FloorRecyclerViewAdapter adapter;

    public FloorFragment() {
    }

    public static FloorFragment newInstance(String floor) {

        Bundle args = new Bundle();
        args.putString("floor", floor);

        FloorFragment fragment = new FloorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_floor, container, false);

        for (int i = 0; i < 9; i++) {
            galleries.add(new Gallery(getString(R.string.example_room_number), 11, 11, "foo", 11, getString(R.string.example_room_name), getString(R.string.example_room_category), 11));
        }

        adapter = new FloorRecyclerViewAdapter(galleries);

        recyclerView = view.findViewById(R.id.recycler_view_floors_id);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String floor = getArguments().getString("floor");

        return view;
    }

}
