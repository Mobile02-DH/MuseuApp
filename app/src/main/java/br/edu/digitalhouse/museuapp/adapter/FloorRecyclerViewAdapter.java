package br.edu.digitalhouse.museuapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.digitalhouse.museuapp.R;
import br.edu.digitalhouse.museuapp.model.Gallery;

public class FloorRecyclerViewAdapter extends RecyclerView.Adapter<FloorRecyclerViewAdapter.ViewHolder>{

    private List<Gallery> galleryList;

    public FloorRecyclerViewAdapter(List<Gallery> galleryList) {
        this.galleryList = galleryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_floor_item, viewGroup, false);

        return new ViewHolder(view);
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

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView roomNumber;
        private TextView category;
        private TextView roomName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roomNumber = itemView.findViewById(R.id.floor_item_room_id);
            category = itemView.findViewById(R.id.floor_item_name_id);
            roomName = itemView.findViewById(R.id.floor_item_category_id);
        }

        public void bind(final Gallery gallery){
            roomNumber.setText(gallery.getGalleryNumber());
            category.setText(gallery.getName());
            roomName.setText(gallery.getTheme());
        }
    }
}
