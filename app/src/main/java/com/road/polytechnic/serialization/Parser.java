package com.road.polytechnic.serialization;

import com.road.polytechnic.logic.SceneController;
import com.road.polytechnic.view.GameActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public final class Parser {
    private final GameActivity activity;
    private final SceneController sceneController;

    private File scriptFile;
    private long lastPos = 0;

    public Parser(GameActivity activity, SceneController sceneController) { // Сейчас не работает
        this.activity = activity;
        this.sceneController = sceneController;
        try {
            var inputStream = activity.getAssets().open("script.txt");
            byte[] buffer = new byte[inputStream.available()];

            var bytesRead = inputStream.read(buffer);

            scriptFile = new File(activity.getFilesDir(), "script_copy.txt");
            FileOutputStream fos = new FileOutputStream(scriptFile);

            fos.write(buffer, 0, bytesRead);
            fos.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseNextScene() {
        try {
            var raf = new RandomAccessFile(scriptFile, "r");
            raf.seek(lastPos); // Переходим к последней позиции после "update scene"

            String[] currentCommand;
            String line;
            while ((line = raf.readLine()) != null) {
                if (!line.contains("update scene")) {
                    currentCommand = line.split(" ");
                    if (line.contains("change phrase")) {
                        sceneController.changePhrase(currentCommand[2], // Имя персонажа
                                line.substring(line.indexOf("\""), line.lastIndexOf("\""))); // Фраза, заключённая в двойных кавычках
                    } else if (line.contains("change bg")) {
                        int id = getResourceId(currentCommand[2], "drawable");
                        sceneController.changeBg(id);
                    } else if (line.contains("change person")) {
                        int pos = switch (currentCommand[5]) {
                            case "left" -> 0;
                            case "center" -> 1;
                            case "right" -> 2;
                            default -> -1;
                        };
                        int id = getResourceId(currentCommand[2] + "_" + currentCommand[3], "drawable");
                        sceneController.changePerson(id, pos);
                    } else if (line.contains("show person")) {
                        int pos = switch (currentCommand[5]) {
                            case "left" -> 0;
                            case "center" -> 1;
                            case "right" -> 2;
                            default -> -1;
                        };
                        sceneController.showPerson(pos);
                    } else if (line.contains("hide person")) {
                        int pos = switch (currentCommand[5]) {
                            case "left" -> 0;
                            case "center" -> 1;
                            case "right" -> 2;
                            default -> -1;
                        };
                        sceneController.hidePerson(pos);
                    } else if (line.contains("move person")) {
                        int pos = switch (currentCommand[5]) {
                            case "left" -> 0;
                            case "center" -> 1;
                            case "right" -> 2;
                            default -> -1;
                        };
                        if (currentCommand[4].equals("front")) {
                            sceneController.movePersonToFront(pos);
                        } else if (currentCommand[4].equals("back")) {
                            sceneController.movePersonToBack(pos);
                        }
                    } else if (line.contains("play")) {
                        if (currentCommand[1].equals("music")) {
                            int id = getResourceId(currentCommand[2], "raw");
                            sceneController.playMusic(id);
                        } else if (currentCommand[1].equals("sound")) {
                            // TODO: короткие звуки
                        }
                    } else if (line.contains("stop music")) {
                        sceneController.stopPlayingMusic();
                    }
                } else {
                    lastPos = raf.getFilePointer();
                    break;
                }
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private int getResourceId(String resourceName, String type) {
        return activity.getResources().getIdentifier(resourceName, type, activity.getPackageName());
    }
}
