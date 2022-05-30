package com.example.macron_simulator;

import com.fasterxml.jackson.core.type.TypeReference;
import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Scene;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//I think that instead of a DialogueScene I will use a DialogueController class that switches between different scenes
public class DialogueScene extends Scene {

    public Scene nextScene;

    Text dialogue;

    //Have it accept JSON dialogue options
    public DialogueScene(String passedDialogue) {
        super(new Group(), Main.screenX, Main.screenY);

        this.dialogue = new Text(passedDialogue);
        dialogue.setFont(new Font(30));
        dialogue.setWrappingWidth(300);
        dialogue.setFill(Color.RED);
//        dialogue.setX(700);
//        dialogue.setY(300);
//        ((Group) this.getRoot()).getChildren().add(dialogue);

    }

};
