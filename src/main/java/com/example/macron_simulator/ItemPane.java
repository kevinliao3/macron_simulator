package com.example.macron_simulator;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ItemPane extends Pane {

    Rectangle y;
    Text itemName;

    public ItemPane(Item x) {
        super();
        //Need to add text
        itemName = new Text(x.name);
        itemName.setFont(new Font(20));

        itemName.setY(20);

        this.getChildren().addAll(itemName);


    }

    public String getitemName() {
        return (itemName.getText());
    }

}
