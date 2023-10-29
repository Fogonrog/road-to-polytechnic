package com.road.polytechnic.view;

import static android.view.View.INVISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.road.polytechnic.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_menu);

        TextView cont = findViewById(R.id.continueView);
        TextView start = findViewById(R.id.startGame);
        TextView load = findViewById(R.id.loadView);
        TextView save = findViewById(R.id.saveView);

        ImageView bg = findViewById(R.id.politechImg);
        bg.setImageResource(R.drawable.fond);

        bg.setOnClickListener(v -> {
            cont.setVisibility(View.INVISIBLE);

            start.setVisibility(View.VISIBLE);
            load.setVisibility(View.VISIBLE);
            save.setVisibility(View.VISIBLE);
        });

        start.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });
    }
}