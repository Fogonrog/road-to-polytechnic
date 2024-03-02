package com.road.polytechnic.logic;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.road.polytechnic.R;
import com.road.polytechnic.view.GameActivity;

public final class SceneController {
    private final ImageView bg;
    private final ImageView[] persons;
    private final TextView phrase;
    private final TextView name;
    private final EventManager eventManager;
    private final GameActivity activity;
    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;
    private boolean texting = false;

    public SceneController(GameActivity activity) {
        this.activity = activity;
        this.mediaPlayer = new MediaPlayer();
        this.bg = activity.findViewById(R.id.imageView);
        this.phrase = activity.findViewById(R.id.phrase);
        this.name = activity.findViewById(R.id.name);
        this.eventManager = new EventManager(this);
        this.persons = new ImageView[3];
        this.persons[0] = activity.findViewById(R.id.person1);
        this.persons[1] = activity.findViewById(R.id.person2);
        this.persons[2] = activity.findViewById(R.id.person3);

        init();

//        AudioAttributes attributes = new AudioAttributes.Builder()
//                .setUsage(AudioAttributes.USAGE_GAME)
//                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                .build();
//        soundPool = new SoundPool.Builder()
//                .setAudioAttributes(attributes)
//                .build();
    }

    private void init() {
        changeBg(R.drawable.black_bg);
        changePhrase("None", "...");
    }
    public void nextScene() {
        if (texting) {
            stopTexting();
        } else {
            eventManager.nextEvent();
        }
    }
    private void stopTexting() { texting = false; }

    public void playMusic(int id) {
        if (mediaPlayer.isPlaying()) {
            stopPlayingMusic();
        }
        mediaPlayer = MediaPlayer.create(activity, id);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void stopPlayingMusic() {
        int fadeDuration = 2000; // Длительность затухания в миллисекундах (например, 5 секунд)
        int numberOfSteps = fadeDuration / 100; // Количество шагов изменения громкости
        long delay = fadeDuration / numberOfSteps; // Задержка между шагами

        float volumeStep = 1.0f / numberOfSteps; // Величина изменения громкости за шаг

        Handler handler = new Handler();

        for (int i = 0; i < numberOfSteps; i++) {
            final int currentStep = i;
            handler.postDelayed(() -> {
                float volume = 1.0f - (volumeStep * currentStep);
                if (mediaPlayer != null) {
                    mediaPlayer.setVolume(volume, volume);

                    if (currentStep == numberOfSteps - 1) {
                        mediaPlayer.stop();
                    }
                }
            }, i * delay);
        }
    }

    public void movePersonToFront(int pos) {
        Animation fadeOut = AnimationUtils.loadAnimation(activity, R.anim.fade_out);
        persons[pos].startAnimation(fadeOut);

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) persons[pos].getLayoutParams();
        params.width = 600;
        params.height = 1000;

        Animation fadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in);

        persons[pos].setLayoutParams(params);
        persons[pos].startAnimation(fadeIn);
    }

    public void movePersonToBack(int pos) {
        Animation fadeOut = AnimationUtils.loadAnimation(activity, R.anim.fade_out);
        persons[pos].startAnimation(fadeOut);

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) persons[pos].getLayoutParams();
        params.width = 300;
        params.height = 700;

        Animation fadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in);

        persons[pos].setLayoutParams(params);
        persons[pos].startAnimation(fadeIn);
    }

    public void hidePerson(int pos) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(500); // Длительность анимации в миллисекундах
        alphaAnimation.setFillAfter(true);

        persons[pos].startAnimation(alphaAnimation);

        // Убедитесь, что после анимации изображение скрыто
        persons[pos].setVisibility(View.INVISIBLE);
    }

    public void showPerson(int pos) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(500); // Длительность анимации в миллисекундах
        alphaAnimation.setFillAfter(true);

        persons[pos].startAnimation(alphaAnimation);
        persons[pos].setVisibility(View.VISIBLE);
    }

    public void changePerson(int id, int pos) {
        Drawable[] layers = new Drawable[2]; // Создаем массив спрайтов с изначальным и новым изображениями
        layers[0] = persons[pos].getDrawable(); // Изначальное изображение
        layers[1] = ContextCompat.getDrawable(activity, id); // Новое изображение

        TransitionDrawable transitionDrawable = new TransitionDrawable(layers); // Эффект плавного перехода между двумя изображениями
        persons[pos].setImageDrawable(transitionDrawable);

        transitionDrawable.startTransition(500); // Запускаем плавный переход между двумя изображениями
        persons[pos].setVisibility(View.VISIBLE);
    }

    public void changePhrase(String strName, String strPhrase) {
        changeName(strName);
        phrase.setText("");
        texting = true;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            int index = 0;
            @Override
            public void run() {
                if (!texting) {
                    phrase.setText(strPhrase);
                    return;
                }

                phrase.append(String.valueOf(strPhrase.charAt(index)));
                index++;
                if (index < strPhrase.length()) {
                    handler.postDelayed(this, 10); // Задержка между символами
                } else {
                    stopTexting();
                }
            }
        }, 10); // Задержка перед началом анимации

    }

    public void changeName(String strName) { //, int colorId) {
        if (strName.equals("none") || strName.equals("None")) {
            name.setVisibility(View.INVISIBLE);
            activity.findViewById(R.id.bg2).setVisibility(View.INVISIBLE);
        } else {
            name.setVisibility(View.VISIBLE);
            activity.findViewById(R.id.bg2).setVisibility(View.VISIBLE);
            name.setText(strName);
//            name.setTextColor(activity.getColor(colorId));
        }
    }

    /**
     * Метод для изменения фона с плавным переходом между двумя изображениями.
     *
     * @param id Идентификатор нового изображения в ресурсах.
     */
    public void changeBg(int id) {
        Drawable[] layers = new Drawable[2]; // Создаем массив слоев с изначальным и новым изображениями
        layers[0] = bg.getDrawable(); // Изначальное изображение
        layers[1] = ContextCompat.getDrawable(activity, id); // Новое изображение

        TransitionDrawable transitionDrawable = new TransitionDrawable(layers); // Эффект плавного перехода между двумя изображениями
        bg.setImageDrawable(transitionDrawable); // Устанавливаем переход в качестве фона

        transitionDrawable.startTransition(500); // Запускаем плавный переход между двумя изображениями
    }

    public void onPause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void onResume() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }
}
