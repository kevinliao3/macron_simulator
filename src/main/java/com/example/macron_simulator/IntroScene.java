package com.example.macron_simulator;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class IntroScene extends Scene {

    Media media;
    MediaPlayer mediaPlayer;
    MediaView mediaView;

    public IntroScene() {
        super(new Group(), 540, 209);
        media = new Media((new File("assets/slap.mp4").toURI().toString()));
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView = new MediaView(mediaPlayer);
        ((Group) this.getRoot()).getChildren().add(mediaView);
    }



}
