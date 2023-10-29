package com.road.polytechnic.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.road.polytechnic.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_game);

//        width = 884;
//        height = 1073;      классика
//        topMargin = 148;
//
//        width = 1084;
//        height = 1273;      близко
//        topMargin = 148;

//        String[] persons = new String["add bg 1-1", ""];
        ImageView person1 = findViewById(R.id.person1);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) person1.getLayoutParams();
        params.width = 884;
        params.height = 1073;
        params.topMargin = 148;
        person1.setLayoutParams(params);

        ImageView bg = findViewById(R.id.imageView);
        bg.setOnClickListener(v -> {
            bg.setImageResource(R.drawable.bg1_2);
        });

    }
}