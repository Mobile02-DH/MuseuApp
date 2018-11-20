package br.edu.digitalhouse.museuapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.digitalhouse.museuapp.GalleryActivity;
import br.edu.digitalhouse.museuapp.Interfaces.ListClickListener;
import br.edu.digitalhouse.museuapp.Interfaces.ServiceListener;
import br.edu.digitalhouse.museuapp.R;
import br.edu.digitalhouse.museuapp.adapter.FloorRecyclerViewAdapter;
import br.edu.digitalhouse.museuapp.model.dao.FloorDao;
import br.edu.digitalhouse.museuapp.model.floorrequest.Gallery;
import br.edu.digitalhouse.museuapp.model.floorrequest.GalleryResponse;

public class FloorFragment extends Fragment implements ServiceListener {

    private GalleryResponse galleryResponse;
    private RecyclerView recyclerView;
    private FloorRecyclerViewAdapter adapter;
    private FloorDao floorDao = new FloorDao();
    private int floor;

    public FloorFragment() {
    }

    public static FloorFragment newInstance(int floor) {

        Bundle args = new Bundle();
        args.putInt("floor", floor);

        FloorFragment fragment = new FloorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_floor, container, false);

        floor = getArguments().getInt("floor");
        floorDao.getGalleries(getContext(), this, floor);

        adapter = new FloorRecyclerViewAdapter(new ArrayList<Gallery>(), new ListClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), GalleryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("number", adapter.getGalleryList().get(position).getGalleryNumber());
                bundle.putString("name", adapter.getGalleryList().get(position).getTheme());
                bundle.putString("category", adapter.getGalleryList().get(position).getName());
                bundle.putString("description", adapter.getGalleryList().get(position).getLabelText());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        recyclerView = view.findViewById(R.id.recycler_view_floors_id);
        /*recyclerView.setHasFixedSize(true);*/
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onSucess(Object object) {

        galleryResponse = (GalleryResponse) object;
        adapter.update(galleryResponse.getRecords());
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(getContext(), "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
