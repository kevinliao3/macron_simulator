package com.example.macron_simulator;

//https://www.geeksforgeeks.org/factory-method-design-pattern-in-java/

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class FightSceneFactory {

    public FightScene createFight(String fighter) {
        switch (fighter) {
            case "brigette":
                Image brigette = new Image("file:assets/brigette.jpg");
                ImageView brigetteView = new ImageView(brigette);
                FightScene brigetteFight = new FightScene(brigetteView, Main.screenX, Main.screenY);
                brigetteFight.slap.addEventHandler(MouseEvent.MOUSE_PRESSED,transitionToSlap);

                return brigetteFight;

            case "zemmour":
                Image zemmour = new Image("file:assets/zemmour.jpg");
                ImageView zemmourView = new ImageView(zemmour);

                return new FightScene(zemmourView, Main.screenX, Main.screenY);

        }
        ;
        return null;
    }
    EventHandler transitionToFight = new EventHandler() {
        @Override
        public void handle(Event event) {
            Main.stage.setScene(Main.sceneFactory.createScene("fight"));
        }
    };

        EventHandler transitionToSlap = new EventHandler() {
        @Override
        public void handle(Event event) {
//            SlapScene test = (SlapScene) Main.sceneFactory.createScene("slap");
            Text x = (Text) event.getSource();
            FightScene y = (FightScene) x.getScene();
            SlapScene test = y.slapSceneFactory.createSlapScene("brigette", y);
            Main.stage.setScene(test);

//            double speed = test.slap();
//
//            if (speed > 1000) {
//                speed = 0.5;
//            }
//
//            FightScene x = (FightScene) event.getSource();
//            x.handleSlap(speed);
            //add event handler to slap scene
        }
    };


}