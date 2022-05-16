package com.example.macron_simulator;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SceneFactory {

        public Scene createScene(String id) {
            switch (id) {
                case "intro":
                    IntroScene introScene = new IntroScene(Main.screenX, Main.screenY);
                    introScene.addEventHandler(MouseEvent.MOUSE_PRESSED,transitionToMenu);
                    return introScene;
                case "shop":
                    ShopScene shopScene = new ShopScene(Main.screenX, Main.screenY);
                    shopScene.back.addEventHandler(MouseEvent.MOUSE_PRESSED, transitionToMenu);
                    return shopScene;
                case "menu":
                    MenuScene menuScene = new MenuScene(Main.screenX, Main.screenY);
                    menuScene.fight.addEventHandler(MouseEvent.MOUSE_PRESSED, transitionToFight);
                    menuScene.shop.addEventHandler(MouseEvent.MOUSE_PRESSED, transitionToShop);
                    return menuScene;
                case "slap":
                    SlapScene slapScene = new SlapScene(Main.screenX, Main.screenY);
                    return slapScene;

            }
            ;
            return null;
        }

//    EventHandler transitionToDialogue = new EventHandler() {
//        @Override
//        public void handle(Event event) {
//            //Log to a logger
//            Main.dialogueController("assets/dialogue1.json");
//
//            Main.currentScene.stopPlaying();
//        }
//    };

    EventHandler transitionToShop = new EventHandler() {
        @Override
        public void handle(Event event) {
            Main.stage.setScene(createScene("shop"));

        }
    };

    EventHandler transitionToFight = new EventHandler() {
        @Override
        public void handle(Event event) {
            Main.stage.setScene(Main.fightFactory.createFight("brigette"));
        }
    };

    EventHandler transitionToMenu = new EventHandler() {
        @Override
        public void handle(Event event) {;
            Main.stage.setScene(createScene("menu"));
        }
    };



}
