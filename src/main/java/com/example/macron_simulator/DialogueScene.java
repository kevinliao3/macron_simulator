package com.example.macron_simulator;

import com.fasterxml.jackson.core.type.TypeReference;
import javafx.scene.Group;
import javafx.scene.Scene;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DialogueScene extends Scene {

    ObjectMapper mapper;
    File dialogueFile;
    File personToImageFile;

    //Have it accept JSON dialogue options
    public DialogueScene(String jsonpath) {
        super(new Group(), 540, 209);

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

        try {
            var dialogueJsonNode = mapper.readTree(dialogueFile);

            Map<String, Object> result = mapper.convertValue(dialogueJsonNode,new TypeReference<Map<String, Object>>(){});
            ArrayList<Map<String, String>> y = (ArrayList<Map<String,String>>) result.get("dialogue");

            for (Map sentence: y) {
                System.out.println(sentence.get("id"));
                System.out.println(sentence.get("dialogue"));

                //Change the scene for the person and render the dialogue box

                //This will be if the design is like in the design document
//                DialogueBubble zz = new DialogueBubble((String) sentence.get("id"), (String) sentence.get("dialogue"));
                //Create a dialogue bubble for each sentence
                //Render it?
                //set the css background by reading the appropriate mapping?
            }

        } catch (IOException e) {
            throw new AssertionError("IOException found.",e);
        }
//        ((Group) this.getRoot()).getChildren().add(textFlow);
        String cssValue = "-fx-background-image: url(macron_phone.jpg)";
        (this.getRoot()).setStyle(cssValue);

    }

}
