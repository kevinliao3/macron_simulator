package com.example.macron_simulator;
;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import javafx.util.Duration;

import java.io.File;

public class SlapScene extends Scene {

    Image hand;
    ImageView handView;

    AnimationTimer at;

    Double pastX;
    Double pastY;

    Double speed;
    Double maxSpeed;

    long pastTime;

    boolean slapped;

    public BoundingBox y;
    TranslateTransition asdf;
    Rectangle circle;

    Pane canvas;
    //Need an animation timer with slap
    public SlapScene(double X, double Y) {

        super(new Group(), X, Y);

        hand = new Image("file:assets/hand.png");
        handView = new ImageView(hand);
        //there may be some wierd stuff going on with longs and doubles
        pastTime = 0;
        pastX = 0.0;

        maxSpeed = 5e-19;

//        canvas = new Pane();
        circle = new Rectangle(90,90,Color.RED);
        //Need to have a function that checks for collisions
//        canvas.getChildren().addAll(circle);
//        circle.setX(0);
//        circle.setY(0);
        circle.setX(X/2);
        circle.setY(Y/2);

        y = new BoundingBox(X/2,Y/2,90,90);


        this.addEventHandler(MouseEvent.MOUSE_MOVED, new handler());

        at = new AnimationTimer() {
            @Override
            public void handle(long l) {
//                System.out.println(circle.getX());
//                System.out.println(circle.getY());

                //Get current X location and subtract from previous absolute value
                Double curX = handView.getX();
                Double diffX = Math.abs(curX-pastX);

                speed = diffX/((l-pastTime)*10e-9);

                pastTime = l;

                if (speed > maxSpeed) {
                    maxSpeed = speed;
                }

//                System.out.println("Current record speed " + maxSpeed);

                //Check for intersection

                boolean intersects = handView.intersects(y);

//                System.out.println(intersects);

//                System.out.println("CIRCLE TRANSLATE X IS" + circle.getTranslateX());
//                System.out.println(circle.getTranslat)
            }
        };

        ((Group) this.getRoot()).getChildren().add(handView);

//        ((Group) this.getRoot()).getChildren().add(canvas);
        ((Group) this.getRoot()).getChildren().add(circle);

//        Bounds x = new Bounds(X,Y,0,1,1,1);


        at.start();

        asdf = new TranslateTransition(Duration.millis(1000),circle);
        asdf.setFromX(0);
        asdf.setFromY(0);
        asdf.setByX(100);
        asdf.setByY(100);
        asdf.setRate(1);
//        asdf.setToX((X/2));
//        asdf.setToY((Y/2));

        asdf.setDelay(Duration.millis(5000));
        asdf.setCycleCount(5);
        asdf.setAutoReverse(true);
        asdf.play();
//        asdf.pause();
//        asdf.stop();

    }

    public class handler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            //Log to a logger
            pastX = handView.getX();
//            pastY = handView.getY();

//            System.out.println(event.getX() - pastX);
//            System.out.println(event.getY() - pastY);

            handView.setX(event.getX());
            handView.setY(event.getY());

            Double rectX = circle.getX();
            Double rectY = circle.getY();

            System.out.println(rectX);
            System.out.println(rectY);

//            boolean intersects = handView.intersects(y);
            boolean intersects = handView.intersects(rectX, rectY, 90,90);
            if (intersects && !slapped) {
                String temp = "You slapped at:" + speed;
                System.out.println(temp);
                slapped = true;
            }
        }
    }



}
