package ru.alexleru.dogtoy.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.alexleru.dogtoy.Model.ModelCatalogue;
import ru.alexleru.dogtoy.Model.ModelPrecautionary;
import ru.alexleru.dogtoy.Model.ModelSubItem;

public interface APIService {
    @GET("precautionary.php")
    Call<List<ModelPrecautionary>> getPrecautionary(@Query("item") int item);

    @GET("subitem.php")
    Call<List<ModelSubItem>> getSubItem(@Query("item") int item);

    @GET("catalogue.php")
    Call<List<ModelCatalogue>> getCatalogue(@Query("item") int item);

    @GET("catalogue.php")
    Call<List<ModelCatalogue>> getFavourites(@Query("item_arr") String favourites);
}
