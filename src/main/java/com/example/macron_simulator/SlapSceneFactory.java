package com.example.macron_simulator;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SlapSceneFactory {

    public SlapScene createSlapScene(String id, FightScene parent) {
        switch (id) {
           case "brigette":
               ImageView opponentView = parent.opponent;
               opponentView.setFitWidth(100);
               opponentView.setFitHeight(100);
               SlapScene slapScene = new SlapScene(Main.screenX, Main.screenY, opponentView, parent);
            return slapScene;
        }


return null;
    };

}
