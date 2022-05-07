package com.example.macron_simulator;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class MacronBackground extends Scene {

    public MacronBackground(double X, double Y) {
        super(new Group(), X, Y);

        Image x = new Image("file:assets/macron_phone.png");
        BackgroundImage bImg = new BackgroundImage(x, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        Background bGround = new Background(bImg);

        Pane canvas = new Pane();
        canvas.setPrefSize(X,Y);
//        String cssValue1 = "-fx-background-color: black;";
        String cssValue = "-fx-background-image: url(assets/macron_phone.jpg)";
        canvas.setStyle(cssValue);

        ((Group) this.getRoot()).getChildren().add(canvas);
    }

}
