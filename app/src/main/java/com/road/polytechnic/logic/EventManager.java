package com.road.polytechnic.logic;

import com.road.polytechnic.R;

public class EventManager {
    private int currentNumEvent = 0;
    private final SceneController sc;

    public EventManager(SceneController sc) {
        this.sc = sc;
    }

    public void nextEvent() {
        switch (currentNumEvent) {
            case 0 -> {
                sc.changeBg(R.drawable.bg1_1);
                sc.playMusic(R.raw.with_you_in_my_arms);
                currentNumEvent++;
            }
            case 1 -> {
                sc.changePhrase("Лёха", "Я столько сил потратил ради этого результата! " +
                        "Как я рад! Ура! Мама, м-а-а-а-м!");
                sc.changeBg(R.drawable.bg1_2);
                currentNumEvent++;
            }
            case 2 -> {
                sc.changePhrase("None", "…");
                sc.changeBg(R.drawable.black_bg);
                currentNumEvent++;
            }
            case 3 -> {
                sc.changePhrase("Мать", "Леша, что такое?");
                sc.changePerson(R.drawable.mama_happy, 1);
                sc.showPerson(1);
                sc.changeBg(R.drawable.kitchen);
                sc.stopPlayingMusic();
                currentNumEvent++;
            }
            case 4 -> {
                sc.changePhrase("Лёха", "Результаты за экзамен пришли!");
                currentNumEvent++;
            }
            case 5 -> {
                sc.changePhrase("Мать", "И как?");
                sc.changePerson(R.drawable.mama_shock, 1);
                sc.movePersonToFront(1);
                currentNumEvent++;
            }
            case 6 -> {
               sc.movePersonToBack(1);
               sc.hidePerson(1);
                currentNumEvent++;
            }
        }
    }
}
