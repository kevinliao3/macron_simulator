package com.example.macron_simulator;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.File;

public class BrigetteFight extends FightScene {

    boolean hasEarplugs;
    boolean seenDialogue;

    public BrigetteFight(ImageView personView) {
        super(personView, Main.screenX, Main.screenY);

        seenDialogue = false;

        hasEarplugs = (Main.heldItem == "earplugs");
//        this.opponentStartDamage = 5.0;
//        this.opponentEndDamage = 25.0;
        this.slap.addEventHandler(MouseEvent.MOUSE_PRESSED, brigetteTransitionToSlap) ;
    }

    @Override
    public void handleSlap(Double percentage, Double speed) {
        decreaseOpponentHP(percentage, speed);
        modifyMacronHealthText();

        if (!hasEarplugs) {

        Main.textToSpeech.speak("Brigette did severe emotional damage");

        }
        //Here i will check for the opponent HP conditon after i get slapped the shit out of

    }

    EventHandler brigetteTransitionToSlap = new EventHandler() {
        @Override
        public void handle(Event event) {

            if (!seenDialogue) {
                if (hasEarplugs) {
                    opponentStartDamage = 10.0;
                    opponentEndDamage = 25.0;
                } else {
                    opponentStartDamage = 100.0;
                    opponentEndDamage = 100.0;                }
            }

            Text x = (Text) event.getSource();
            BrigetteFight y = (BrigetteFight) x.getScene();
            SlapScene test = y.slapSceneFactory.createSlapScene("brigette", y);
            y.currentSlapScene = test;
            Main.stage.setScene(test);


        }
    };

    EventHandler TEST = new EventHandler() {
        @Override
        public void handle(Event event) {
            SlapScene test = slapSceneFactory.createSlapScene("brigette", Main.curScene);
            Main.stage.setScene(test);
        }
    };

}
