package br.edu.digitalhouse.museuapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.digitalhouse.museuapp.Interfaces.ListClickListener;
import br.edu.digitalhouse.museuapp.Interfaces.ServiceListener;
import br.edu.digitalhouse.museuapp.ItemActivity;
import br.edu.digitalhouse.museuapp.R;
import br.edu.digitalhouse.museuapp.adapter.GalleryRecyclerViewAdapter;
import br.edu.digitalhouse.museuapp.model.dao.GalleryDao;
import br.edu.digitalhouse.museuapp.model.galleryrequest.Item;
import br.edu.digitalhouse.museuapp.model.galleryrequest.ItemImage;
import br.edu.digitalhouse.museuapp.model.galleryrequest.ItemPeople;
import br.edu.digitalhouse.museuapp.model.galleryrequest.ItemResponse;

public class GalleryItemListFragment extends Fragment implements ServiceListener {

    private RecyclerView recyclerView;
    private GalleryRecyclerViewAdapter adapter;
    private String gallery;
    private GalleryDao galleryDao = new GalleryDao();
    private ItemResponse itemResponse;

    public GalleryItemListFragment() {
    }

    public static GalleryItemListFragment newInstance (Bundle bundle) {

        Bundle args = bundle;

        GalleryItemListFragment fragment = new GalleryItemListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery_item_list, container, false);

        gallery = getArguments().getString("number");
        galleryDao.getItems(getContext(), this, gallery);

        adapter = new GalleryRecyclerViewAdapter(new ArrayList<Item>(), new ListClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), ItemActivity.class);
                intent.putExtra("item", adapter.getItemList().get(position));
                startActivity(intent);
            }
        });

        recyclerView = view.findViewById(R.id.recycler_view_items);
        /*recyclerView.setHasFixedSize(true);*/
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        return view;
    }

    @Override
    public void onSucess(Object object) {

        itemResponse = (ItemResponse) object;
        adapter.update(itemResponse.getRecords());
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(getContext(), "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
