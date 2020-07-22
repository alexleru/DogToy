package ru.alexleru.dogtoy.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import ru.alexleru.dogtoy.DataAdapterPrecautionary;
import ru.alexleru.dogtoy.Model.ModelPrecautionary;
import ru.alexleru.dogtoy.Service.NetworkService;
import ru.alexleru.dogtoy.R;

public class PrecautionaryActivity extends AppCompatActivity {
    private ImageView imageButtonBack, imageItem;
    private RecyclerView recyclerView;
    private int item;
    private String itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precautionary);
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
                .getAPI().getPrecautionary(item)
                .enqueue(new Callback<List<ModelPrecautionary>>() {
            @Override
            public void onResponse(Call<List<ModelPrecautionary>> call, Response<List<ModelPrecautionary>> response) {
                List<ModelPrecautionary> modelPrecautionaries = response.body();
                setAdapter(modelPrecautionaries);
            }
            @Override
            public void onFailure(Call<List<ModelPrecautionary>> call, Throwable t) {
                Intent intent = new Intent(PrecautionaryActivity.this, NoNetworkActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void setAdapter(List<ModelPrecautionary> modelPrecautionaries){
        DataAdapterPrecautionary dataAdapterPrecautionary = new DataAdapterPrecautionary(modelPrecautionaries);
        recyclerView.setAdapter(dataAdapterPrecautionary);
    }
}
