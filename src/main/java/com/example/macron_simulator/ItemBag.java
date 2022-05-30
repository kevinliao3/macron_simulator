package com.example.macron_simulator;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ItemBag extends Pane {

    public List<Item> itemList;
    public List<Text> textList;

    Text heldItem;
    public ItemBag() {
        itemList = new ArrayList<Item>();
        textList = new ArrayList<Text>();

        heldItem = new Text();
    }

    public void dealWithHeldItem() {

        this.getChildren().remove(heldItem);
        String c = "Current held item: " + Main.heldItem;
        heldItem = new Text(c);
        heldItem.setFont(new Font(50));
        heldItem.setX(50);
        heldItem.setY(50);
        this.getChildren().add(heldItem);
    }

    public void deleteTextList() {
if (textList.size() > 0) {
    for (int i = 0; i < textList.size(); i++) {
        // Print all elements of List
        this.getChildren().remove(textList.get(i));
    }
    ;
}
        textList.clear();
    }

    public void renewTextList() {

        for (int i = 0; i < itemList.size(); i++) {
            // Print all elements of List
            this.getChildren().add(textList.get(i));
        };
    }


    public List<Text> generateTextList() {
        double x = 100;
        double y = 100;

        for (int i = 0; i < itemList.size(); i++) {
            // Print all elements of List
            Integer itemIndex = i;
            String itemVersion = itemIndex.toString();
            textList.add(new Text(x,(y+i*10),itemVersion + itemList.get(i).name));
        }

        return textList;

    }

    public void addItem(String id) {

        if (itemList.size() >= 3) {
            return;
        }
        else {
            switch (id) {
                case ("rad140"):
                    Item RAD140 = new Trenbolone();
                    itemList.add(RAD140);
                    break;
                case ("mushroom"):
                    Item Mushrooms = new Mushroom();
                    itemList.add(Mushrooms);
                    break;
                case ("baguette"):
                    Item baguette = new Baguette();
                    itemList.add(baguette);
                    break;

            }
            deleteTextList();
            generateTextList();
            renewTextList();
        }
    }

    public void useItem(Integer itemSlot) {

        Item x = itemList.remove((int) itemSlot);
        //Call the effect

        x.effect();

        deleteTextList();
        generateTextList();
        renewTextList();
    }

    //For poutou
    public void deleteItems() {
        itemList.clear();
        deleteTextList();
        generateTextList();
        renewTextList();

    }
};
