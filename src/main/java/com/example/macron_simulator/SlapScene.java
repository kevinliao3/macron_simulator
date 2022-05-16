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
    ImageView circle;

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
        Image brigette = new Image("file:assets/brigette_head.PNG");
        circle = new ImageView(brigette);        //Need to have a function that checks for collisions

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

            }
        };

        ((Group) this.getRoot()).getChildren().add(handView);
        ((Group) this.getRoot()).getChildren().add(circle);



        asdf = new TranslateTransition(Duration.millis(1000),circle);
        asdf.setFromX(0);
        asdf.setFromY(0);
        asdf.setByX(100);
        asdf.setByY(100);
        asdf.setRate(1);

        asdf.setDelay(Duration.millis(5000));
        asdf.setCycleCount(1000);
        asdf.setAutoReverse(true);

    }

    public double slap() {
        at.start();

        asdf.play();

        while (!slapped) {
            ;
        }

        return speed;
    };

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


            Double rectX = circle.getTranslateX();
            Double rectY = circle.getTranslateY();

            y = new BoundingBox(circle.getX()+rectX,circle.getY(),90,90);

            boolean intersects = handView.intersects(y);
//            boolean intersects = handView.intersects(rectX, rectY, 90,90);
            if (intersects && !slapped) {
                String temp = "You slapped at:" + speed;
                System.out.println(temp);
                slapped = true;

            }
        }
    }



}
