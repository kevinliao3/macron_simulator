package com.example.macron_simulator;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.util.Random;

public class FightScene extends Scene {

    //This should be an abstract class to be the base for each opponent
    ImageView macronView;

    ImageView opponent;

    Double opponentHP;
    Text opponentHPText;
    Double macronHealthPercentage;
    Text macronHealthPercentageText;

    Double opponentStartDamage;
    Double opponentEndDamage;
    
    public Text slap;

    SlapSceneFactory slapSceneFactory;

    SlapScene currentSlapScene;
    SpeedText speedSlap;

    public FightScene(ImageView opponentView, double X, double Y) {
        super(new Group(), X, Y);

        slapSceneFactory = new SlapSceneFactory();

        this.opponent = opponentView;

        opponentHP = 200.0;
        opponentHPText = new Text(opponentHP.toString());
        opponentHPText.setX(100);
        opponentHPText.setY(300);

        macronHealthPercentage = Main.macron.getHP();
        macronHealthPercentageText = new Text(macronHealthPercentage.toString());
        macronHealthPercentageText.setX(100);
        macronHealthPercentageText.setY(700);

        opponentStartDamage = 20.0;
        opponentEndDamage = 30.0;
        ((Group) this.getRoot()).getChildren().add(opponentHPText);

        slap = new Text("Slap");
        slap.setFont(new Font(100));

        speedSlap = null;

        slap.setTextAlignment(TextAlignment.RIGHT);
        Image macron = new Image("file:assets/macron_wink_snipped.png");
        macronView = new ImageView(macron);

        File cssFile = new File("css/fight.css");
        this.getStylesheets().add("file:///" + cssFile.getAbsolutePath().replace("\\", "/"));

        macronView.getStyleClass().add("macron");
        opponent.getStyleClass().add("opponent");

        slap.setX(800);
        slap.setY(500);

        ((Group) this.getRoot()).getChildren().add(macronView);
        ((Group) this.getRoot()).getChildren().add(opponent);
        ((Group) this.getRoot()).getChildren().add(slap);
        ((Group) this.getRoot()).getChildren().add(macronHealthPercentageText);

    }

    public void handleSlap(Double percentage, Double speed) {
        decreaseOpponentHP(percentage,speed);

        modifyMacronHealthText();

    }

    public Text getNewHPText() {
        opponentHPText = new Text(opponentHP.toString());
        opponentHPText.setX(100);
        opponentHPText.setY(300);
        return opponentHPText;
    }


    public void decreaseOpponentHP(Double percentage, Double speed) {

        opponentHP = opponentHP - percentage;
        ((Group) this.getRoot()).getChildren().remove(opponentHPText);
        ((Group) this.getRoot()).getChildren().add(getNewHPText());

        if (!(speedSlap == null)) {
            ((Group) this.getRoot()).getChildren().remove(speedSlap);

        }

        speedSlap = new SpeedText(speed.toString());
        speedSlap.setX(300);
        speedSlap.setY(300);
        ((Group) this.getRoot()).getChildren().add(speedSlap);

    }

    public void decreaseMacronHealth() {

        Random random = new Random();

        Double randomRoll = random.nextDouble();
        Double result = opponentStartDamage + (randomRoll * (opponentEndDamage-opponentStartDamage));

        System.out.println("Opponent did " + result +  "damage" + macronHealthPercentage);
        macronHealthPercentage = macronHealthPercentage - result;

        System.out.println(macronHealthPercentage);
    };

    public void modifyMacronHealthText() {
        //Define a general combat loop here
        //Each person can have its own

        decreaseMacronHealth();

        ((Group) this.getRoot()).getChildren().remove(macronHealthPercentageText);

        macronHealthPercentageText = new Text(macronHealthPercentage.toString());

        macronHealthPercentageText.setX(100);
        macronHealthPercentageText.setY(700);

        ((Group) this.getRoot()).getChildren().add(macronHealthPercentageText);

    }

    public void increaseHealth(Double amount) {
        macronHealthPercentage = macronHealthPercentage + amount;
    }

}
