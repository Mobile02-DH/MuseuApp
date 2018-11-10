package br.edu.digitalhouse.museuapp.Interfaces;

import br.edu.digitalhouse.museuapp.model.GalleryResponse;
import retrofit2.Call;
import retrofit2.http.GET;

import static br.edu.digitalhouse.museuapp.model.dao.network.RetrofitService.API_KEY;

public interface API {

    @GET("gallery?floor=1&apikey="+API_KEY)
    Call<GalleryResponse> getFloor1();

    @GET("gallery?floor=2&apikey="+API_KEY)
    Call<GalleryResponse> getFloor2();

    @GET("gallery?floor=3&apikey="+API_KEY)
    Call<GalleryResponse> getFloor3();
}
