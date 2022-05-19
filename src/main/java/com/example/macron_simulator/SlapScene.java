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
import javafx.scene.shape.Rectangle;

import javafx.util.Duration;

import java.io.File;

public class SlapScene extends Scene {

    Image hand;
    ImageView handView;

    AnimationTimer at;
    long pastTime;

    Double pastX;
    Double pastY;

    Double speed;
    Double maxSpeed;


    boolean slapped;

    public BoundingBox personBoundingBox;

    TranslateTransition personTransition;
    ImageView personSlapped;

    FightScene parentScene;
    
    //Need an animation timer with slap
    public SlapScene(double X, double Y, ImageView person, FightScene parent) {

        super(new Group(), X, Y);

        hand = new Image("file:assets/hand.png");
        handView = new ImageView(hand);
        
        //there may be some wierd stuff going on with longs and doubles
        pastTime = 0;
        pastX = 0.0;

        maxSpeed = 5e-19;

        personSlapped = person;        //Need to have a function that checks for collisions

        personSlapped.setX(50);
        personSlapped.setY(Y/2);

        //this should not be necessayr since it should be updated in the handler 
//        personBoundingBox = new BoundingBox(X/2,Y/2,90,90);
        parentScene = parent;

        this.addEventHandler(MouseEvent.MOUSE_MOVED, new handler());

       at = new AnimationTimer() {
            @Override
            public void handle(long l) {

                speed = getSpeed(l);

//                System.out.println("Current speed " + speed);

            }
        };

        personTransition = new TranslateTransition(Duration.millis(10000),personSlapped);
        personTransition.setFromX(0);
        personTransition.setFromY(-400);
        personTransition.setByX(300);
        personTransition.setByY(600);
        personTransition.setRate(50);

        personTransition.setCycleCount(100000000);
        personTransition.setAutoReverse(true);

        ((Group) this.getRoot()).getChildren().add(handView);
        ((Group) this.getRoot()).getChildren().add(personSlapped);

        at.start();
        //Need to also start translation

        personTransition.play();

    }

    //this is probably the problem
    public double slap() {
        at.start();

        personTransition.play();

        while (!slapped) {
            ;
        }

        return speed;
    };

    public double getSpeed(long l) {
        Double curX = handView.getX();
        Double diffX = Math.abs(curX-pastX);

        //The 10e-9 just is to reduce the value, is not anything that significant
        speed = diffX/((l-pastTime)*10e-9);

        pastTime = l;

        return speed;
    };

    public double getPercentageDecreaseFromSpeed(Double speed) {
        if (speed > 0 && speed < 500) {
            return 0.05;
        } else if ( speed >= 500 && speed < 1000) {
            return 0.1;
        } else if (speed >= 1000) {
            return 0.15;
        } else {
            return 0;
        }
    }

    public class handler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            //Log to a logger
            pastX = handView.getX();

            handView.setX(event.getX());
            handView.setY(event.getY());

            Double rectX = personSlapped.getTranslateX();
            Double rectY = personSlapped.getTranslateY();

            System.out.println(personSlapped.getFitHeight());
            System.out.println(personSlapped.getFitWidth());

            Double startX = personSlapped.getX()+rectX;
            Double startY = personSlapped.getY()+rectY;

            System.out.println(startX);
            System.out.println(startY);

            personBoundingBox = new BoundingBox(startX,startY,100, 100);

            boolean intersects = handView.intersects(personBoundingBox);
            if (intersects && !slapped) {
                String temp = "You slapped at:" + speed + " km/hr";
//                System.out.println(temp);
                slapped = true;
                at.stop();

                parentScene.handleSlap(getPercentageDecreaseFromSpeed(speed),speed);

                Main.stage.setScene(parentScene);

            }
        }
    }


}
