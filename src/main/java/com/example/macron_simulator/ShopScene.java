package com.example.macron_simulator;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.text.*;

import java.io.File;
import java.net.URL;

public class ShopScene extends Scene {

    public ShopScene() {
        super(new Group(), 540, 209);

        File cssFile = new File("css/shop.css");

        this.getStylesheets().add("file:///" + cssFile.getAbsolutePath().replace("\\", "/"));

        Text fight = new Text("Earplugs");
        fight.setFont(new Font(20));
        Text shop = new Text("RAD-140");
        shop.setFont(new Font(20));

        TextFlow textFlow = new TextFlow(fight, shop);
        ((Group) this.getRoot()).getChildren().add(textFlow);


    }

}
