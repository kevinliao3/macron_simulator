package com.example.macron_simulator;

//https://www.geeksforgeeks.org/factory-method-design-pattern-in-java/

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FightFactory {

    public FightScene createFight(String fighter, double screenX, double screenY) {
        switch (fighter) {
            case "brigette":
                Image brigette = new Image("file:assets/brigette.jpg");
                ImageView brigetteView = new ImageView(brigette);
                FightScene brigetteFight = new FightScene(brigetteView, screenX, screenY);
                return brigetteFight;

            case "zemmour":
                Image zemmour = new Image("file:assets/zemmour.jpg");
                ImageView zemmourView = new ImageView(zemmour);

                return new FightScene(zemmourView, screenX, screenY);


        }
        ;
        return null;
    }
}