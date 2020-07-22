package ru.alexleru.dogtoy.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.alexleru.dogtoy.DataAdapterSubItem;
import ru.alexleru.dogtoy.Model.ModelSubItem;
import ru.alexleru.dogtoy.R;
import ru.alexleru.dogtoy.Service.NetworkService;

public class SubItemActivity extends AppCompatActivity {
    public static final String SUB_ITEM = "SUBITEM";
    public static final String SUB_ITEM_NAME = "SUBITEMNAME";
    public static final String SUB_ITEM_DESCRIPTION = "SUBITEMDESCRIPTION";
    private ImageView imageButtonBack, imageItem;
    private RecyclerView recyclerView;
    private int item;
    private String itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_item);
        item = getIntent().getIntExtra(HomeActivity.ITEM, 0);
        itemName = getIntent().getStringExtra(HomeActivity.ITEM_NAME);
        view();
        toFinishActivity();
    }

    private void view() {
        imageButtonBack = findViewById(R.id.imageBack);
        imageItem = findViewById(R.id.imageItem);
        Picasso.get()
                .load("http://alexleru.h1n.ru/dogtoy/subitem/drawable-xxxhdpi/"+item+".png")
                .into(imageItem);
        TextView itemText = findViewById(R.id.textItem);
        itemText.setText(itemName);
        recyclerView = findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getResponse();
    }

    private void toFinishActivity() {
        imageButtonBack.setOnClickListener((View v) -> finish());
    }

    private void getResponse() {
        NetworkService.getNetworkService()
                .getAPI().getSubItem(item)
                .enqueue(new Callback<List<ModelSubItem>>() {
                    @Override
                    public void onResponse(Call<List<ModelSubItem>> call, Response<List<ModelSubItem>> response) {
                        List<ModelSubItem> modelSubItems = response.body();
                        setAdapter(modelSubItems);
                    }

                    @Override
                    public void onFailure(Call<List<ModelSubItem>> call, Throwable t) {
                        Intent intent = new Intent(SubItemActivity.this, NoNetworkActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    private void setAdapter(List<ModelSubItem> modelSubItems) {
        DataAdapterSubItem dataAdapterSubItem = new DataAdapterSubItem(modelSubItems, this);
        recyclerView.setAdapter(dataAdapterSubItem);
    }

    public void goCatalogueActivity(ModelSubItem modelSubItem) {
        Intent intent = new Intent(SubItemActivity.this, CatalogueActivity.class);
        intent.putExtra(SubItemActivity.SUB_ITEM, modelSubItem.id);
        intent.putExtra(SubItemActivity.SUB_ITEM_NAME, modelSubItem.name);
        intent.putExtra(SubItemActivity.SUB_ITEM_DESCRIPTION, modelSubItem.description);
        startActivity(intent);
    }
}
