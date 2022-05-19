package com.example.macron_simulator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main extends Application {

    static Double screenX;
    static Double screenY;

    public static Stage stage;
    Scene currentScene;

    ShopScene shopscene;

    IntroScene introscene;

    FightScene brigetteFight;
    SlapScene slapScene;
    MenuScene menuScene;

    MacronBackground macronBackground;
    LepenBackground lepenBackground;

    static FightSceneFactory fightFactory;
    static SceneFactory sceneFactory;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        screenX = screenBounds.getMaxX();
        screenY = screenBounds.getMaxY();

        fightFactory = new FightSceneFactory();
        sceneFactory = new SceneFactory();

        //Need to have a seperate thing for the correct transition between stages

        Scene introscene =  sceneFactory.createScene("intro");


        brigetteFight = fightFactory.createFight("brigette");

        currentScene = introscene;
        stage.setScene(currentScene);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}