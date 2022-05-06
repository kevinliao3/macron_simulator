package com.example.macron_simulator;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;

public class Main extends Application {

    public static Stage stage;
    Object currentScene;
    IntroScene introscene = new IntroScene();
    ShopScene shopscene = new ShopScene();
    DialogueScene dialoguescene = new DialogueScene("assets/dialogue1.json");

    Image brigette = new Image("file:assets/brigette.jpg");
    ImageView brigetteView = new ImageView(brigette);
    BrigetteFight brigetteFight = new BrigetteFight(brigetteView);

    @Override
    public void start(Stage primaryStage) throws IOException {

        stage = primaryStage;

        stage.addEventHandler(MouseEvent.MOUSE_PRESSED,handler);
//        stage.setScene(introscene);
        currentScene = introscene;
        stage.setScene(brigetteFight);
        stage.show();
    }

    //Writing this function with temporary arg with flag as transition
    //called via TransitionEvent


    //This EventHandler will handle events that transition between Scenes

    EventHandler handler = new EventHandler() {
        @Override
        public void handle(Event event) {
            //Log to a logger
            transitionScene(1);
        }
    };

    public void transitionScene(int sceneID) {
        switch(sceneID) {
            case 1:
                stage.setScene(dialoguescene);
                break;
            case 2:
                stage.setScene(dialoguescene);
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}