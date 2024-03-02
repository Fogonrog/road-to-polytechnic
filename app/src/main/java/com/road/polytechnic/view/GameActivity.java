package com.road.polytechnic.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.road.polytechnic.R;
import com.road.polytechnic.logic.SceneController;


public class GameActivity extends AppCompatActivity {
    private SceneController sceneController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, // скрываем строку состояния
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility( // скрываем панель навигации
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_game);

        sceneController = new SceneController(this); // инициализируем контроллер сцены
        View mainLayout = findViewById(R.id.сonstraintLayout);
        mainLayout.setOnClickListener(v -> change()); // назначаем всему экрану 1 действие - следующая сцена
    }

    @Override
    protected void onPause() {
        super.onPause();
        sceneController.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sceneController.onResume();
    }

    public void change() {
        sceneController.nextScene();
    }
}