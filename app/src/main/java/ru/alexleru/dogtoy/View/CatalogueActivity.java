package ru.alexleru.dogtoy.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.alexleru.dogtoy.DataAdapterCatalogue;
import ru.alexleru.dogtoy.Model.ModelCatalogue;
import ru.alexleru.dogtoy.R;
import ru.alexleru.dogtoy.Service.NetworkService;

public class CatalogueActivity extends AppCompatActivity implements InterfaceActivity {
    public static final String MODEL = "MODEL";
    private ImageView imageButtonBack;
    private ImageView imageButtonBackHome;
    private TextView textItem;
    private TextView textDescription;
    private RecyclerView recyclerView;
    private int subItem;
    private String subItemName;
    private String subItemDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);
        subItem = getIntent().getIntExtra(SubItemActivity.SUB_ITEM, 0);
        subItemName = getIntent().getStringExtra(SubItemActivity.SUB_ITEM_NAME);
        subItemDescription = getIntent().getStringExtra(SubItemActivity.SUB_ITEM_DESCRIPTION);
        view();
        toFinishActivity();
        toGoHomeActivity();
    }

    private void view() {
        imageButtonBack = findViewById(R.id.imageBack);
        imageButtonBackHome = findViewById(R.id.imageBackHome);
        textItem = findViewById(R.id.textItem);
        textItem.setText(subItemName);
        textDescription = findViewById(R.id.textDescription);
        textDescription.setText(subItemDescription);
        recyclerView = findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getResponse();
    }

    private void toFinishActivity() {
        imageButtonBack.setOnClickListener(v -> finish());
    }

    private void toGoHomeActivity() {
        Intent intent = new Intent(CatalogueActivity.this, HomeActivity.class);
        imageButtonBackHome.setOnClickListener(v -> {
            startActivity(intent);
            finish();
        });
    }

    private void getResponse() {
        NetworkService.getNetworkService()
                .getAPI().getCatalogue(subItem)
                .enqueue(new Callback<List<ModelCatalogue>>() {
                    @Override
                    public void onResponse(Call<List<ModelCatalogue>> call, Response<List<ModelCatalogue>> response) {
                        List<ModelCatalogue> modelCatalogues = response.body();
                        setAdapter(modelCatalogues);
                    }

                    @Override
                    public void onFailure(Call<List<ModelCatalogue>> call, Throwable t) {
                        Intent intent = new Intent(CatalogueActivity.this, NoNetworkActivity.class);
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
