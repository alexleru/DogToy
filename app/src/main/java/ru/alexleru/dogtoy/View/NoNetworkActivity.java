package ru.alexleru.dogtoy.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.alexleru.dogtoy.R;

public class NoNetworkActivity extends AppCompatActivity {
    private ImageView imageButtonBack;
    private TextView textWarning;
    private String textStringWarning = "Нет сети";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_network);
        textStringWarning = getIntent().getStringExtra(FavouritesActivity.TEXT_WARNING);
        view();
        toFinishActivity();
    }

    private void view() {
        imageButtonBack = findViewById(R.id.imageBack);
        textWarning = findViewById(R.id.textWarning);
        textWarning.setText(textStringWarning);
    }

    private void toFinishActivity() {
        imageButtonBack.setOnClickListener((View v) -> finish());
    }
}
