package br.edu.digitalhouse.museuapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.edu.digitalhouse.museuapp.Interfaces.ListClickListener;
import br.edu.digitalhouse.museuapp.R;
import br.edu.digitalhouse.museuapp.model.galleryrequest.ItemImage;

public class ItemImageRecyclerViewAdapter extends RecyclerView.Adapter<ItemImageRecyclerViewAdapter.ViewHolder> {

    private List<ItemImage> imageList;
    private ListClickListener listener;

    public ItemImageRecyclerViewAdapter(List<ItemImage> imageList, ListClickListener listener) {
        this.imageList = imageList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_image_item, viewGroup, false);

        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        ItemImage itemImage = imageList.get(i);

        viewHolder.bind(itemImage);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView itemImageLarge;
        private ListClickListener listener;

        public ViewHolder(@NonNull View itemView, ListClickListener listener) {

            super(itemView);

            this.listener = listener;
            itemView.setOnClickListener(this);

            itemImageLarge = itemView.findViewById(R.id.img_item_list_large);
        }

        public void bind(final ItemImage itemImage) {

            Picasso.get()
                    .load(itemImage.getImageUrl()+"?height=500&width=500")
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(itemImageLarge);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }

}

