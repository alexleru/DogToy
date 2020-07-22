package ru.alexleru.dogtoy.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ru.alexleru.dogtoy.DogToyApp;
import ru.alexleru.dogtoy.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    public final static String ITEM = "ITEM";
    public final static String ITEM_NAME = "ITEM_NAME";
    ImageView imagePrecautionaryOnStreet, imagePrecautionaryAtHome;
    TextView textPrecautionaryFavourites;
    Button buttonForPlayOnStreet, buttonForPlayAtHome, buttonForFavourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        view();
    }

    @Override
    protected void onResume() {
        super.onResume();
        textPrecautionaryFavourites.setText(setCountFavourites());
    }

    private void view() {
        imagePrecautionaryOnStreet = findViewById(R.id.attention1);
        imagePrecautionaryAtHome = findViewById(R.id.attention2);
        textPrecautionaryFavourites = findViewById(R.id.textCountFavourites);
        buttonForPlayOnStreet = findViewById(R.id.buttonForPlayOnStreet);
        buttonForPlayAtHome = findViewById(R.id.buttonForPlayAtHome);
        buttonForFavourites = findViewById(R.id.buttonForFavourites);
        imagePrecautionaryOnStreet.setOnClickListener(this);
        imagePrecautionaryAtHome.setOnClickListener(this);
        textPrecautionaryFavourites.setOnClickListener(this);
        buttonForPlayOnStreet.setOnClickListener(this);
        buttonForPlayAtHome.setOnClickListener(this);
        buttonForFavourites.setOnClickListener(this);
    }

    private String setCountFavourites() {
        int countFavouritesString = DogToyApp.getCountFavourites(getApplicationContext());
        return String.valueOf(countFavouritesString);
    }

    @Override
    public void onClick(View v) {
        if (DogToyApp.isConnectingToInternet(getBaseContext())) {
            switch (v.getId()) {
                case R.id.attention1:
                    goActivityPrecautionary(1, "Игры на улице");
                    break;
                case R.id.attention2:
                    goActivityPrecautionary(2, "Игры дома");
                    break;
                case R.id.buttonForPlayOnStreet:
                    goActivitySubItem(1, "Игры на улице");
                    break;
                case R.id.buttonForPlayAtHome:
                    goActivitySubItem(2, "Игры дома");
                    break;
                case R.id.buttonForFavourites:
                    goActivityFavourites();
                    break;
            }
        } else goActivityNoNetwork();
    }

    private void goActivityPrecautionary(int item, String str) {
        Intent intent = new Intent(getBaseContext(), PrecautionaryActivity.class);
        intent.putExtra(ITEM, item);
        intent.putExtra(ITEM_NAME, str);
        startActivity(intent);
    }

    private void goActivitySubItem(int item, String str) {
        Intent intent = new Intent(getBaseContext(), SubItemActivity.class);
        intent.putExtra(ITEM, item);
        intent.putExtra(ITEM_NAME, str);
        startActivity(intent);
    }

    private void goActivityFavourites() {
        Intent intent = new Intent(getBaseContext(), FavouritesActivity.class);
        startActivity(intent);
    }


    private void goActivityNoNetwork() {
        Intent intent = new Intent(getBaseContext(), NoNetworkActivity.class);
        intent.putExtra(FavouritesActivity.TEXT_WARNING, "Нет сети");
        startActivity(intent);
    }
}
