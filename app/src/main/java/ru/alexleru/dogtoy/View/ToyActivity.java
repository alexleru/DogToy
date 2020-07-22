package ru.alexleru.dogtoy.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.alexleru.dogtoy.Model.ModelCatalogue;
import ru.alexleru.dogtoy.R;

public class ToyActivity extends AppCompatActivity {
    private ImageView imageButtonBack;
    private ImageView imageButtonBackHome;
    private TextView nameText, descriptionText, sizeText, branchText, countryText, weightText;
    private ImageView imageToy;
    private ModelCatalogue modelCatalogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toy);
        modelCatalogue = (ModelCatalogue) getIntent().getSerializableExtra(CatalogueActivity.MODEL);
        view();
        toFinishActivity();
        toGoHomeActivity();
    }

    private void view() {
        imageButtonBack = findViewById(R.id.imageBack);
        imageButtonBackHome = findViewById(R.id.imageBackHome);
        nameText = findViewById(R.id.name);
        descriptionText = findViewById(R.id.description);
        sizeText = findViewById(R.id.size);
        branchText = findViewById(R.id.branch);
        countryText = findViewById(R.id.producer_country);
        weightText = findViewById(R.id.weight);
        imageToy = findViewById(R.id.imageToy);

        nameText.setText(modelCatalogue.name);
        descriptionText.setText(modelCatalogue.description);
        sizeText.setText("Размер игрушки -" + modelCatalogue.size);
        branchText.setText("Бренд - " + modelCatalogue.branch);
        countryText.setText("Страна производства - " + modelCatalogue.country);
        weightText.setText("Вес - " + modelCatalogue.weight);
        Picasso.get()
                .load("http://alexleru.h1n.ru/dogtoy/catalog/drawable-xxxhdpi/" + modelCatalogue.picture + ".png")
                .into(imageToy);
    }

    private void toFinishActivity() {
        imageButtonBack.setOnClickListener(v -> finish());
    }

    private void toGoHomeActivity() {
        Intent intent = new Intent(ToyActivity.this, HomeActivity.class);
        imageButtonBackHome.setOnClickListener(v -> {
            startActivity(intent);
            finish();
        });
    }
}
