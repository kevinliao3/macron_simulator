package com.example.macron_simulator;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MacronCanvas extends StackPane{

    public MacronCanvas(double X, double Y, String dialogue) {
        super();
        this.setPrefSize(X,Y);

        Image x = new Image("file:assets/macron_phone.jpg",X,Y, true,true);
        BackgroundImage bImg = new BackgroundImage(x, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        Background bGround = new Background(bImg);

        this.setBackground(bGround);

        Text asdf = new Text(dialogue);
        asdf.setFont(new Font(50));
        this.getChildren().add(asdf);

    }



}
