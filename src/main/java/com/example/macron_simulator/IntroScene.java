package com.example.macron_simulator;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

import static com.example.macron_simulator.Main.stage;

public class IntroScene extends Scene {

    Media media;
    MediaPlayer mediaPlayer;
    MediaView mediaView;

    public IntroScene(double X, double Y) {

        super(new Group(), X, Y);
        media = new Media((new File("assets/intro.mp4").toURI().toString()));
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView = new MediaView(mediaPlayer);

        mediaView.fitWidthProperty().bind(stage.widthProperty());
        mediaView.fitHeightProperty().bind(stage.heightProperty());

        ((Group) this.getRoot()).getChildren().add(mediaView);
    }


    public void stopPlaying() {
        mediaPlayer.stop();
    }

}
