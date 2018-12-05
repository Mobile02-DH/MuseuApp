package br.edu.digitalhouse.museuapp.model.dao;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.edu.digitalhouse.museuapp.Interfaces.ServiceListener;
import br.edu.digitalhouse.museuapp.model.dao.network.RetrofitService;
import br.edu.digitalhouse.museuapp.model.galleryrequest.Item;
import br.edu.digitalhouse.museuapp.model.galleryrequest.ItemResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryDao {

    public void getItems(Context context, final ServiceListener listener, String gallery) {

        List<Item> itemList = new ArrayList<>();

        if (isConnected(context)) {

            getNetworkData(listener, gallery, 100);

        } else {

            Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show();
            /*getLocalData(context, listener, itemList);*/
        }
    }

    private void getNetworkData(final ServiceListener listener, String gallery, int recordsPerQuery) {

        Call<ItemResponse> call = RetrofitService.getApiService().getGallery(gallery, recordsPerQuery, RetrofitService.API_KEY);

        call.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                if (response.body() != null) {
                    listener.onSucess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                listener.onSucess(t);

            }
        });

    }

    private void getLocalData(Context context, ServiceListener listener, List<Item> itemList) {
        try {
            AssetManager manager = context.getAssets();
            InputStream itemsJson = manager.open("items.json");
            BufferedReader bufferReaderIn = new BufferedReader(new InputStreamReader(itemsJson));

            Gson gson = new Gson();

            Item[] itemArray = gson.fromJson(bufferReaderIn, Item[].class);

            itemList.addAll(Arrays.asList(itemArray));

            listener.onSucess(itemList);

        } catch (IOException exception) {
            Log.e("JSON", "ERRO AO LER ARQUIVO items.json");
            listener.onError(exception);
        }
    }

    private boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo;

        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected() &&
                    (networkInfo.getType() == ConnectivityManager.TYPE_WIFI
                            || networkInfo.getType() == ConnectivityManager.TYPE_MOBILE);
        }
        return false;
    }
}
