package com.example.macron_simulator;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ZemmourFight extends FightScene{

    boolean hasLockBox;
    boolean seenDialogue;

    public ZemmourFight(ImageView personView) {
        super(personView, Main.screenX, Main.screenY);

        seenDialogue = false;

        hasLockBox = (Main.heldItem == "lockbox");

        this.slap.addEventHandler(MouseEvent.MOUSE_PRESSED, zemmourTransitionToSlap) ;
    }

    @Override
    public void handleSlap(Double percentage, Double speed) {
        decreaseOpponentHP(percentage, speed);
        modifyMacronHealthText();
        //Here i will check for the opponent HP conditon after i get slapped the shit out of

    }

    EventHandler zemmourTransitionToSlap = new EventHandler() {
        @Override
        public void handle(Event event) {
            Text x = (Text) event.getSource();
            ZemmourFight y = (ZemmourFight) x.getScene();


            if (!seenDialogue) {
                System.out.println("This is exe");
                if (!hasLockBox) {
                    System.out.println("Not lockbox");
                    DialogueChain dialogueChain = new DialogueChain("assets/ZemmourNoLock.json");
                    dialogueChain.sceneList.getLast().addEventHandler(MouseEvent.MOUSE_PRESSED, TEST);
                    Main.stage.setScene(dialogueChain.sceneList.getFirst());

                    Main.itemBag.deleteItems();
                } else {
                    System.out.println("Lockbox");
                    DialogueChain dialogueChain = new DialogueChain("assets/ZemmourLock.json");
                    dialogueChain.sceneList.getLast().addEventHandler(MouseEvent.MOUSE_PRESSED, TEST);
                    Main.stage.setScene(dialogueChain.sceneList.getFirst());
                }
                    seenDialogue = true;
            } else {
                System.out.println("This should not be executing.");
                SlapScene test = y.slapSceneFactory.createSlapScene("brigette", y);
                Main.curScene.currentSlapScene = test;
                y.currentSlapScene = test;
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
