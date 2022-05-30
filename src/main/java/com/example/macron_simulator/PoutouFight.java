package com.example.macron_simulator;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.Random;

public class PoutouFight extends FightScene {

    boolean hasYellowVest;
    boolean seenDialogue;

    public PoutouFight(ImageView personView) {
        super(personView, Main.screenX, Main.screenY);

        seenDialogue = false;

        hasYellowVest = (Main.heldItem == "yellowvest");

        this.slap.addEventHandler(MouseEvent.MOUSE_PRESSED, poutouTransitionToSlap) ;
        this.opponentStartDamage = 5.0;
        this.opponentEndDamage = 25.0;
    }

    @Override
    public void handleSlap(Double percentage, Double speed) {
            decreaseOpponentHP(percentage, speed);
            modifyMacronHealthText();
            //Here i will check for the opponent HP conditon after i get slapped the shit out of

        }

    EventHandler poutouTransitionToSlap = new EventHandler() {
        @Override
        public void handle(Event event) {
            Text x = (Text) event.getSource();
            PoutouFight y = (PoutouFight) x.getScene();

            if (!seenDialogue) {
                if (hasYellowVest) {
                    DialogueChain dialogueChain = new DialogueChain("assets/PoutouVestOn.json");
                    dialogueChain.sceneList.getLast().addEventHandler(MouseEvent.MOUSE_PRESSED, TEST);
                    Main.stage.setScene(dialogueChain.sceneList.getFirst());

                } else {
                    DialogueChain dialogueChain = new DialogueChain("assets/PoutouNoVest.json");
                    Main.stage.setScene(dialogueChain.sceneList.getFirst());
                }

                seenDialogue = true;
            }
            else {
                SlapScene test = Main.curScene.slapSceneFactory.createSlapScene("brigette", y);
                Main.curScene.currentSlapScene = test;
                Main.stage.setScene(test);
            }

        }
    };

   EventHandler TEST = new EventHandler() {
       @Override
       public void handle(Event event) {
           SlapScene test = slapSceneFactory.createSlapScene("brigette", Main.curScene);
           Main.stage.setScene(test);
       }
   };

};
