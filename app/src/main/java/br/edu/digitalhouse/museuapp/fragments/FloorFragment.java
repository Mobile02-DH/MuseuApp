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
import java.util.List;

import br.edu.digitalhouse.museuapp.GalleryActivity;
import br.edu.digitalhouse.museuapp.Interfaces.ListClickListener;
import br.edu.digitalhouse.museuapp.Interfaces.ServiceListener;
import br.edu.digitalhouse.museuapp.R;
import br.edu.digitalhouse.museuapp.adapter.FloorRecyclerViewAdapter;
import br.edu.digitalhouse.museuapp.model.floorrequest.Gallery;
import br.edu.digitalhouse.museuapp.model.floorrequest.GalleryResponse;
import br.edu.digitalhouse.museuapp.model.dao.FloorDao;

public class FloorFragment extends Fragment implements ServiceListener {

    private GalleryResponse galleryResponse;
    private RecyclerView recyclerView;
    private FloorRecyclerViewAdapter adapter;
    private int totalPages = 1;
    private int page = 1;
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

       /* for (int i = 0; i < 9; i++) {
            galleries.add(new Gallery(getString(R.string.example_room_number), 11, 11, "foo", 11, getString(R.string.example_room_name), getString(R.string.example_room_category), 11));
        }*/

        floor = getArguments().getInt("floor");

        floorDao.getGalleries(getContext(), this, floor, page);

        adapter = new FloorRecyclerViewAdapter(new ArrayList<Gallery>(), new ListClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "Galeria: "+ adapter.getGalleryList().get(position).getGalleryNumber(), Toast.LENGTH_SHORT).show();
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
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onSucess(Object object) {
        while (page <= totalPages) {
            galleryResponse = (GalleryResponse) object;
            totalPages = galleryResponse.getInfo().getPages();
            adapter.update(galleryResponse.getRecords());
            page++;
            if (page <= totalPages) {
                floorDao.getGalleries(getContext(), this, floor, page);
            }
        }
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(getContext(), "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
