package br.edu.digitalhouse.museuapp.Interfaces;

import br.edu.digitalhouse.museuapp.model.GalleryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("gallery")
    Call<GalleryResponse> getFloor(
            @Query("floor") int floor,
            @Query("page") int page,
            @Query("apikey") String apikey
    );
}
