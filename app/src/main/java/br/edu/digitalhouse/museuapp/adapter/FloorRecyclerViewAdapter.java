package br.edu.digitalhouse.museuapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.digitalhouse.museuapp.Interfaces.ListClickListener;
import br.edu.digitalhouse.museuapp.R;
import br.edu.digitalhouse.museuapp.model.floorrequest.Gallery;

public class FloorRecyclerViewAdapter extends RecyclerView.Adapter<FloorRecyclerViewAdapter.ViewHolder> {

    private List<Gallery> galleryList;
    private ListClickListener listener;

    public List<Gallery> getGalleryList() {
        return galleryList;
    }

    public FloorRecyclerViewAdapter(List<Gallery> galleryList, ListClickListener listener) {
        this.galleryList = galleryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_floor_item, viewGroup, false);

        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Gallery gallery = galleryList.get(i);

        viewHolder.bind(gallery);

    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public void update(List<Gallery> records) {
        if (this.galleryList.isEmpty()) {
            this.galleryList = records;
        } else {
            this.galleryList.addAll(records);
        }
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView roomNumber;
        private TextView category;
        private TextView roomName;
        private ListClickListener listener;


        public ViewHolder(@NonNull View itemView, ListClickListener listener) {
            super(itemView);

            this.listener = listener;

            itemView.setOnClickListener(this);

            roomNumber = itemView.findViewById(R.id.floor_item_room_id);
            roomName = itemView.findViewById(R.id.floor_item_name_id);
            category = itemView.findViewById(R.id.floor_item_category_id);
        }

        public void bind(final Gallery gallery) {
            roomNumber.setText(gallery.getGalleryNumber());

            if (gallery.getTheme() != null) {
                roomName.setText(gallery.getTheme());
                category.setText(gallery.getName());
            } else {
                roomName.setText(gallery.getName());
                category.setText("");
            }

        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }
}
