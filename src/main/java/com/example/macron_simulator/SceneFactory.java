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
                    introScene.addEventHandler(MouseEvent.MOUSE_PRESSED,transitionToDialogue);
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
                case "dialogue":
                    DialogueChain dialogueChain = new DialogueChain("assets/dialogue1.json");
                    return dialogueChain.sceneList.getFirst();


            }
            ;
            return null;
        }


    EventHandler transitionToShop = new EventHandler() {
        @Override
        public void handle(Event event) {

            Main.stage.setScene(createScene("shop"));

        }
    };

    EventHandler transitionToFight = new EventHandler() {
        @Override
        public void handle(Event event) {

            FightScene y = Main.fightFactory.createFight(Main.fightGen());
            Main.curScene = y;
            Main.stage.setScene(y);
        }

    };

    EventHandler transitionToMenu = new EventHandler() {
        @Override
        public void handle(Event event) {;


            Main.stage.setScene(createScene("menu"));
        }
    };

    EventHandler transitionToDialogue = new EventHandler() {
        @Override
        public void handle(Event event) {;
            //Get the slapscene and call the stop funcitn, this is a temporary location for this location while dialogue does not wokr

            IntroScene introScene = (IntroScene) event.getSource();
            introScene.stopPlaying();

            Main.stage.setScene(createScene("dialogue"));
        }
    };



}
