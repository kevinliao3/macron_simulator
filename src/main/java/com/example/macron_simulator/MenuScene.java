package com.example.macron_simulator;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.text.*;

public class MenuScene extends Scene {

    public Text fight;
    public Text shop;


    public MenuScene(double X, double Y) {
        super(new Group(), X, Y);

        fight = new Text("Fight");
        fight.setFont(new Font(20));
        shop = new Text("Shop");
        shop.setFont(new Font(20));

        TextFlow textFlow = new TextFlow(fight, shop);
        ((Group) this.getRoot()).getChildren().add(textFlow);

    }

}
