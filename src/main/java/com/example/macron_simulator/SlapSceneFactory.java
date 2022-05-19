package com.example.macron_simulator;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SlapSceneFactory {

    public SlapScene createSlapScene(String id, FightScene parent) {
        switch (id) {
           case "brigette":
               Image brigette = new Image("file:assets/brigette_head.PNG");
               ImageView brigetteView = new ImageView(brigette);        //Need to have a function that checks for collisions
               brigetteView.setFitWidth(100);
               brigetteView.setFitHeight(100);
               SlapScene slapScene = new SlapScene(Main.screenX, Main.screenY, brigetteView, parent);
            return slapScene;
        }


return null;
    };

}
