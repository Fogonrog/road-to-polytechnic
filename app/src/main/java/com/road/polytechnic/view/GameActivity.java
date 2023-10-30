package com.road.polytechnic.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.road.polytechnic.R;
import com.road.polytechnic.model.Event;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private ImageView bg;
    private ImageView person1;
    private TextView phrase;
    private TextView name;
    private Event currentEvent;
    private int numCurrentEvent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_game);

        bg = findViewById(R.id.imageView);
        phrase = findViewById(R.id.phrase);
        name = findViewById(R.id.name);
        person1 = findViewById(R.id.person1);

        bg.setOnClickListener(v -> {
            change();
        });
        phrase.setOnClickListener(v -> {
            change();
        });
        name.setOnClickListener(v -> {
            change();
        });
        person1.setOnClickListener(v -> {
            change();
        });

            currentEvent = new Event(R.string.name_Lexa, R.string.Lphrase_1,
                    R.drawable.none, R.drawable.black_bg);

            bg.setImageResource(currentEvent.getBackground());
            person1.setImageResource(currentEvent.getPerson());
            name.setText(currentEvent.getName());
            phrase.setText(currentEvent.getPhrase());

            numCurrentEvent = 1;
        }
        public void change() {
            switch (numCurrentEvent) {
                case 6 :
                    currentEvent.setName(R.string.name_mama);
                    name.setText(currentEvent.getName());

                    currentEvent.setPhrase(R.string.Mphrase_2);
                    phrase.setText(currentEvent.getPhrase());

                    currentEvent.setPerson(R.drawable.mama_shock);
                    person1.setImageResource(currentEvent.getPerson());
                    numCurrentEvent++;
                    break;
                case 5 :
                    currentEvent.setPhrase(R.string.Lphrase_3);
                    phrase.setText(currentEvent.getPhrase());

                    currentEvent.setName(R.string.name_Lexa);
                    name.setText(currentEvent.getName());
                    numCurrentEvent++;
                    break;
            case 4 :
                currentEvent.setPhrase(R.string.Mphrase_1);
                phrase.setText(currentEvent.getPhrase());

                currentEvent.setPerson(R.drawable.mama_happy);
                person1.setImageResource(currentEvent.getPerson());

                currentEvent.setName(R.string.name_mama);
                name.setText(currentEvent.getName());

                currentEvent.setBackground(R.drawable.kitchen);
                bg.setImageResource(currentEvent.getBackground());

                numCurrentEvent++;
                break;
            case 3 :
                currentEvent.setPhrase(R.string.Lphrase_1);
                phrase.setText(currentEvent.getPhrase());

                currentEvent.setBackground(R.drawable.black_bg);
                bg.setImageResource(currentEvent.getBackground());

                currentEvent.setPerson(R.drawable.none);
                person1.setImageResource(currentEvent.getPerson());

                numCurrentEvent++;
                break;
            case 2 :
                currentEvent.setBackground(R.drawable.bg1_2);
                bg.setImageResource(currentEvent.getBackground());
                currentEvent.setPhrase(R.string.Lphrase_2);
                phrase.setText(currentEvent.getPhrase());
                numCurrentEvent++;
                break;
            case 1:
                currentEvent.setBackground(R.drawable.bg1_1);
                bg.setImageResource(currentEvent.getBackground());
                numCurrentEvent++;
                break;
        }
    }
}