package br.edu.digitalhouse.museuapp.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import br.edu.digitalhouse.museuapp.Interfaces.ListClickListener;
import br.edu.digitalhouse.museuapp.R;
import br.edu.digitalhouse.museuapp.model.galleryrequest.ItemImage;
import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;
import ozaydin.serkan.com.image_zoom_view.ImageViewZoomConfig;

public class ItemImageRecyclerViewAdapter extends RecyclerView.Adapter<ItemImageRecyclerViewAdapter.ViewHolder> {

    private List<ItemImage> imageList;
    private ListClickListener listener;
    private ImageViewZoomConfig imageViewZoomConfig;
    private Activity activity;

    public ItemImageRecyclerViewAdapter(List<ItemImage> imageList, ListClickListener listener, Activity activity) {
        this.imageList = imageList;
        this.listener = listener;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_image_item, viewGroup, false);

        return new ViewHolder(view, listener, activity);
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

        private ImageViewZoom itemImageLarge;
        private ListClickListener listener;

        public ViewHolder(@NonNull View itemView, ListClickListener listener, Activity activity) {

            super(itemView);

            this.listener = listener;
            itemView.setOnClickListener(this);

            itemImageLarge = itemView.findViewById(R.id.img_item_list_large);

            imageViewZoomConfig = new ImageViewZoomConfig();
            imageViewZoomConfig.saveProperty(true);

            ImageViewZoomConfig.ImageViewZoomConfigSaveMethod imageViewZoomConfigSaveMethod = ImageViewZoomConfig.ImageViewZoomConfigSaveMethod.always;
            imageViewZoomConfig.setImageViewZoomConfigSaveMethod(imageViewZoomConfigSaveMethod);

            itemImageLarge.setConfig(imageViewZoomConfig);

            itemImageLarge.setOnLongClickListener(v -> {
                try {

                    String insertImage = MediaStore.Images.Media.insertImage(activity.getContentResolver(), itemImageLarge.getBitmap(), "teste", "image from Museu Mapp");
                    ContentValues values = new ContentValues();

                    values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
                    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                    values.put(MediaStore.MediaColumns.DATA, insertImage);

                    activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                } catch (Exception e){
                    Log.e("Image", "error"+e.getMessage());
                }
                return false;
            });


//            itemImageLarge.saveImage(activity, "MuseuMapp", "art", Bitmap.CompressFormat.JPEG, 1, imageViewZoomConfig, new SaveFileListener() {
//                @Override
//                public void onSuccess(File file) {
//                    Toast.makeText(activity,"Image Downloaded",Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFail(Exception e) {
//                    Toast.makeText(activity,"Download Error",Toast.LENGTH_SHORT).show();
//                }
//            });

        }

        public void bind(final ItemImage itemImage) {

            Picasso.get()
                    //.load(itemImage.getImageUrl()+"?height=500&width=500")
                    .load(itemImage.getImageUrl())
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

