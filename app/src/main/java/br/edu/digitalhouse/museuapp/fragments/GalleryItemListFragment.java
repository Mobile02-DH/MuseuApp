package br.edu.digitalhouse.museuapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.digitalhouse.museuapp.Interfaces.ListClickListener;
import br.edu.digitalhouse.museuapp.Interfaces.ServiceListener;
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
    private List<Item> itemList = new ArrayList<>();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery_item_list, container, false);


        /*List<ItemPeople> itemPeople = new ArrayList<>();
        itemPeople.add(new ItemPeople("bolota"));

        List<ItemImage> itemImages = new ArrayList<>();
        itemImages.add(new ItemImage("http://meioambiente.culturamix.com/blog/wp-content/gallery/a-bolota-do-carvalho-e-comestivel-5/A-Bolota-do-Carvalho-%C3%A9-Comest%C3%ADvel-13.jpg"));

        for (int i = 0; i < 11; i++) {
            itemList.add(new Item(itemPeople, "1500", itemImages, 1500, "hue", "bolota", "lol","hue", "lol", "lol"));
        }*/

        gallery = getArguments().getString("number");
        galleryDao.getItems(getContext(), this, gallery);

        adapter = new GalleryRecyclerViewAdapter(new ArrayList<Item>(), new ListClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "Opa!", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView = view.findViewById(R.id.recycler_view_items);
        recyclerView.setHasFixedSize(true);
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
