package com.example.macron_simulator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

public class DialogueChain {

            /*
        So you take in the json file and read it, yeah there are preprogrammed functions for each dialogue
        since there is not that much dialogue scenes and you call a dialogue controller with the json file?
         */

    //Kind of like the head of a linkedlist
    DialogueScene firstScene;
    DialogueScene prevScene;
    DialogueScene curScene;
    LinkedList<Scene> sceneList = new LinkedList<Scene>();

    public DialogueChain(String jsonfile) {
        firstScene = null;
        prevScene = null;
        curScene = null;

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


                switch(id) {
                    case "macron":
                        MacronBackground macronBackground = new MacronBackground(Main.screenX, Main.screenY, dialogue);
                        sceneList.add(macronBackground);
                        break;
                    case "lepen":
                        LepenBackground lepenBackground = new LepenBackground(Main.screenX, Main.screenY, dialogue);
                        sceneList.add(lepenBackground);
                        break;

                }

            }

            //Iterate through the list and add EventHandler

            ListIterator<Scene> x = sceneList.listIterator(0);

            curScene = (DialogueScene) x.next();

            while (x.hasNext()) {
            prevScene = curScene;

            curScene = (DialogueScene) x.next();
            prevScene.nextScene = curScene;
            prevScene.addEventHandler(MouseEvent.MOUSE_PRESSED, transitionToCurScene);

            }

            curScene.addEventHandler(MouseEvent.MOUSE_PRESSED, transitionToMenu);
            //For the last one add the eent hanlder


        } catch (
                IOException e) {
            throw new AssertionError("IOException found.", e);


        }
    };

    EventHandler transitionToMenu = new EventHandler() {
        @Override
        public void handle(Event event) {
            Main.stage.setScene(Main.sceneFactory.createScene("menu"));
        }
    };

    EventHandler transitionToCurScene = new EventHandler() {
        @Override
        public void handle(Event event) {
            DialogueScene x = (DialogueScene) event.getSource();
            Main.stage.setScene(x.nextScene);
        }
    };



}
