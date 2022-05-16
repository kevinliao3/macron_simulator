package com.example.macron_simulator;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;

public class FightScene extends Scene {

    //This should be an abstract class to be the base for each opponent
    ImageView macronView;

    ImageView opponent;

    Rectangle macronHPBar;
    Rectangle macronPPBar;

    Rectangle opponentHPBar;
    Rectangle opponentPPBar;

    DoubleProperty healthPercentage1;
    DoubleBinding b1;

    public Text slap;

    public FightScene(ImageView opponentView, double X, double Y) {
        super(new Group(), X, Y);

        macronHPBar = new Rectangle(150.0, 30.0);
        macronPPBar = new Rectangle(150.0, 30.0);

        opponentHPBar = new Rectangle(150.0, 30.0);
        healthPercentage1 = new SimpleDoubleProperty(1.0);
        b1 = opponentHPBar.widthProperty().multiply(healthPercentage1);

        slap = new Text("Slap");
        slap.setFont(new Font(100));

        slap.setTextAlignment(TextAlignment.RIGHT);
        Image macron = new Image("file:assets/macron_wink_snipped.png");
        macronView = new ImageView(macron);

        File cssFile = new File("css/fight.css");
        this.getStylesheets().add("file:///" + cssFile.getAbsolutePath().replace("\\", "/"));
        macronHPBar.getStyleClass().add("hpbar");
        macronPPBar.getStyleClass().add("ppbar");
        macronView.getStyleClass().add("macron");
        opponentView.getStyleClass().add("opponent");

//        slap.getStyleClass().add("menu");

        slap.setX(500);
        slap.setY(500);

        ((Group) this.getRoot()).getChildren().add(macronHPBar);
        ((Group) this.getRoot()).getChildren().add(macronPPBar);
        ((Group) this.getRoot()).getChildren().add(macronView);
        ((Group) this.getRoot()).getChildren().add(opponentView);
        ((Group) this.getRoot()).getChildren().add(slap);

    }

    public void decreaseOpponentHP(Double percentage) {
        b1 = b1.subtract(percentage);
    }

}
