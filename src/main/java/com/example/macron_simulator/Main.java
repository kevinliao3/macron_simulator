package com.example.macron_simulator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main extends Application {

    Double screenX;
    Double screenY;

    public static Stage stage;
    Scene currentScene;

    ShopScene shopscene;

    IntroScene introscene;

    FightScene brigetteFight;
    SlapScene slapScene;
    MenuScene menuScene;

    MacronBackground macronBackground;
    LepenBackground lepenBackground;

    FightFactory fightFactory;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        screenX = screenBounds.getMaxX();
        screenY = screenBounds.getMaxY();

        //Need to have a seperate thing for the correct transition between stages

        introscene = new IntroScene(screenX, screenY);

        shopscene = new ShopScene(screenX, screenY);
        slapScene = new SlapScene(screenX, screenY);
        menuScene = new MenuScene(screenX, screenY);

        fightFactory = new FightFactory();

        brigetteFight = fightFactory.createFight("brigette", screenX, screenY);

        introscene.addEventHandler(MouseEvent.MOUSE_PRESSED,handler);

        menuScene.fight.addEventHandler(MouseEvent.MOUSE_PRESSED, transitiontoFight);
        menuScene.shop.addEventHandler(MouseEvent.MOUSE_PRESSED, menuTransition);

        shopscene.back.addEventHandler(MouseEvent.MOUSE_PRESSED, dialogueTransition);

        brigetteFight.addEventHandler(MouseEvent.MOUSE_PRESSED, fightTransition);


        currentScene = menuScene;
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

    EventHandler transitiontoFight = new EventHandler() {
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
        switch (sceneID) {
            case 1:
                stage.setScene(macronBackground);
                break;
            case 2:
                dialogueController("assets/dialogue1.json");
//                stage.setScene(dialoguescene);
                break;
            case 3:
                stage.setScene(shopscene);
                break;
            case 4:
                stage.setScene(brigetteFight);
                break;
            case 5:
                stage.setScene(slapScene);
                double speed = slapScene.slap();

                if (speed > 1000) {
                    speed = 0.5;
                }
                handleSlap(speed);
                //add event handler to slap scene
                break;
            case 6:
                stage.setScene(menuScene);
                break;
            case 7:
                dialogueController("assets/dialogue1.json");
                break;
        }
    }

        void dialogueController(String jsonfile) {
        /*
        So you take in the json file and read it, yeah there are preprogrammed functions for each dialogue
        since there is not that much dialogue scenes and you call a dialogue controller with the json file?
         */

            Timeline timeline = new Timeline();

            ObjectMapper mapper = new ObjectMapper();

//        File personToImageFile = new File("assets/persontoimage.json");

//        try {
//            var xx = mapper.readTree(personToImageFile);
//            Map<String, String> personToImage = mapper.convertValue(xx, new TypeReference<Map<String, String>>() {
//            });
//        } catch (IOException e) {
//            throw new AssertionError("IOException found.", e);
//        }

            File dialogueFile = new File(jsonfile);

            try {
                var dialogueJsonNode = mapper.readTree(dialogueFile);

                Map<String, Object> result = mapper.convertValue(dialogueJsonNode, new TypeReference<Map<String, Object>>() {
                });
                ArrayList<Map<String, String>> y = (ArrayList<Map<String, String>>) result.get("dialogue");

                for (Map sentence : y) {
                    String id = new String((String)sentence.get("id"));
                    String dialogue = new String((String)sentence.get("dialogue"));

                System.out.println(sentence.get("id"));
                System.out.println(sentence.get("dialogue"));

                DialogueScene dialoguescene = new DialogueScene("assets/dialogue1.json",screenX, screenY);

                stage.setScene(dialoguescene);

                    switch(id) {
                        case "macron":
                            MacronBackground macronBackground = new MacronBackground(this.screenX, this.screenY, dialogue);
                            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10000),event -> stage.setScene(macronBackground)));
//                            stage.setScene(new MacronBackground(this.screenX, this.screenY, dialogue));
//                            stage.show();
//                            try {
//                                Thread.sleep(10000);
//                            } catch (Exception e) {
//                            System.out.println("adsf");


                            break;
                        case "lepen":
                            LepenBackground lepenBackground = new LepenBackground(this.screenX, this.screenY, dialogue);
                            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10000),event -> stage.setScene(lepenBackground)));
                            break;
                    }

                    //Change the scene for the person and render the dialogue box

                    //This will be if the design is like in the design document
//                DialogueBubble zz = new DialogueBubble((String) sentence.get("id"), (String) sentence.get("dialogue"));
                    //Create a dialogue bubble for each sentence
                    //Render it?
                    //set the css background by reading the appropriate mapping?
                }

                timeline.play();
            } catch (IOException e) {
                throw new AssertionError("IOException found.", e);


            }
        };

    public void handleSlap(Double percentage) {
        brigetteFight.decreaseOpponentHP(percentage);
        stage.setScene(brigetteFight);

    }


    public static void main(String[] args) {
        launch();
    }
}