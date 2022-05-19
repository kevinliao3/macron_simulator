package com.example.macron_simulator;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.util.Random;

public class FightScene extends Scene {

    //This should be an abstract class to be the base for each opponent
    ImageView macronView;

    ImageView opponent;

    Rectangle macronHPBar;
    Rectangle macronPPBar;

    Rectangle opponentHPBar;
    Rectangle opponentPPBar;
    
    Double healthPercentage;
    Text healthPercentageText;
    Double macronHealthPercentage;
    Text macronHealthPercentageText;
    
    DoubleProperty healthPercentage1;

    DoubleBinding b1;

    public Text slap;

    SlapSceneFactory slapSceneFactory;

    SlapScene currentSlapScene;
    SpeedText speedSlap;

    public FightScene(ImageView opponentView, double X, double Y) {
        super(new Group(), X, Y);

        slapSceneFactory = new SlapSceneFactory();

        macronHPBar = new Rectangle(150.0, 30.0);
        macronPPBar = new Rectangle(150.0, 30.0);

        opponentHPBar = new Rectangle(150.0, 30.0);
        opponentHPBar.getStyleClass().add("opponenthpbar");

        healthPercentage1 = new SimpleDoubleProperty(1.0);
        b1 = opponentHPBar.heightProperty().multiply(healthPercentage1);
        
        healthPercentage = 1.0;
        healthPercentageText = new Text(healthPercentage.toString());
        healthPercentageText.setX(100);
        healthPercentageText.setY(300);

        macronHealthPercentage = 1.0;
        macronHealthPercentageText = new Text(macronHealthPercentage.toString());
        macronHealthPercentageText.setX(100);
        macronHealthPercentageText.setY(700);

        ((Group) this.getRoot()).getChildren().add(healthPercentageText);

        slap = new Text("Slap");
        slap.setFont(new Font(100));

        speedSlap = null;

        slap.setTextAlignment(TextAlignment.RIGHT);
        Image macron = new Image("file:assets/macron_wink_snipped.png");
        macronView = new ImageView(macron);

        File cssFile = new File("css/fight.css");
        this.getStylesheets().add("file:///" + cssFile.getAbsolutePath().replace("\\", "/"));

        macronHPBar.getStyleClass().add("hpbar");
        macronPPBar.getStyleClass().add("ppbar");

        macronView.getStyleClass().add("macron");
        opponentView.getStyleClass().add("opponent");

        slap.setX(800);
        slap.setY(500);

        ((Group) this.getRoot()).getChildren().add(macronHPBar);
        ((Group) this.getRoot()).getChildren().add(macronPPBar);
        ((Group) this.getRoot()).getChildren().add(macronView);
        ((Group) this.getRoot()).getChildren().add(opponentView);
        ((Group) this.getRoot()).getChildren().add(opponentHPBar);
        ((Group) this.getRoot()).getChildren().add(slap);
        ((Group) this.getRoot()).getChildren().add(macronHealthPercentageText);

    }

    public void handleSlap(Double percentage, Double speed) {
        decreaseOpponentHP(percentage,speed);

        modifyMacronHealthText();
        //Here i will check for the opponent HP conditon after i get slapped the shit out of


//        Main.stage.setScene(this);

    }

    public Text getNewHPText() {
        healthPercentageText = new Text(healthPercentage.toString());
        healthPercentageText.setX(100);
        healthPercentageText.setY(300);
        return healthPercentageText;
    }


    public void decreaseOpponentHP(Double percentage, Double speed) {

        healthPercentage = healthPercentage - percentage;
        ((Group) this.getRoot()).getChildren().remove(healthPercentageText);
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
        Double start = 0.10;
        Double end = 0.15;

        Double randomRoll = random.nextDouble();
        Double result = start + (randomRoll * (end-start));

        macronHealthPercentage = macronHealthPercentage - result;

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

}
