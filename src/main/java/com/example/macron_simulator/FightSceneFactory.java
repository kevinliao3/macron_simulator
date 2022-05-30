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
                BrigetteFight brigetteFight = new BrigetteFight(brigetteView);

                return brigetteFight;

            case "zemmour":
                Image zemmour = new Image("file:assets/zemmour.PNG");
                ImageView zemmourView = new ImageView(zemmour);
                ZemmourFight zemmourFight = new ZemmourFight(zemmourView);

                return zemmourFight;

            case "poutou":
                Image poutou = new Image("file:assets/poutou.jpg");
                ImageView poutouView = new ImageView(poutou);
               PoutouFight poutouFight = new PoutouFight(poutouView);

                return poutouFight;
            case "kid":
                Image kid = new Image("file:assets/will.png");
                ImageView kidView = new ImageView(kid);
                KidFight kidFight = new KidFight(kidView);
                return kidFight;
        }
        ;
        return null;
    }

        EventHandler transitionToSlap = new EventHandler() {
        @Override
        public void handle(Event event) {
            Text x = (Text) event.getSource();
            FightScene y = (FightScene) x.getScene();
            SlapScene test = y.slapSceneFactory.createSlapScene("brigette", y);
            Main.stage.setScene(test);

        }
    };


}