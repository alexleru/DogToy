package ru.alexleru.dogtoy.View;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import ru.alexleru.dogtoy.R;

public class StartActivity extends AppCompatActivity {
    public static final int DELAY_MILLIS = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        new Handler().postDelayed(()->{
            Intent intent = new Intent(getBaseContext(), HomeActivity.class);
            startActivity(intent);}, DELAY_MILLIS);
    }
}
