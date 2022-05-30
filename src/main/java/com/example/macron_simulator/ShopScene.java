package com.example.macron_simulator;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShopScene extends Scene {

    public Text back;

    ListView<ItemPane> itemListView;
    List<Item> itemList;
    List<ItemPane> itemPaneList;

    Text consumable;
    Text consumableAdvice;

    ListView<ItemPane> consumableListView;
    List<Item> consumableList;
    List<ItemPane> consumablePaneList;


    public ShopScene(double X, double Y) {
        super(new Group(), X, Y);

        consumable = new Text("Consumables");

        File cssFile = new File("css/shop.css");

        this.getStylesheets().add("file:///" + cssFile.getAbsolutePath().replace("\\", "/"));

        itemListView = new ListView<ItemPane>();
        itemList = new ArrayList<Item>();
        itemPaneList = new ArrayList<ItemPane>();

        consumableListView = new ListView<ItemPane>();
        consumableList = new ArrayList<Item>();
        consumablePaneList = new ArrayList<ItemPane>();

//        consumableList.add(new Mushroom());
        consumableList.add(new Baguette());

        itemList.add(new YellowVest());
        itemList.add(new Earplugs());
        itemList.add(new LockBox());
        itemList.add(new Trenbolone());

        for (int i = 0; i < itemList.size(); i++) {
            itemPaneList.add(new ItemPane(itemList.get(i)));
        }

        // for item in itempanelist add event handler that adds to the shop

        for (int i = 0; i < itemPaneList.size(); i++) {
            //add to the item bag
            itemPaneList.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, changeHeldItem);
        }

        for (int i = 0; i < itemList.size(); i++) {
            itemListView.getItems().add(itemPaneList.get(i));
        }

        for (int i = 0; i < consumableList.size(); i++) {
            consumablePaneList.add(new ItemPane(consumableList.get(i)));
        }

        // for item in itempanelist add event handler that adds to the shop

        for (int i = 0; i < consumablePaneList.size(); i++) {
            //add to the item bag
            consumablePaneList.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, x);
        }

        for (int i = 0; i < consumableList.size(); i++) {
            consumableListView.getItems().add(consumablePaneList.get(i));
        }

        
        back = new Text("Back");
        back.setFont(new Font(30));

        back.setX(0);
        back.setY(50);

        itemListView.setLayoutX(0);
        itemListView.setLayoutY(50);

        consumableListView.setLayoutX(200);
        consumableListView.setLayoutY(50);
        consumable.setFont(new Font(30));
        consumable.setLayoutX(200);
        consumable.setLayoutY(35);

        consumableAdvice = new Text("Press 0 during combat to eat a baguette to restore 40 health\n You can up to three baguettes");

        consumableAdvice.setLayoutX(600);
        consumableAdvice.setLayoutY(600);
        consumableAdvice.setFont(new Font(20));

        Text heldItemText = new Text();

        ((Group) this.getRoot()).getChildren().add(consumableAdvice);
        ((Group) this.getRoot()).getChildren().add(consumable);
        ((Group) this.getRoot()).getChildren().add(back);
        ((Group) this.getRoot()).getChildren().add(itemListView);
        ((Group) this.getRoot()).getChildren().add(consumableListView);
        

        Main.itemBag.setLayoutX(400);
        Main.itemBag.setLayoutY(400);
        ((Group) this.getRoot()).getChildren().add(Main.itemBag);

    }

    EventHandler x = new EventHandler() {
        @Override
        public void handle(Event event) {
            ItemPane x = (ItemPane) event.getSource();

            Main.itemBag.addItem(x.getitemName());

        }
    };

    EventHandler changeHeldItem = new EventHandler() {
        @Override
        public void handle(Event event) {
            ItemPane x = (ItemPane) event.getSource();

            Main.heldItem = x.getitemName();
            Main.itemBag.dealWithHeldItem();

        }
    };


}
