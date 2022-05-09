package com.example.macron_simulator;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    public static Stage stage;
    Scene currentScene;

    ShopScene shopscene;
    DialogueScene dialoguescene;

    IntroScene introscene;
    Image brigette = new Image("file:assets/brigette.jpg");
    ImageView brigetteView = new ImageView(brigette);
    BrigetteFight brigetteFight;
    SlapScene slapScene;
    MenuScene menuScene;

    MacronBackground macronBackground;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        Double screenX = screenBounds.getMaxX();
        Double screenY = screenBounds.getMaxY();

        //Need to have a seperate thing for the correct transition between stages

        introscene = new IntroScene(screenX, screenY);



        dialoguescene = new DialogueScene("assets/dialogue1.json",screenX, screenY);
        brigetteFight = new BrigetteFight(brigetteView, screenX, screenY);
        macronBackground = new MacronBackground(screenX, screenY);
        shopscene = new ShopScene(screenX, screenY);
        slapScene = new SlapScene(screenX, screenY);
        menuScene = new MenuScene(screenX, screenY);

        introscene.addEventHandler(MouseEvent.MOUSE_PRESSED,handler);
        dialoguescene.addEventHandler(MouseEvent.MOUSE_PRESSED, dialogueTransition);
        menuScene.addEventHandler(MouseEvent.MOUSE_PRESSED, menuTransition);
        shopscene.addEventHandler(MouseEvent.MOUSE_PRESSED, shopTransition);
        brigetteFight.addEventHandler(MouseEvent.MOUSE_PRESSED, fightTransition);

        currentScene = introscene;
        stage.setScene(currentScene);
        stage.show();

    }

    //Writing this function with temporary arg with flag as transition
    //called via TransitionEvent


    //This EventHandler will handle events that transition between Scenes

    EventHandler handler = new EventHandler() {
        @Override
        public void handle(Event event) {
            //Log to a logger
            transitionScene(2);
            introscene.stopPlaying();
        }
    };

    EventHandler dialogueTransition = new EventHandler() {
        @Override
        public void handle(Event event) {
            //Log to a logger
            transitionScene(6);
            introscene.stopPlaying();
        }
    };

    EventHandler menuTransition = new EventHandler() {
        @Override
        public void handle(Event event) {
            transitionScene(3);

        }
    };

    EventHandler shopTransition = new EventHandler() {
        @Override
        public void handle(Event event) {
            transitionScene(4);

        }
    };

    EventHandler fightTransition = new EventHandler() {
        @Override
        public void handle(Event event) {
            transitionScene(5);
        }
    };

    public void transitionScene(int sceneID) {
        switch(sceneID) {
            case 1:
                stage.setScene(macronBackground);
                break;
            case 2:
                stage.setScene(dialoguescene);
                break;
            case 3:
                stage.setScene(shopscene);
                break;
            case 4:
                stage.setScene(brigetteFight);
                break;
            case 5:
                stage.setScene(slapScene);
                break;
            case 6:
                stage.setScene(menuScene);
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}