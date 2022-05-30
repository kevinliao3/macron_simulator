package com.example.macron_simulator;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class ZemmourBackground extends DialogueScene {

    public ZemmourBackground(double X, double Y, String passedDialogue) {
        super(passedDialogue);

        Image x = new Image("file:assets/zemmour.PNG",X,Y, true,true);
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
