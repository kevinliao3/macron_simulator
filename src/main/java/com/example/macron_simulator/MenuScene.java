package com.example.macron_simulator;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.io.File;

public class MenuScene extends Scene {

    public Text fight;
    public Text shop;

    public Text nextOpponent;

    Media media;
    public MediaPlayer mediaPlayer;

    public MenuScene(double X, double Y) {
        super(new Group(), X, Y);

        fight = new Text("Fight");
        fight.setFont(new Font(40));
        shop = new Text("Shop");
        shop.setFont(new Font(40));

        fight.setX(X/2);
        fight.setY(Y/2);

        shop.setX(X/2);
        shop.setY((Y/2) + 70);

//        media = new Media((new File("assets/la_syrie.mp4").toURI().toString()));
//       mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.setAutoPlay(true);

        nextOpponent = new Text("Next opponent: " + Main.fightGen());

        nextOpponent.setX(X/2);
        nextOpponent.setY(40);
        nextOpponent.setFont(new Font(30));

        ((Group) this.getRoot()).getChildren().add(nextOpponent);
        ((Group) this.getRoot()).getChildren().add(fight);
        ((Group) this.getRoot()).getChildren().add(shop);

    }

}
