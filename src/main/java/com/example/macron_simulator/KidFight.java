package com.example.macron_simulator;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class KidFight extends FightScene{

    boolean hasTrenbolone;
    boolean seenDialogue;

    public KidFight(ImageView personView) {
        super(personView, Main.screenX, Main.screenY);

        seenDialogue = false;

        hasTrenbolone = (Main.heldItem == "trenbolone");

        this.slap.addEventHandler(MouseEvent.MOUSE_PRESSED, kidTransitionToSlap) ;
    }

    @Override
    public void handleSlap(Double percentage, Double speed) {
        decreaseOpponentHP(percentage, speed);
        modifyMacronHealthText();
        //Here i will check for the opponent HP conditon after i get slapped the shit out of

    }

    EventHandler kidTransitionToSlap = new EventHandler() {
        @Override
        public void handle(Event event) {

            if (!seenDialogue) {
                if (!hasTrenbolone) {
                    opponentStartDamage = 40.0;
                    opponentEndDamage = 50.0;
                    DialogueChain dialogueChain = new DialogueChain("assets/KidJsonNoRoid.json");
                    dialogueChain.sceneList.getLast().addEventHandler(MouseEvent.MOUSE_PRESSED, TEST);
                    Main.stage.setScene(dialogueChain.sceneList.getFirst());
                } else {
                    opponentStartDamage = 5.0;
                    opponentEndDamage = 10.0;
                    DialogueChain dialogueChain = new DialogueChain("assets/Roid.json");
                    dialogueChain.sceneList.getLast().addEventHandler(MouseEvent.MOUSE_PRESSED, TEST);
                    Main.stage.setScene(dialogueChain.sceneList.getFirst());
                }
                seenDialogue = true;
            } else {
                SlapScene test = Main.curScene.slapSceneFactory.createSlapScene("brigette",Main.curScene);
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

}
