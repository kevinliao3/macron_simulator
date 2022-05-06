package com.example.macron_simulator;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;

public class FightScene extends Scene {

    //This should be an abstract class to be the base for each opponent
    ImageView macronView;

    ImageView opponent;

    Rectangle macronHPBar;
    Rectangle macronPPBar;

    Rectangle opponentHPBar;
    Rectangle opponentPPBar;

    public FightScene(ImageView opponent) {
        super(new Group(), 540, 209);

        macronHPBar = new Rectangle(200.0, 50.0);
        macronPPBar = new Rectangle(200.0, 50.0);

        Image macron = new Image("file:assets/macron_wink.png");
        macronView = new ImageView(macron);

        File cssFile = new File("css/fight.css");
        this.getStylesheets().add("file:///" + cssFile.getAbsolutePath().replace("\\", "/"));
        macronHPBar.getStyleClass().add("hpbar");
        macronPPBar.getStyleClass().add("ppbar");
        macronView.getStyleClass().add("macron");

        ((Group) this.getRoot()).getChildren().add(macronHPBar);
        ((Group) this.getRoot()).getChildren().add(macronPPBar);
        ((Group) this.getRoot()).getChildren().add(macronView);

    }



}
