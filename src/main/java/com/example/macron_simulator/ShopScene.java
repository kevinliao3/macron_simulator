package com.example.macron_simulator;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.text.*;

public class ShopScene extends Scene {

    public ShopScene() {
        super(new Group(), 540, 209);

//        this.getStylesheets().add("css/shop.css");
//        this.getStylesheets().add("resources/shop.css");
        this.getStylesheets().add("/shop.css");

        Text fight = new Text("Fight");
        fight.setFont(new Font(20));
        Text shop = new Text("Shop");
        shop.setFont(new Font(20));

        TextFlow textFlow = new TextFlow(fight, shop);
        ((Group) this.getRoot()).getChildren().add(textFlow);


    }

}
