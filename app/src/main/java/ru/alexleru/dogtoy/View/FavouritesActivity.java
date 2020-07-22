package ru.alexleru.dogtoy.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.alexleru.dogtoy.DataAdapterCatalogue;
import ru.alexleru.dogtoy.DogToyApp;
import ru.alexleru.dogtoy.Model.ModelCatalogue;
import ru.alexleru.dogtoy.R;
import ru.alexleru.dogtoy.Service.NetworkService;

public class FavouritesActivity extends AppCompatActivity implements InterfaceActivity {
    public static final String TEXT_WARNING = "TEXT_WARNING";
    private ImageView imageButtonBack;
    private RecyclerView recyclerView;
    private String numbersOfFavourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        view();
        toFinishActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        numbersOfFavourites = DogToyApp.getSetOfNumbersOfFavourites(getApplicationContext());
        getResponse();
    }

    private void view() {
        imageButtonBack = findViewById(R.id.imageBack);
        recyclerView = findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void toFinishActivity() {
        imageButtonBack.setOnClickListener(v -> finish());
    }

    private void getResponse() {
        NetworkService.getNetworkService()
                .getAPI().getFavourites(numbersOfFavourites)
                .enqueue(new Callback<List<ModelCatalogue>>() {

                    @Override
                    public void onResponse(Call<List<ModelCatalogue>> call, Response<List<ModelCatalogue>> response) {
                        List<ModelCatalogue> modelCatalogues = response.body();
                        setAdapter(modelCatalogues);
                    }

                    @Override
                    public void onFailure(Call<List<ModelCatalogue>> call, Throwable t) {
                        Intent intent = new Intent(FavouritesActivity.this, NoNetworkActivity.class);
                        intent.putExtra(TEXT_WARNING, "Нет избранного");
                        startActivity(intent);
                        finish();
                    }
                });
    }

    private void setAdapter(List<ModelCatalogue> modelCatalogues) {
        DataAdapterCatalogue dataAdapterCatalogue = new DataAdapterCatalogue(modelCatalogues, this);
        recyclerView.setAdapter(dataAdapterCatalogue);
    }

    @Override
    public void goIntent(ModelCatalogue item) {
        Intent intent = new Intent(getApplicationContext(), ToyActivity.class);
        intent.putExtra(CatalogueActivity.MODEL, item);
        startActivity(intent);
    }
}
