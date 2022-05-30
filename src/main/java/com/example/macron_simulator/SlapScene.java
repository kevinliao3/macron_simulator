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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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

    Rectangle collisionLine;

    boolean slapped;

    public BoundingBox personBoundingBox;

    TranslateTransition personTransition;
    ImageView personSlapped;

    FightScene parentScene;

    String slapSoundFile;
    
    //Need an animation timer with slap
    public SlapScene(double X, double Y, ImageView person, FightScene parent) {

        super(new Group(), X, Y);

        slapSoundFile = "assets/slap.mp3";

        collisionLine = new Rectangle(150.0, 2.0);
        collisionLine.setFill(Color.RED);

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
        personTransition.setByX(0);
        personTransition.setByY(600);
        personTransition.setRate(35);

        personTransition.setCycleCount(100000000);
        personTransition.setAutoReverse(true);

        ((Group) this.getRoot()).getChildren().add(handView);
        ((Group) this.getRoot()).getChildren().add(personSlapped);
        ((Group) this.getRoot()).getChildren().add(collisionLine);

        Main.itemBag.setLayoutX(500);
        Main.itemBag.setLayoutY(500);
        ((Group) this.getRoot()).getChildren().add(Main.itemBag);

        at.start();
        //Need to also start translation

        personTransition.play();

        this.addEventHandler(KeyEvent.KEY_TYPED, useItem);

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
            return 10;
        } else if ( speed >= 500 && speed < 1000) {
            return 20;
        } else if (speed >= 1000) {
            return 40;
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

            Double startX = personSlapped.getX()+rectX;
            Double startY = personSlapped.getY()+rectY;

            personBoundingBox = new BoundingBox(startX,startY,100, 100);

            boolean intersects = handView.intersects(personBoundingBox);
            if (intersects) {
                String temp = "You slapped at:" + speed + " km/hr";
//                System.out.println(temp);
                at.stop();

                Media sound = new Media(new File(slapSoundFile).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();

                parentScene.handleSlap(getPercentageDecreaseFromSpeed(speed),speed);

                System.out.println(parentScene.macronHealthPercentage);
                checkForWin();
                return;
            }

            //Check for collision with the line

            boolean adsf = handView.intersects(0,0,10,Main.screenY);

            if (adsf) {
                parentScene.handleSlap(0.0,0.0);
                checkForWin();
                return;
            }
        }

        private void checkForWin() {
            if (parentScene.opponentHP <= 0) {
                Main.winFight();
                Main.textToSpeech.speak("You won.");
                System.out.println("Fight has been wor.");
                Main.stage.setScene(Main.sceneFactory.createScene("menu"));

            } else if (parentScene.macronHealthPercentage <= 0) {
                Main.textToSpeech.speak("You lost.");
                Main.stage.setScene(Main.sceneFactory.createScene("menu"));
            }
            else {

                Main.stage.setScene(parentScene);
            }
        }
    }

    EventHandler useItem = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {

                Main.itemBag.useItem(Integer.valueOf(ke.getCharacter()));
//                System.out.println(ke.getCharacter());
//                ke.consume(); // <-- stops passing the event to next node
            }
    };

    public void slowSpeed(Integer rate) {
        System.out.println("THIS IS GETTING EXECUTED");
//        personTransition.setRate(rate);
        personTransition.stop();
        personTransition.pause();
        System.out.println("WF");
//        personTransition.play();

    };


}
