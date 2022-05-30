package com.example.macron_simulator;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MacronBackground extends DialogueScene {

    public MacronBackground(double X, double Y, String passedDialogue) {
        super(passedDialogue);

        Image x = new Image("file:assets/macron_phone.jpg",X,Y, true,true);
        BackgroundImage bImg = new BackgroundImage(x, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        Background bGround = new Background(bImg);

        StackPane canvas = new StackPane();
        canvas.setPrefSize(X,Y);

        canvas.setBackground(bGround);

        canvas.getChildren().add(dialogue);
        ((Group) this.getRoot()).getChildren().add(canvas);

    }

}
