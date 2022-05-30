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

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;


public class Main extends Application {

    static Double screenX;
    static Double screenY;

    public static Stage stage;
    Scene currentScene;

    ShopScene shopscene;

    IntroScene introscene;

    FightScene brigetteFight;
    static FightScene curScene;
    SlapScene slapScene;
    MenuScene menuScene;

    MacronBackground macronBackground;
    LepenBackground lepenBackground;

    static FightSceneFactory fightFactory;
    static SceneFactory sceneFactory;

    static ItemBag itemBag;
    static String heldItem;

    public static Integer progressFlag;

    public static TextToSpeech textToSpeech;

    public static Macron macron;

    @Override
    public void start(Stage primaryStage) throws IOException {

        macron = new Macron();

        textToSpeech = new TextToSpeech();

        stage = primaryStage;

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        screenX = screenBounds.getMaxX();
        screenY = screenBounds.getMaxY();

        sceneFactory = new SceneFactory();
        fightFactory = new FightSceneFactory();

        progressFlag = 0;

        //Need to have a seperate thing for the correct transition between stages

        Scene introscene =  sceneFactory.createScene("intro");

        itemBag = new ItemBag();

        currentScene = introscene;
        stage.setScene(currentScene);
        stage.show();

    }

    public static String fightGen() {

    switch(progressFlag) {
        case 0:
            return "poutou";
            //Poutou
        case 1:
            return "zemmour";
            //Zemmour
        case 2:
            return "brigette";
            //briggete
        case 3:
            return "kid";
        default:
            System.out.println(progressFlag);
            //will kid

    }
    return null;
    };

    public static void winFight() {
        progressFlag += 1;
    }

    public static void main(String[] args) {
        launch();
    }
}