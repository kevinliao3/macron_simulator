package com.example.macron_simulator;

import com.fasterxml.jackson.core.type.TypeReference;
import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Scene;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//I think that instead of a DialogueScene I will use a DialogueController class that switches between different scenes
public class DialogueScene extends Scene {

    ObjectMapper mapper;
    File dialogueFile;
    File personToImageFile;

    //Have it accept JSON dialogue options
    public DialogueScene(String jsonpath, double X, double Y) {
        super(new Group(), X, Y);

        mapper = new ObjectMapper();

        personToImageFile = new File("assets/persontoimage.json");

        try {
            var xx = mapper.readTree(personToImageFile);
            Map<String, String> personToImage = mapper.convertValue(xx,new TypeReference<Map<String, String>>(){});
        }
        catch (IOException e) {
            throw new AssertionError("IOException found.",e);
        }

        dialogueFile = new File(jsonpath);

        StackPane x = new StackPane();

        ((Group) this.getRoot()).getChildren().add(x);


        try {
            var dialogueJsonNode = mapper.readTree(dialogueFile);

            Map<String, Object> result = mapper.convertValue(dialogueJsonNode,new TypeReference<Map<String, Object>>(){});
            ArrayList<Map<String, String>> y = (ArrayList<Map<String,String>>) result.get("dialogue");

            for (Map sentence: y) {
                String id = (String) sentence.get("id");
                String dialogue = (String) sentence.get("dialogue");

                System.out.println(id);
                System.out.println(dialogue);

                switch(id) {
                    case "macron":
                        x = new MacronCanvas(X,Y,dialogue);
                        break;
//                    case "lepen":
//                        x = new LepenCanvas(X,Y,dialogue);
//                        break;
                }

            }

        } catch (IOException e) {
            throw new AssertionError("IOException found.",e);
        }

    }

}
