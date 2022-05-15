package com.example.macron_simulator;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.control.ListView;
import javafx.scene.text.*;

import java.io.File;
import java.net.URL;

public class ShopScene extends Scene {

    public Text back;
    ListView<ItemPane> list;

    public ShopScene(double X, double Y) {
        super(new Group(), X, Y);

        File cssFile = new File("css/shop.css");

        this.getStylesheets().add("file:///" + cssFile.getAbsolutePath().replace("\\", "/"));

        list = new ListView<ItemPane>();

        list.getItems().add(new ItemPane(new Item("Earplugs")));
        list.getItems().add(new ItemPane(new Item("RAD-140")));
//Each node can have an event handler zzzzzzz

        back = new Text("Back");
        back.setFont(new Font(30));

        back.setX(300);
        back.setY(300);

        ((Group) this.getRoot()).getChildren().add(back);
        ((Group) this.getRoot()).getChildren().add(list);

    }

}
